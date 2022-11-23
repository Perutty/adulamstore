package co.empresa.adulam.repository;

import org.springframework.data.repository.CrudRepository;

import co.empresa.adulam.model.Administrador;

public interface AdministradorRepository extends CrudRepository<Administrador, Integer> {

	public abstract Administrador findByEmailAndPassword(String email, String password);
}
