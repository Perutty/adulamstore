package co.empresa.adulam.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.empresa.adulam.model.Producto;
import co.empresa.adulam.services.ProductoService;

@Controller
@RequestMapping("/producto")
public class ProductoController {
	
	@Autowired
	public ProductoService productoService;
	
	@GetMapping("/list")
	public String listProduct(HttpServletRequest request, Model model) {
		List<Producto> listMostrar = productoService.getAll();
	
		model.addAttribute("producto", listMostrar);
		return "dashboard";
	}
	
	@PostMapping("/save")
	public String insert(Producto producto, Model model) {
		productoService.save(producto);
		return "redirect:/producto/list";
	}

}
