package co.empresa.adulam.repository;

import org.springframework.data.repository.CrudRepository;

import co.empresa.adulam.model.Factura;

public interface FacturaRepository extends CrudRepository<Factura, Integer> {
	

}
