package co.empresa.adulam.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.empresa.adulam.model.Administrador;
import co.empresa.adulam.model.Categoria;
import co.empresa.adulam.model.Producto;
import co.empresa.adulam.services.AdministradorService;
import co.empresa.adulam.services.CategoriaService;
import co.empresa.adulam.services.ProductoService;

@Controller
@RequestMapping("/producto")
public class ProductoController {
	
	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private AdministradorService administradorService;
	
	
	@GetMapping("/list")
	public String listProduct(HttpServletRequest request, Model model) {
		int adm_id = (int)request.getSession().getAttribute("admin_id");
		
		Administrador adm = administradorService.get(adm_id);
		List<Producto> listMostrar = productoService.getAll();
		List<Categoria> listCategoria = categoriaService.getAll();
		model.addAttribute("producto", listMostrar);
		model.addAttribute("categoria", listCategoria);
		model.addAttribute("admin", adm);
		return "dashboard";
	}
	
	@PostMapping("/save")
	public String insert(RedirectAttributes att, Producto producto, Model model) {
		productoService.save(producto);
		att.addFlashAttribute("accion", "¡Producto registrado con éxito!");
		return "redirect:/producto/list";
	}
	
	@GetMapping("/edit/{id}")
	public String editProduct(HttpServletRequest request, @PathVariable("id") Integer id, Producto producto, Model model) {
		if(id!=null) {
			List<Categoria> listCategoria = categoriaService.getAll();
			model.addAttribute("categoria", listCategoria);
			int adm_id = (int)request.getSession().getAttribute("admin_id");
			Administrador adm = administradorService.get(adm_id);
			model.addAttribute("admin", adm);
			model.addAttribute("producto", productoService.get(id));
		}else {
			model.addAttribute("producto", new Producto());
		}
		return "editproduct";
	}
	
	@GetMapping("/askdelete/{id}")
	public String ask(@PathVariable("id") Integer id, HttpServletRequest request, HttpSession session,
			Model model) {
		int adm_id = (int)request.getSession().getAttribute("admin_id");
		Administrador adm = administradorService.get(adm_id);
		model.addAttribute("admin", adm);
		model.addAttribute("producto", productoService.get(id));
		return "deleteproduct";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(RedirectAttributes att, @PathVariable("id") Integer id, Model model) {
		productoService.delete(id);
		att.addFlashAttribute("accion", "¡Producto eliminado con éxito!");
		return "redirect:/producto/list";
	}

}
