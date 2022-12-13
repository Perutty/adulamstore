package co.empresa.adulam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.empresa.adulam.commands.GenericServiceImpl;
import co.empresa.adulam.model.DetalleFactura;
import co.empresa.adulam.repository.DetalleFacturaRepository;
import co.empresa.adulam.services.DetalleFacturaService;

@Service
public class DetalleFacturaServiceImpl extends GenericServiceImpl<DetalleFactura, Integer> implements DetalleFacturaService{
	
	@Autowired
	private DetalleFacturaRepository detalleFacturaRepository;
	
	@Override
	public CrudRepository<DetalleFactura, Integer> getDao(){
		return detalleFacturaRepository;
	}
	
	

}
