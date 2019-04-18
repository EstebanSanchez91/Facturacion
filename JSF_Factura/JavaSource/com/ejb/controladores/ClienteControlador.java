package com.ejb.controladores;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.ejb.entidades.Cliente;
import com.ejb.entidades.Nacionalidad;
import com.ejb.servicios.ClienteServicio;
import com.ejb.servicios.NacionalidadServicio;
@ManagedBean
@ViewScoped
public class ClienteControlador {
	@EJB
	private ClienteServicio serCliente;
	@EJB
	private NacionalidadServicio serNacionalidad;
	private List<Cliente> clientesRecuperados;
	private List<Nacionalidad> nacionalidadesRecuperadas;
	private int idCliente;
	private Cliente cliente;
	private boolean paraIngresar;
	private boolean paraActualizar;
	private int idNacionalidadSeleccionada; // id de dropdown

	// Constructor
	public ClienteControlador() {
		cliente = new Cliente();
		paraIngresar = false;
		paraActualizar = true;

	}

	// para evitar null pointer exception.. postContruct obliga al compilador a
	// leer este metodo primero.
	@PostConstruct
	public void ejecutar() {
		clientesRecuperados = serCliente.recuperarTodo(cliente);
		nacionalidadesRecuperadas = serNacionalidad.recuperarTodos();
	}

	public void ingresarCliente() {
		try {
			cliente.setNacionalidad(serNacionalidad.buscarPorId(idNacionalidadSeleccionada));
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Autor Ingresado Exitosamente", null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje);
		} catch (Exception e) {
			FacesMessage mensaje2 = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje2);
		}
		cliente = new Cliente(); // Limpiar formulario
		ejecutar(); // parar que se refresque la tabla
		paraIngresar = false;
		paraActualizar = true;
	}

	public void actualizarCliente() {
		try {
			serCliente.actualizar(cliente);
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Autor Actualizado Existosamente", null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje);
		} catch (Exception e) {
			FacesMessage mensaje2 = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje2);
		}

		cliente = new Cliente(); // Limpiar formulario
		ejecutar(); // para refrescar tabla
		paraIngresar = false;
		paraActualizar = true;
	}

	public void seleccionarCliente() {
		idCliente = serCliente.buscarPorId(cliente.getCodigoC()).getCodigoC();
		paraIngresar = true;
		paraActualizar = false;
	}

	public void eliminarCliente() {
		try {
			serCliente.eliminar(cliente);
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Autor Eliminado Exitosamente", null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje);
		} catch (Exception e) {
			FacesMessage mensaje2 = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje2);
		}
		cliente = new Cliente(); // Limpiar
		ejecutar(); // refrescar
	}

	public List<Cliente> getClientesRecuperados() {
		return clientesRecuperados;
	}

	public void setClientesRecuperados(List<Cliente> clientesRecuperados) {
		this.clientesRecuperados = clientesRecuperados;
	}

	public List<Nacionalidad> getNacionalidadesRecuperadas() {
		return nacionalidadesRecuperadas;
	}

	public void setNacionalidadesRecuperadas(
			List<Nacionalidad> nacionalidadesRecuperadas) {
		this.nacionalidadesRecuperadas = nacionalidadesRecuperadas;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
