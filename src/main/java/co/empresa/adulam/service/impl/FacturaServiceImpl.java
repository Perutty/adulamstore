package co.empresa.adulam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.empresa.adulam.commands.GenericServiceImpl;
import co.empresa.adulam.model.Factura;
import co.empresa.adulam.repository.FacturaRepository;
import co.empresa.adulam.services.FacturaService;

@Service
public class FacturaServiceImpl extends GenericServiceImpl<Factura, Integer> implements FacturaService{
	
	@Autowired
	private FacturaRepository facturaRepository;
	
	@Override
	public CrudRepository<Factura, Integer> getDao(){
		return facturaRepository;
	}

}
