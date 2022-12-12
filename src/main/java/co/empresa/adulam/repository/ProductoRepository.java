package co.empresa.adulam.repository;

import org.springframework.data.repository.CrudRepository;

import co.empresa.adulam.model.Producto;

public interface ProductoRepository extends CrudRepository<Producto, Integer> {

	public abstract Producto findByRef(String Ref);
}
