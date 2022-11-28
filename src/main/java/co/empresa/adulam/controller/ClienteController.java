package co.empresa.adulam.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.empresa.adulam.model.Categoria;
import co.empresa.adulam.model.Cliente;
import co.empresa.adulam.model.Producto;
import co.empresa.adulam.services.CategoriaService;
import co.empresa.adulam.services.ClienteService;
import co.empresa.adulam.services.ProductoService;

@Controller
@RequestMapping("/adulamstore")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private ProductoService productoService;
    @Autowired
    private CategoriaService categoriaService;
	
	private List<Producto> listMostrar = new ArrayList<>();

	
	@GetMapping("")
	public String store(Model model) {
		return "redirect:/adulamstore/list";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}
	
	@PostMapping("/singin")
	public String validate(RedirectAttributes att, @RequestParam String email, @RequestParam String password, 
			HttpServletRequest request, HttpSession session,  Model model) {
		Cliente cliente = clienteService.select(email, password);
		
		if(cliente != null)
		{
			request.getSession().setAttribute("cliente_id", cliente.getId());
			model.addAttribute("cliente", cliente);
			return "redirect:/adulamstore/home";
		}else {
			att.addFlashAttribute("loginError", "Usuario o contraseña incorrecta");
			return "redirect:/adulamstore/login";
			}
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpSession session,  Model model) {
			request.getSession().invalidate();
			return "redirect:/adulamstore/home";
	}
	
	@PostMapping("/save")
	public String insertClient(RedirectAttributes att, Cliente cliente, Model model) {
		clienteService.save(cliente);
		att.addFlashAttribute("accion", "¡Te has registrado con éxito!");
		return "redirect:/adulamstore/home";
	}
	
	@GetMapping("/edit/{id}")
	public String editClient(@PathVariable("id") Integer id, Cliente cliente, Model model) {
		if(id!=null) {
			model.addAttribute("cliente", clienteService.get(id));
		}else {
			model.addAttribute("cliente", new Producto());
		}
		return "editcliente";
	}
	
	@GetMapping("/list/{id}")
	public String listProduct(@PathVariable("id") Integer id, Model model) {
		List<Producto> listProductos = productoService.getAll();
		List<Categoria> listCategoria = categoriaService.getAll();
		listMostrar.clear();
		listProductos.forEach((producto)->{
			if(producto.getId_categoria() == id)
				listMostrar.add(producto);
		});
		model.addAttribute("categoria", listCategoria);
		model.addAttribute("listProducto", listMostrar);
		return "adulamstore";
	}
	
	@GetMapping("/list")
	public String listProducto(Model model) {
		List<Producto> listProductos = productoService.getAll();
		List<Categoria> listCategoria = categoriaService.getAll();
		model.addAttribute("categoria", listCategoria);
		model.addAttribute("listProducto", listProductos);
		return "adulamstore";
	}
}
