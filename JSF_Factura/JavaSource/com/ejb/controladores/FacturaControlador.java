package com.ejb.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.ejb.entidades.Cliente;
import com.ejb.entidades.DetalleFactura;
import com.ejb.entidades.Factura;
import com.ejb.entidades.Libro;
import com.ejb.servicios.ClienteServicio;
import com.ejb.servicios.DetalleFacturaServicio;
import com.ejb.servicios.FacturaServicio;
import com.ejb.servicios.LibroServicio;

@ManagedBean
@ViewScoped
public class FacturaControlador {

	@EJB
	private ClienteServicio clserv;
	
	@EJB
	private FacturaServicio factserv;
	
	@EJB
	private LibroServicio libserv;
		
	@EJB
	private DetalleFacturaServicio detserv;
	
	
	private DetalleFactura detalle;
	private List<DetalleFactura> listadetalle;
	
	private int libroSeleccionado;
	private Libro libro;
	private double subtotal;
	private double iva;
	private double total;
	
	
	private Factura factura;
	private List<Factura> listaFacturas;
	private int clienteSeleccionado;
	private Cliente cliente;
	
	
	
	public FacturaControlador() {
		super();
		this.factura= new Factura();
		this.listaFacturas=new ArrayList<Factura>();
		detalle= new DetalleFactura();
		this.listadetalle= new ArrayList<DetalleFactura>();
	}

	
	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public List<Factura> getListaFacturas() {
		return listaFacturas;
	}

	public void setListaFacturas(List<Factura> listaFacturas) {
		this.listaFacturas = listaFacturas;
	}

	public int getClienteSeleccionado() {
		return clienteSeleccionado;
	}

	public void setClienteSeleccionado(int clienteSeleccionado) {
		this.clienteSeleccionado = clienteSeleccionado;
	}
	
	
	
	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public DetalleFactura getDetalle() {
		return detalle;
	}

	public void setDetalle(DetalleFactura detalle) {
		this.detalle = detalle;
	}

	public List<DetalleFactura> getListadetalle() {
		return listadetalle;
	}

	public void setListadetalle(List<DetalleFactura> listadetalle) {
		this.listadetalle = listadetalle;
	}

	public int getLibroSeleccionado() {
		return libroSeleccionado;
	}

	public void setLibroSeleccionado(int libroSeleccionado) {
		this.libroSeleccionado = libroSeleccionado;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	@PostConstruct
	public void Listar() {
		this.listaFacturas = factserv.recuperarTodo();
	}
	
	public void insertar(){
		
		factserv.ingresarFacturas(getFactura());
		
		//this.Listar();
		FacesMessage mensaje = new FacesMessage(
				FacesMessage.SEVERITY_INFO, "Factura ingresado con éxito", null);
		FacesContext.getCurrentInstance().addMessage(null, mensaje);
		insertarDetalle();
		factura= new Factura();
		cliente=new Cliente();
		libro= new Libro();
		this.detalle.setCantidad(0);
		this.listadetalle= new ArrayList<DetalleFactura>();
		calcular();
		
	}
	
	public void buscarCliente() {
		cliente=clserv.buscarPorId(clienteSeleccionado);
		factura.setCliente(getCliente());
	}
	
	
	
	public void insertarDetalle() {
		
		
		for (DetalleFactura detalleFactura : listadetalle) {
			detalleFactura.setFactura(factura);
			detserv.ingresarDetalles(detalleFactura);
			
		}
			
		FacesMessage mensaje = new FacesMessage(
				FacesMessage.SEVERITY_INFO, "Detalle de Factura ingresado con éxito", null);
		FacesContext.getCurrentInstance().addMessage(null, mensaje);
		
	}
	
	public void agregar(){
		this.detalle.setFactura(getFactura());		
		double subt=this.detalle.getCantidad()*this.libro.getPrecio();
		this.detalle.setSubtotal(subt);
		listadetalle.add(getDetalle());
		this.detalle= new DetalleFactura();
		calcular();
		
				
	}
	
	public void calcular() {
		subtotal=0;
		iva=0;
		total=0;
		for (DetalleFactura detalleFactura : listadetalle) {
			subtotal=subtotal+detalleFactura.getSubtotal();	
		}
		iva=subtotal*0.12;
		total=subtotal+iva;
	}
	
	public void quitar() {
		listadetalle.remove(detalle);
		calcular();
		
	}
	
	public void buscarLibro(){
		
		libro=libserv.buscarPorId(libroSeleccionado);
		detalle.setLibro(getLibro());
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getIva() {
		return iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}
	
	
	
	

}
