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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.cursojsf.validadores.Cedula;

@Entity
@Table(name = "cliente")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cli_id")
	private int codigoC;
	
	@Cedula(message="Cedula no valida")
	@Size (min = 10 , max =10, message= "El Apellido debe tener entre 5 y 50 letras")
	@Column(name = "cli_cedula")
	private String cedula;
	
	@NotNull(message = "El nombre es un campo obligatorio")
	@Size (min = 3 , max =25, message= "El nombre debe tener entre 5 y 50 letras")
	@Column(name = "cli_nombre")
	private String nombre;
	
	@NotNull(message = "El apelllido es un campo obligatorio")
	@Size (min = 3 , max =25, message= "El apellido debe tener entre 5 y 50 letras")
	@Column(name = "cli_apellido")
	private String apellido;
	
	@NotNull(message = "La dirección es un campo obligatorio")
	@Size (min = 3 , max =50, message= "La dirección debe tener entre 5 y 50 letras")
	@Column(name = "cli_direccion")
	private String direccion;
	
	@Column(name = "cli_fecha_nacimiento")
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;
	
	@JoinColumn(name = "nac_id")
	@ManyToOne
	private Nacionalidad nacionalidad;
	
	
	
	@OneToMany(mappedBy="cliente") 
	private List<Factura> listafacturas;

	// constructor vacio
	public Cliente() {
		
	}

	public Nacionalidad getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(Nacionalidad nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getCodigoC() {
		return codigoC;
	}

	public void setCodigoC(int codigoC) {
		this.codigoC = codigoC;
	}

	

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	
	public List<Factura> getListafacturas() {
		return listafacturas;
	}

	public void setListafacturas(List<Factura> listafacturas) {
		this.listafacturas = listafacturas;
	}
	
	

}
