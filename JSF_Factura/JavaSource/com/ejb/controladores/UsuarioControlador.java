package com.ejb.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.ejb.entidades.Rol;
import com.ejb.entidades.Usuario;
import com.ejb.servicios.RolServicio;
import com.ejb.servicios.UsuarioServicio;

@ManagedBean
@SessionScoped
public class UsuarioControlador {
	
	@EJB
	private UsuarioServicio serUsuario;
	
	@EJB
	private RolServicio serRol;
	
	private Usuario usuario;
	private List<Usuario> listaUsuario = new ArrayList<Usuario>();
	private List<Rol> listaRoles;
	private int rolRecuperado;
	private boolean paraIngresar;
	private boolean paraActualizar;
	private boolean paraEliminar;
	
	//constructor
	public UsuarioControlador (){
		super();
		this.usuario = new Usuario();
	}
	
	//Postconstructor
	@PostConstruct
	public void Listar(){
		listaUsuario = serUsuario.recuperarTodos();
		listaRoles = serRol.recuperarTodos();
		this.paraIngresar = false;
		this.paraActualizar = true;
		this.paraEliminar = true;
	}
	
	//metodos
	public void insertar() {
		usuario.setRol(serRol.buscarPorId(rolRecuperado));
		serUsuario.ingresarUsuario(getUsuario());
		this.usuario = new Usuario();
		this.Listar();
	}
		
	public void Seleccionar() {

		this.paraActualizar = false;
		this.paraIngresar = true;
		this.paraEliminar = true;
	}

	public void actualizar() {
		try {
			usuario.setRol(serRol.buscarPorId(rolRecuperado));
			serUsuario.actualizarUsuario(getUsuario());
			this.usuario = new Usuario();
			FacesMessage mensaje = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Sus cambios fueron guardados", null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FacesMessage mensaje = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje);
		}
		this.paraActualizar = true;
		this.paraIngresar = false;
		this.Listar();
	}
	
	//getters and setters
	public UsuarioServicio getSerUsuario() {
		return serUsuario;
	}
	public void setSerUsuario(UsuarioServicio serUsuario) {
		this.serUsuario = serUsuario;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}
	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}
	public List<Rol> getListaRoles() {
		return listaRoles;
	}
	public void setListaRoles(List<Rol> listaRoles) {
		this.listaRoles = listaRoles;
	}
	public int getRolRecuperado() {
		return rolRecuperado;
	}
	public void setRolRecuperado(int rolRecuperado) {
		this.rolRecuperado = rolRecuperado;
	}


	public RolServicio getSerRol() {
		return serRol;
	}


	public void setSerRol(RolServicio serRol) {
		this.serRol = serRol;
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
	
	
	
}
