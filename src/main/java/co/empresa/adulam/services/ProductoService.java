package co.empresa.adulam.services;

import co.empresa.adulam.commands.GenericService;
import co.empresa.adulam.model.Producto;

public interface ProductoService extends GenericService<Producto, Integer>{
	
	public Producto select(String ref);

}
