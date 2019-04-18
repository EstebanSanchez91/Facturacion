package com.ejb.controladores;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.ejb.entidades.Categoria;
import com.ejb.entidades.Libro;
import com.ejb.servicios.CategoriaServicio;
import com.ejb.servicios.LibroServicio;
@ManagedBean
@ViewScoped
public class CategoriaControlador {
	@EJB
	private CategoriaServicio serCategoria;
	@EJB
	private LibroServicio serLibros;
	private List<Categoria> categoriasRecuperadas;
	private List<Libro> librosRecuperados;
	private int idCategoria;
	private Categoria categoria;
	private boolean paraIngresar;
	private boolean paraActualizar;
	private int idCatSeleccionada; // id de dropdown

	// Constructor
	public CategoriaControlador() {
		categoria = new Categoria();
		paraIngresar = false;
		paraActualizar = true;

	}

	// para evitar null pointer exception.. postContruct obliga al compilador a
	// leer este metodo primero.
	@PostConstruct
	public void ejecutar() {
		categoriasRecuperadas = serCategoria.recuperarTodo(categoria);
		librosRecuperados = serLibros.recuperarTodos();
	}

	public void ingresarCategoria() {
		try {
			categoria.setNombre(serLibros.buscarPorId(idCatSeleccionada).getCategoria().getNombre());
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Categoria Ingresado Exitosamente", null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje);
		} catch (Exception e) {
			FacesMessage mensaje2 = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje2);
		}
		categoria = new Categoria(); // Limpiar formulario
		ejecutar(); // parar que se refresque la tabla
		paraIngresar = false;
		paraActualizar = true;
	}

	public void actualizarCategoria() {
		try {
			serCategoria.actualizar(categoria);
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Categoria Actualizado Existosamente", null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje);
		} catch (Exception e) {
			FacesMessage mensaje2 = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje2);
		}

		categoria = new Categoria(); // Limpiar formulario
		ejecutar(); // para refrescar tabla
		paraIngresar = false;
		paraActualizar = true;
	}

	public void seleccionarCategoria() {
		idCategoria = serCategoria.buscarPorId(categoria.getId()).getId();
		paraIngresar = true;
		paraActualizar = false;
	}

	public void eliminarCategoria() {
		try {
			serCategoria.eliminar(categoria);
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Categoria Eliminada Exitosamente", null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje);
		} catch (Exception e) {
			FacesMessage mensaje2 = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje2);
		}
		categoria = new Categoria(); // Limpiar
		ejecutar(); // refrescar
	}

	public List<Categoria> getCategoriasRecuperadas() {
		return categoriasRecuperadas;
	}

	public void setCategoriasRecuperadas(List<Categoria> categoriasRecuperadas) {
		this.categoriasRecuperadas = categoriasRecuperadas;
	}

	public List<Libro> getLibrosRecuperados() {
		return librosRecuperados;
	}

	public void setLibrosRecuperados(List<Libro> librosRecuperados) {
		this.librosRecuperados = librosRecuperados;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
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

	public int getIdCatSeleccionada() {
		return idCatSeleccionada;
	}

	public void setIdCatSeleccionada(int idCatSeleccionada) {
		this.idCatSeleccionada = idCatSeleccionada;
	}

}
