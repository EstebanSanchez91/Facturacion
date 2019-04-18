package com.ejb.controladores;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.ejb.entidades.Nacionalidad;
import com.ejb.servicios.NacionalidadServicio;

@ManagedBean
@SessionScoped
public class NacionalidadControlador {
	
	@EJB
	private NacionalidadServicio servNac;
	
	private Nacionalidad nac;
	private List<Nacionalidad> listaNac;
	
	//constructor
	public NacionalidadControlador (){
		super();
		this.nac = new Nacionalidad();
	}
	
	//PostCOnstructor
	@PostConstruct
	public void Listar (){
		this.listaNac = servNac.recuperarTodos();
	}
	
	
	//Metodos
	public void insertar() {

		servNac.ingresarNacionalidad(getNac());
		this.nac= new Nacionalidad();		
		this.Listar();
		FacesMessage mensaje = new FacesMessage(
				FacesMessage.SEVERITY_INFO, "Nacionalidad ingresado con éxito", null);
		FacesContext.getCurrentInstance().addMessage(null, mensaje);

	}
	
	public void actualizar(){
		try {
			servNac.actualizarNacionalidad(getNac());
			this.nac= new Nacionalidad();
		
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
	public NacionalidadServicio getServNac() {
		return servNac;
	}
	public void setServNac(NacionalidadServicio servNac) {
		this.servNac = servNac;
	}
	public Nacionalidad getNac() {
		return nac;
	}
	public void setNac(Nacionalidad nac) {
		this.nac = nac;
	}
	public List<Nacionalidad> getListaNac() {
		return listaNac;
	}
	public void setListaNac(List<Nacionalidad> listaNac) {
		this.listaNac = listaNac;
	}
	
	
}
