package co.empresa.adulam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.empresa.adulam.commands.GenericServiceImpl;
import co.empresa.adulam.model.Producto;
import co.empresa.adulam.repository.ProductoRepository;
import co.empresa.adulam.services.ProductoService;

@Service
public class ProductoServiceImpl extends GenericServiceImpl<Producto, Integer> implements ProductoService{

	@Autowired
	private ProductoRepository productoRepository;
	
	@Override
	public CrudRepository<Producto, Integer> getDao(){
		return productoRepository;
	}
	
	@Override
	public Producto select(String nombre) {
		return productoRepository.findByNombre(nombre);
	}
}
