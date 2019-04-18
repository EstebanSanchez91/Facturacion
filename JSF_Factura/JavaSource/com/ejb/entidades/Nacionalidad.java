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
@Table(name = "nacionalidad")
public class Nacionalidad {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //Genera el id automatico
	@Column(name="nac_id")
	private int id;

	@Column(name="nac_pais")
	private String pais;
	
	@Column(name="nac_nombre")
	private String nombre;
	
	@OneToMany (mappedBy="nacionalidad")
	private List<Autor> listaAutor;
	
	@OneToMany (mappedBy="nacionalidad")
	private List<Cliente> listaCliente;
	
	//getters and setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<Autor> getListaAutor() {
		return listaAutor;
	}
	public void setListaAutor(List<Autor> listaAutor) {
		this.listaAutor = listaAutor;
	}
	public List<Cliente> getListaCliente() {
		return listaCliente;
	}
	public void setListaCliente(List<Cliente> listaCliente) {
		this.listaCliente = listaCliente;
	}
	
	
}
