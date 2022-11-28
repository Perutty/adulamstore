package co.empresa.adulam.repository;

import org.springframework.data.repository.CrudRepository;

import co.empresa.adulam.model.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Integer> {

	public abstract Cliente findByEmailAndPassword(String email, String password);
}
