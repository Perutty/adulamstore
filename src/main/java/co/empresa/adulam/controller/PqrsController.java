package co.empresa.adulam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.empresa.adulam.model.Pqrs;
import co.empresa.adulam.services.PqrsService;

@Controller
@RequestMapping("/pqrs")
public class PqrsController {
	
	@Autowired
	private PqrsService pqrsService;
	
	
	@GetMapping("/new")
	public String showForm(Model model){
		return "pqrs";
	}
	
	@PostMapping("/save")
	public String savePqrs(Pqrs pqrs, Model model){
		pqrsService.save(pqrs);
		return "redirect:/adulamstore/list";
	}
	
	@GetMapping("/list")
	public String listPqrs(Model model) {
		List<Pqrs> mostrar = pqrsService.getAll();
		model.addAttribute("pqrs", mostrar);
		return "dashboardpqrs";
	}

}
