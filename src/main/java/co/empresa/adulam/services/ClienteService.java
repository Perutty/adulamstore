package co.empresa.adulam.services;

import co.empresa.adulam.commands.GenericService;
import co.empresa.adulam.model.Cliente;

public interface ClienteService extends GenericService<Cliente, Integer>{
	
	public Cliente select(String email, String password);
}
