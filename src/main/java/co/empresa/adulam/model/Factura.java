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
@Table(name="factura")
@Data
public class Factura {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	@DateTimeFormat(iso = ISO.DATE)
	private Date fechacreacion;
	
	@Column
	private Integer id_cliente;
	
	@Column
	private Integer totalapagar;
	
	public Factura(){};
	
	public Factura (Integer id){
		this.id = id;
	};

}
