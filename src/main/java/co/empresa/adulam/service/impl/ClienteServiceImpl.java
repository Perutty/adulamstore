package co.empresa.adulam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.empresa.adulam.commands.GenericServiceImpl;
import co.empresa.adulam.model.Cliente;
import co.empresa.adulam.repository.ClienteRepository;
import co.empresa.adulam.services.ClienteService;

@Service
public class ClienteServiceImpl extends GenericServiceImpl<Cliente, Integer> implements ClienteService{

	@Autowired
	public ClienteRepository clienteRepository;
	
	@Override
	public CrudRepository<Cliente, Integer> getDao(){
		return clienteRepository;
	}
	
	@Override
	public Cliente select(String email, String password) {
		return clienteRepository.findByEmailAndPassword(email, password);
	}
}
