package co.empresa.adulam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.empresa.adulam.commands.GenericServiceImpl;
import co.empresa.adulam.model.Administrador;
import co.empresa.adulam.model.Pqrs;
import co.empresa.adulam.repository.PqrsRepository;
import co.empresa.adulam.services.PqrsService;

@Service
public class PqrsServiceImpl extends GenericServiceImpl<Pqrs, Integer> implements PqrsService{
	
	@Autowired
	public PqrsRepository pqrsRepository;
	
	@Override
	public CrudRepository<Pqrs, Integer> getDao(){
		return pqrsRepository;
	}

}
