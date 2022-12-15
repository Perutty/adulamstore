package co.empresa.adulam.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.empresa.adulam.model.Administrador;
import co.empresa.adulam.model.Categoria;
import co.empresa.adulam.model.Cliente;
import co.empresa.adulam.model.DetalleFactura;
import co.empresa.adulam.model.Factura;
import co.empresa.adulam.model.Producto;
import co.empresa.adulam.services.AdministradorService;
import co.empresa.adulam.services.CategoriaService;
import co.empresa.adulam.services.ClienteService;
import co.empresa.adulam.services.DetalleFacturaService;
import co.empresa.adulam.services.FacturaService;
import co.empresa.adulam.services.ProductoService;

@Controller
@RequestMapping("/detallefactura")
public class DetalleFacturaController {
	
	@Autowired
	private DetalleFacturaService detalleFacturaService;
	
	@Autowired
	private FacturaService facturaService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private AdministradorService administradorService;

	@Autowired
	private CategoriaService categoriaService;
	
	
	public int precio = 0;
	public int total = 0;
	public int subtotal = 0;
	
	
	
	@GetMapping("/topay/{title}/{quantity}/{price}/{total}")
	public String formInvoice(HttpServletRequest request, HttpSession session,@PathVariable("title") String nombre, 
								@PathVariable("quantity") Integer quantity, @PathVariable("price") Integer price,@PathVariable("total") Integer total,Model model) {
				if(request.getSession().getAttribute("cliente_id")!=null) {
					Producto producto = productoService.select(nombre);
					DetalleFactura detallefactura = new DetalleFactura();
					int c_id = (int)request.getSession().getAttribute("cliente_id");
					Cliente cli = clienteService.get(c_id);
					model.addAttribute("cliente", cli.getNombre());
					int id_f = (int)request.getSession().getAttribute("id_f");
						if(producto.getCantidad()>=quantity){
									precio = quantity * price;
									detallefactura.setName_product(nombre);
									detallefactura.setCantidad(quantity);
									detallefactura.setSubtotal(precio);
									detallefactura.setId_factura(id_f);
									detalleFacturaService.save(detallefactura);
									producto.setCantidad(producto.getCantidad()-quantity);
									Factura fac = facturaService.get(id_f);
									fac.setTotalapagar(total);
						}
					return "redirect:/adulamstore/list";
				}
				return "redirect:/adulamstore/login";
					
	}
	
	@GetMapping("/confirmar/")
	public String confirmar(HttpServletRequest request, HttpSession session, Model model) {
		if(request.getSession().getAttribute("cliente_id")!=null) {
			int c_id = (int)request.getSession().getAttribute("cliente_id");
			Cliente cli = clienteService.get(c_id);
			model.addAttribute("cliente", cli.getNombre());	
			Factura factura = new Factura();
			Date dat = new Date();
			java.sql.Date sqlDate = new java.sql.Date(dat.getTime());
			factura.setFechacreacion(sqlDate);
			factura.setId_cliente(cli.getId());
			facturaService.save(factura);
			request.getSession().setAttribute("id_f", factura.getId());
		}
			return "invoiceform";
	
	}
	
	@GetMapping("/borrar")
	public String borrarDetalle(HttpServletRequest request, HttpSession session, Model model) {
		if(request.getSession().getAttribute("id_f")!=null) {
			int id_f = (int)request.getSession().getAttribute("id_f");
			facturaService.delete(id_f);
			return "redirect:/adulamstore/list";
		}else 
			return "redirect:/adulamstore/list";
	}
	
	@GetMapping("/list/{id}")
	public String listDetalle(@PathVariable("id") Integer id, HttpServletRequest request, Model model) {
		int adm_id = (int)request.getSession().getAttribute("admin_id");
		List<DetalleFactura> mostrar = new ArrayList();
		Administrador adm = administradorService.get(adm_id);
		List<Producto> listProductos = productoService.getAll();
		List<Categoria> listCategoria = categoriaService.getAll();
		List<DetalleFactura> detalles = detalleFacturaService.getAll();
		List<Factura> factura = facturaService.getAll();
		detalles.forEach((detal)->{
			if(detal.getId_factura() == id)
				mostrar.add(detal);
				model.addAttribute("detalle", mostrar);
		});
		model.addAttribute("facturas", factura);
		model.addAttribute("admin", adm);
		return "dashboarddetalle";
	}
}


