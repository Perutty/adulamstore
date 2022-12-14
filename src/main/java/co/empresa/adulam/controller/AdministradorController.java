package co.empresa.adulam.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.empresa.adulam.model.Administrador;
import co.empresa.adulam.services.AdministradorService;

@Controller
@RequestMapping("/admin")
public class AdministradorController {

	@Autowired
	private AdministradorService administradorService;
	
	
	@GetMapping("")
	public String login(HttpServletRequest request, HttpSession session, Model model) {
		if(request.getSession().getAttribute("admin_id") != null) {
			return "redirect:/producto/list";
		}else
			return "loginadmin";
	}
	
	@PostMapping("/login")
	public String validate(RedirectAttributes att, @RequestParam String email, @RequestParam String password, 
			HttpServletRequest request, HttpSession session,  Model model) {
		Administrador admin = administradorService.select(email, password);
		
		if(admin != null)
		{
			request.getSession().setAttribute("admin_id", admin.getId());
			return "redirect:/producto/list";
		}else {
			att.addFlashAttribute("loginError", "Usuario o contraseña incorrecta");
			return "redirect:/admin/";
			}
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpSession session,  Model model) {
			request.getSession().invalidate();
			return "redirect:/admin/";
	}
	
	@PostMapping("/save")
	public String insertAdmin(RedirectAttributes att, Administrador admin, Model model) {
		administradorService.save(admin);
		att.addFlashAttribute("accion", "Administrador registrado con éxito!");
		return "redirect:/admin/";
	}
	
	@GetMapping("/new")
	public String showForm(Model model) {
		return "registeradmin";
	}
}
