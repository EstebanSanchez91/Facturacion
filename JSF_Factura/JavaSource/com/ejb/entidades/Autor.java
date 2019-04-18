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
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "autor")
public class Autor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "au_id")
	private int codigo;
	@NotNull (message = "El Nombre es un campo requerido")
	@Column(name = "au_nombre")
	private String nombre;
	@NotNull (message = "El Apellido es un campo requerido")
	@Column(name = "au_apellido")
	private String apellido;
	@Column(name = "au_fecha_nacimiento")
	private Date fechaNacimiento;
	@NotNull (message = "La Nacionalidad es un campo requerido")
	
	@JoinColumn(name = "nac_id")
	@ManyToOne // asignando la relacion
	private Nacionalidad nacionalidad;
	
	@OneToMany(mappedBy="autor")
	private List<Libro> libros;
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Autor(int codigo , String nombre, String apellido, Nacionalidad nacionalidad) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.apellido = apellido;
		this.nacionalidad = nacionalidad;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Nacionalidad getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(Nacionalidad nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public Autor(){
	}
	
	public Autor(String nombre, String apellido){
		this.nombre= nombre;
		this.apellido= apellido;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public List<Libro> getLibros() {
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}
	
}
