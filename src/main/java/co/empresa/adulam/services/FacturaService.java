package co.empresa.adulam.services;

import java.io.ByteArrayInputStream;

import co.empresa.adulam.commands.GenericService;
import co.empresa.adulam.model.Factura;

public interface FacturaService extends GenericService<Factura, Integer>{
	
	ByteArrayInputStream exportAllData(int id) throws Exception;
}
