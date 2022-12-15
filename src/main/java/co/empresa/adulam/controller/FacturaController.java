package co.empresa.adulam.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.empresa.adulam.model.Administrador;
import co.empresa.adulam.model.Categoria;
import co.empresa.adulam.model.DetalleFactura;
import co.empresa.adulam.model.Factura;
import co.empresa.adulam.model.Producto;
import co.empresa.adulam.services.AdministradorService;
import co.empresa.adulam.services.CategoriaService;
import co.empresa.adulam.services.DetalleFacturaService;
import co.empresa.adulam.services.FacturaService;
import co.empresa.adulam.services.ProductoService;

@Controller
@RequestMapping("/factura")
public class FacturaController {
	
	@Autowired
	private FacturaService facturaService;
	
	@Autowired
	private CategoriaService categoriaService;

	@Autowired
	private AdministradorService administradorService;

	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private DetalleFacturaService detalleFacturaService;

	
	@GetMapping("/list")
	public String listFactura(HttpServletRequest request, Model model) {
		int adm_id = (int)request.getSession().getAttribute("admin_id");
		
		Administrador adm = administradorService.get(adm_id);
		List<Producto> listProductos = productoService.getAll();
		List<Categoria> listCategoria = categoriaService.getAll();
		List<DetalleFactura> detalles = detalleFacturaService.getAll();
		List<Factura> factura = facturaService.getAll();
		model.addAttribute("detalle", detalles);
		model.addAttribute("facturas", factura);
		model.addAttribute("admin", adm);
		return "dashboardfactura";
	}
}
