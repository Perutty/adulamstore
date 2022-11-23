package co.empresa.adulam.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="producto")
@Data
public class Producto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String marca;
	
	@Column
	private Integer precio;
	
	@Column
	private String nombre;
	
	@Column
	private Integer cantidad;
	
	@Column
	private Integer id_categoria;
	
	@Column
	private Integer id_detallefactura;
	
	@Column
	private String imagen;
	
	
	public Producto(){};
	
	public Producto(String nombre) {
		this.nombre = nombre;
	};

}
