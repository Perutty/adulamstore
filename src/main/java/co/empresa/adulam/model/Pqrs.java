package co.empresa.adulam.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;

@Entity
@Table (name="pqrs")
@Data
public class Pqrs {

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
	private String asunto;
	
	@Column
	private String mensaje;
	
	@Column
	@DateTimeFormat(iso = ISO.DATE)
	private Date fechacreacion;
}
