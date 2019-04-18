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
@Table(name="libro")

public class Libro {
	
	@Id //Indica que esta variable es el PK de la tabla
	@GeneratedValue(strategy=GenerationType.IDENTITY) //Genera el id automatico
	@Column(name = "li_id")
	private int id;
	
	@Column(name = "li_titulo")
	private String titulo;
		
	@Temporal(TemporalType.DATE)
	@Column(name="li_fecha_publicacion")
	private Date fecha_publicacion;
	
	@Column(name="li_precio")
	private double precio;
	
	@JoinColumn(name="cat_id")
	@ManyToOne
	private Categoria categoria;
	
	@JoinColumn(name="au_id")
	@ManyToOne
	private Autor autor;

	
	@OneToMany(mappedBy="libro") //"departamento" debe ser el mismo nombre que esta declarada en el Empleado
	private List<DetalleFactura> detallesfacturas;
	
		
	public Libro() {
		super();
	
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getFecha_publicacion() {
		return fecha_publicacion;
	}
	public void setFecha_publicacion(Date fecha_publicacion) {
		this.fecha_publicacion = fecha_publicacion;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public Autor getAutor() {
		return autor;
	}
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	public List<DetalleFactura> getDetallesfacturas() {
		return detallesfacturas;
	}
	public void setDetallesfacturas(List<DetalleFactura> detallesfacturas) {
		this.detallesfacturas = detallesfacturas;
	}
	
	
	
	
}
