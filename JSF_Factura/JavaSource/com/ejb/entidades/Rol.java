package com.ejb.entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="rol")
public class Rol {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //Genera el id automatico
	@Column(name="rol_id")
	private int rol;
	
	@Column(name="rol_nombre")
	private String nombre;
	
	@Column(name="rol_descripcion")
	private String descripcion;
	
	@OneToMany (mappedBy="rol")
	private List<Usuario> listaUsuario;
	
	//getters y setters
	public int getRol() {
		return rol;
	}
	public void setRol(int rol) {
		this.rol = rol;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}
	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}
	
	
}
