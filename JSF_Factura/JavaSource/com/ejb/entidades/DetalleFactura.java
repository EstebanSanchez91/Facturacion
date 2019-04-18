package com.ejb.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="detalle_factura")
public class DetalleFactura {

	@Id //Indica que esta variable es el PK de la tabla
	@GeneratedValue(strategy=GenerationType.IDENTITY) //Genera el id automatico
	@Column(name = "df_id")
	private int id;
	
	@JoinColumn(name="li_id")
	@ManyToOne
	private Libro libro;
	
	@JoinColumn(name="fac_id")
	@ManyToOne
	private Factura factura;
	
	@Column(name="df_cantidad")
	private int cantidad;
	
	@Transient
	private double subtotal;

	
	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
	
}
