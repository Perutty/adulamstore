package co.empresa.adulam.services;

import co.empresa.adulam.commands.GenericService;
import co.empresa.adulam.model.Administrador;

public interface AdministradorService extends GenericService<Administrador, Integer>{

	public Administrador select(String email, String password);
}
