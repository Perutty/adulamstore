package co.empresa.adulam.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table (name="administrador")
@Data
public class Administrador {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String nombre;
	
	@Column
	private String email;
	
	@Column
	private String password;
	
	
	public Administrador(){};
	
	public Administrador(String email, String password) {
		this.email = email;
		this.password = password;
	}

}
