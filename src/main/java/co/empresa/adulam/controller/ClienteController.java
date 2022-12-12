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
	
	List<Producto> listMostrar = new ArrayList<>();

	
	@GetMapping("")
	public String store(Model model) {
		List<Categoria> listCategoria = categoriaService.getAll();
		model.addAttribute("categoria", listCategoria);
		return "home";
	}
	
	@GetMapping("/login")
	public String login(HttpServletRequest request, HttpSession session, Model model) {
		if(request.getSession().getAttribute("cliente_id") != null) {
			return "redirect:/adulamstore/list";
		}
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
			att.addFlashAttribute("cliente", cliente.getNombre());
			return "redirect:/adulamstore/list";
		}else {
			att.addFlashAttribute("loginError", "Usuario o contraseña incorrecta");
			return "redirect:/adulamstore/login";
			}
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpSession session,  Model model) {
			request.getSession().invalidate();
			return "redirect:/adulamstore";
	}
	
	@PostMapping("/save")
	public String insertClient(RedirectAttributes att, Cliente cliente, Model model) {
		clienteService.save(cliente);
		att.addFlashAttribute("accion", "¡Te has registrado con éxito!");
		return "redirect:/adulamstore/list";
	}
	
	@GetMapping("/edit/{id}")
	public String editClient(HttpServletRequest request, HttpSession session,
							@PathVariable("id") Integer id, Cliente cliente, Model model) {
		if(id!=null) {
			model.addAttribute("cliente", clienteService.get(id));
		}else {
			model.addAttribute("cliente", new Producto());
		}
		return "editcliente";
	}
	
	@GetMapping("/list/{id}")
	public String listProductId(HttpServletRequest request, HttpSession session,
								@PathVariable("id") Integer id,RedirectAttributes att, Model model) {
		List<Categoria> listCategoria = categoriaService.getAll();
		model.addAttribute("categoria", listCategoria);
		List<Producto> listProductos = productoService.getAll();
		if(request.getSession().getAttribute("cliente_id") != null) {
			int c_id = (int)request.getSession().getAttribute("cliente_id");
			Cliente cli = clienteService.get(c_id);
			model.addAttribute("cliente", cli.getNombre());
			return "adulamstore";
		}
		listMostrar.clear();
		listProductos.forEach((producto)->{
			if(producto.getId_categoria() == id)
				listMostrar.add(producto);
		});
		model.addAttribute("listProducto", listMostrar);
		return "adulamstore";
	}
	
	@GetMapping("/list")
	public String listProducto(HttpServletRequest request, HttpSession session, Model model) {
		List<Producto> listProductos = productoService.getAll();
		List<Categoria> listCategoria = categoriaService.getAll();
		model.addAttribute("categoria", listCategoria);
		model.addAttribute("listProducto", listProductos);
		if(request.getSession().getAttribute("cliente_id") != null) {
			int c_id = (int)request.getSession().getAttribute("cliente_id");
			Cliente cli = clienteService.get(c_id);
			model.addAttribute("cliente", cli.getNombre());	
			return "adulamstore";
			
		}
		else return "adulamstore";
	}
	
	@GetMapping("/new")
	public String showForm(Model model) {
		return "registerpersona";
	}
}
