package com.ejb.controladores;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.ejb.entidades.Rol;
import com.ejb.servicios.RolServicio;

@ManagedBean
@SessionScoped
public class RolControlador {
	
	@EJB
	private RolServicio servicioRol;
	private Rol rol;
	private List<Rol> listaRol;
	
	//constructor
	public RolControlador(){
		super();
		this.rol = new Rol();
	}
	
	//PostConstructor
	@PostConstruct
	public void Listar() {
		this.listaRol = servicioRol.recuperarTodos();
	}
	
	//Metodos
		public void insertar() {

			servicioRol.ingresarRol(getRol());
			this.rol= new Rol();		
			this.Listar();
			FacesMessage mensaje = new FacesMessage(
					FacesMessage.SEVERITY_INFO, "Rol ingresado con éxito", null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje);

		}
		
		public void actualizar(){
			try {
				servicioRol.actualizarRol(getRol());
				this.rol= new Rol();
			
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
		}
	
	//getters and setters
	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public List<Rol> getListaRol() {
		return listaRol;
	}

	public void setListaRol(List<Rol> listaRol) {
		this.listaRol = listaRol;
	}

	public RolServicio getServicioRol() {
		return servicioRol;
	}

	public void setServicioRol(RolServicio servicioRol) {
		this.servicioRol = servicioRol;
	}
	
	
}
