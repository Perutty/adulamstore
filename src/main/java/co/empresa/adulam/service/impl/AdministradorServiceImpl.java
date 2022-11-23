package co.empresa.adulam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.empresa.adulam.commands.GenericServiceImpl;
import co.empresa.adulam.model.Administrador;
import co.empresa.adulam.repository.AdministradorRepository;
import co.empresa.adulam.services.AdministradorService;

@Service
public class AdministradorServiceImpl extends GenericServiceImpl<Administrador, Integer> implements AdministradorService {
	
	@Autowired
	public AdministradorRepository administradorRepository;
	
	@Override
	public CrudRepository<Administrador, Integer> getDao(){
		return administradorRepository;
	}
	
	@Override
	public Administrador select(String email, String password) {
		return administradorRepository.findByEmailAndPassword(email, password);
	}
	

}
