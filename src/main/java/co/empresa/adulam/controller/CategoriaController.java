package co.empresa.adulam.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.empresa.adulam.model.Categoria;
import co.empresa.adulam.model.Producto;
import co.empresa.adulam.services.CategoriaService;
import co.empresa.adulam.services.ProductoService;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {
	
}
