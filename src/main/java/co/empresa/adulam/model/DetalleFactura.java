package co.empresa.adulam.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="detallefactura")
@Data
public class DetalleFactura {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private Integer cantidad;
	
	@Column
	private Integer subtotal;
	
	@Column
	private String name_product;
	
	@Column
	private Integer id_factura;
	
	public DetalleFactura(){};
	
	

}
