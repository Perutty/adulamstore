package co.empresa.adulam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.empresa.adulam.commands.GenericServiceImpl;
import co.empresa.adulam.model.Categoria;
import co.empresa.adulam.repository.CategoriaRepository;
import co.empresa.adulam.services.CategoriaService;

@Service
public class CategoriaServiceImpl extends GenericServiceImpl<Categoria, Integer> implements CategoriaService{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Override
	public CrudRepository<Categoria, Integer> getDao(){
		return categoriaRepository;
	}
	
}
