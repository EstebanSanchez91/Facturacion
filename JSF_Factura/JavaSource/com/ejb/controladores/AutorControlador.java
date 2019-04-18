package com.ejb.controladores;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.ejb.entidades.Autor;
import com.ejb.entidades.Nacionalidad;
import com.ejb.servicios.AutorServicio;
import com.ejb.servicios.NacionalidadServicio;
@ManagedBean
@ViewScoped
public class AutorControlador {
	@EJB
	private AutorServicio serAutor;
	@EJB
	private NacionalidadServicio serNacionalidad;
	private List<Autor> autoresRecuperados;
	private List<Nacionalidad> nacionalidadesRecuperadas;
	private int idNacionalidad;
	private Autor autor;
	private boolean paraIngresar;
	private boolean paraActualizar;
	private int idNacionalidadSeleccionada; // id de dropdown

	// Constructor
	public AutorControlador() {
		autor = new Autor();
		paraIngresar = false;
		paraActualizar = true;

	}

	// para evitar null pointer exception.. postContruct obliga al compilador a
	// leer este metodo primero.
	@PostConstruct
	public void ejecutar() {
		autoresRecuperados = serAutor.recuperarTodo(autor);
		nacionalidadesRecuperadas = serNacionalidad.recuperarTodos();
	}

	public void ingresarAutor() {
		try {
			autor.setNacionalidad(serNacionalidad.buscarPorId(idNacionalidadSeleccionada));
			serAutor.ingresarAutores(autor);
			
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Autor Ingresado Exitosamente", null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje);
		} catch (Exception e) {
			FacesMessage mensaje2 = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje2);
		}
		autor = new Autor(); // Limpiar formulario
		ejecutar(); // parar que se refresque la tabla
		paraIngresar = false;
		paraActualizar = true;
	}

	public void actualizarAutor() {
		try {
			serAutor.actualizar(autor);
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Autor Actualizado Existosamente", null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje);
		} catch (Exception e) {
			FacesMessage mensaje2 = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje2);
		}

		autor = new Autor(); // Limpiar formulario
		ejecutar(); // para refrescar tabla
		paraIngresar = false;
		paraActualizar = true;
	}

	public void seleccionarAutor() {
		idNacionalidad = serAutor.buscarPorId(autor.getCodigo()).getCodigo();
		paraIngresar = true;
		paraActualizar = false;
	}

	public void eliminarAutor() {
		try {
			serAutor.eliminar(autor);
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Autor Eliminado Exitosamente", null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje);
		} catch (Exception e) {
			FacesMessage mensaje2 = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje2);
		}
		autor = new Autor(); // Limpiar
		ejecutar(); // refrescar
	}

	public List<Autor> getAutoresRecuperados() {
		return autoresRecuperados;
	}

	public void setAutoresRecuperados(List<Autor> autoresRecuperados) {
		this.autoresRecuperados = autoresRecuperados;
	}

	public List<Nacionalidad> getNacionalidadesRecuperadas() {
		return nacionalidadesRecuperadas;
	}

	public void setNacionalidadesRecuperadas(
			List<Nacionalidad> nacionalidadesRecuperadas) {
		this.nacionalidadesRecuperadas = nacionalidadesRecuperadas;
	}

	public int getidNacionalidad() {
		return idNacionalidad;
	}

	public void setidNacionalidad(int idNacionalidad) {
		this.idNacionalidad = idNacionalidad;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public boolean isParaIngresar() {
		return paraIngresar;
	}

	public void setParaIngresar(boolean paraIngresar) {
		this.paraIngresar = paraIngresar;
	}

	public boolean isParaActualizar() {
		return paraActualizar;
	}

	public void setParaActualizar(boolean paraActualizar) {
		this.paraActualizar = paraActualizar;
	}

	public int getIdNacionalidadSeleccionada() {
		return idNacionalidadSeleccionada;
	}

	public void setIdNacionalidadSeleccionada(int idNacionalidadSeleccionada) {
		this.idNacionalidadSeleccionada = idNacionalidadSeleccionada;
	}

}
