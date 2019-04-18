package com.ejb.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import com.ejb.entidades.Categoria;
import com.ejb.entidades.Autor;
import com.ejb.entidades.Libro;
import com.ejb.servicios.AutorServicio;
import com.ejb.servicios.CategoriaServicio;
import com.ejb.servicios.LibroServicio;


@ManagedBean
@ViewScoped
public class LibroControlador {

	@EJB
	private LibroServicio libserv;
	
	@EJB
	private AutorServicio autserv;
	
	@EJB
	private CategoriaServicio catserv;
	
	private Libro libro;
	private List<Libro> lista;
	private int CategoriaSelecionado;
	private Categoria categoria;
	private int AutorSelecionado;
	private Autor autor;
	private boolean paraIngresar;
	private boolean paraActualizar;
	private boolean paraEliminar;

	
	
	public LibroControlador() {
		super();
		this.libro= new Libro();
		this.lista= new ArrayList<Libro>();
		this.paraIngresar=false;
		this.paraActualizar=true;
		this.paraEliminar=true;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public List<Libro> getLista() {
		return lista;
	}

	public void setLista(List<Libro> lista) {
		this.lista = lista;
	}

	public int getCategoriaSelecionado() {
		return CategoriaSelecionado;
	}

	public void setCategoriaSelecionado(int categoriaSelecionado) {
		CategoriaSelecionado = categoriaSelecionado;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public int getAutorSelecionado() {
		return AutorSelecionado;
	}

	public void setAutorSelecionado(int autorSelecionado) {
		AutorSelecionado = autorSelecionado;
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

	public boolean isParaEliminar() {
		return paraEliminar;
	}

	public void setParaEliminar(boolean paraEliminar) {
		this.paraEliminar = paraEliminar;
	}

	@PostConstruct
	public void Listar() {
		this.lista = libserv.recuperarTodos();
	}
	
	public void Seleccionar() {
		AutorSelecionado = libro.getAutor().getCodigo();
		CategoriaSelecionado = libro.getCategoria().getId();
		this.paraActualizar=false;
		this.paraIngresar=true;
		this.paraEliminar=true;
	}
	public void insertar() {
		
		this.autor=autserv.buscarPorId(getAutorSelecionado());
		this.categoria=catserv.buscarPorId(getCategoriaSelecionado());
		
		this.libro.setCategoria(getCategoria());
		this.libro.setAutor(getAutor());
				
		libserv.ingresarEmpleados(getLibro());
		this.libro= new Libro();		
		this.Listar();
		FacesMessage mensaje = new FacesMessage(
				FacesMessage.SEVERITY_INFO, "Libro ingresado con éxito", null);
		FacesContext.getCurrentInstance().addMessage(null, mensaje);

	}
	
	public void actualizar(){
		try {
			libserv.actualizarEmpleados(getLibro());
			this.libro= new Libro();
		
			FacesMessage mensaje = new FacesMessage(
					FacesMessage.SEVERITY_INFO, "Sus cambios fueron guardados", null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FacesMessage mensaje = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje);
		}
		this.Listar();
		this.paraActualizar=true;
		this.paraIngresar=false;
	}
	
	public void eliminar(){
		try {
			libserv.eliminarEmpleados(libro);
						
			RequestContext.getCurrentInstance().execute("PF('dialog').hide();");
			FacesMessage mensaje = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Libro Eliminado", null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje);
			this.libro = new Libro();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FacesMessage mensaje = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje);
		}
		this.paraActualizar=true;
		this.paraIngresar=false;
	}

	
}
