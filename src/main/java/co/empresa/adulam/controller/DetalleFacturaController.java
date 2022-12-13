package co.empresa.adulam.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.empresa.adulam.model.Cliente;
import co.empresa.adulam.services.ClienteService;
import co.empresa.adulam.services.DetalleFacturaService;

@Controller
@RequestMapping("/detallefactura")
public class DetalleFacturaController {
	
	@Autowired
	private DetalleFacturaService detalleFacturaService;
	
	@Autowired
	private ClienteService clienteService;
	
	
	
	@GetMapping("/topay")
	public String formInvoice(HttpServletRequest request, HttpSession session, Model model) {
				if(request.getSession().getAttribute("cliente_id")!=null) {
					int c_id = (int)request.getSession().getAttribute("cliente_id");
					Cliente cli = clienteService.get(c_id);
					model.addAttribute("cliente", cli.getNombre());
					return "invoiceform";
				}else
					return "redirect:/adulamstore/login";
			}
	}


