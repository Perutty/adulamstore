package co.empresa.adulam.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table (name="cliente")
@Data
public class Cliente {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String nombre;
	
	@Column
	private String telefono;
	
	@Column
	private String email;
	
	@Column
	private String password;
	
	
	public Cliente(){};
	
	public Cliente(String email, String password) {
		this.email = email;
		this.password = password;
	}

}
