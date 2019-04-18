package com.ejb.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="factura")
public class Factura {

	@Id //Indica que esta variable es el PK de la tabla
	//@GeneratedValue(strategy=GenerationType.IDENTITY) //Genera el id automatico
	@Column(name = "fac_id")
	private int id;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fac_fecha")
	private Date fecha;
	
	@JoinColumn(name="cli_id")
	@ManyToOne
	private Cliente cliente;
	
	@OneToMany(mappedBy="factura") 
	private List<DetalleFactura> detallesfacturas;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<DetalleFactura> getDetallesfacturas() {
		return detallesfacturas;
	}

	public void setDetallesfacturas(List<DetalleFactura> detallesfacturas) {
		this.detallesfacturas = detallesfacturas;
	}

	
}
