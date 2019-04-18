package com.ejb.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.logging.Logger;


import com.ejb.entidades.Factura;

@Stateless
public class FacturaServicio {

	@PersistenceContext
	protected EntityManager em;
	protected static Logger LOG= Logger.getLogger(FacturaServicio.class);
	
	public void ingresarFacturas(Factura factura) {
		LOG.debug("Ingresando factura >>" + factura);
		em.persist(factura);
	}

	public void actualizar(Factura factura){
		LOG.debug("Actualizando factura >>" + factura);
		em.merge(factura);
	}

	public void eliminar(Factura factura) {
		LOG.debug("Eliminando factura >>" + factura);
		em.remove(em.merge(factura));
		em.flush();
	}

	public Factura buscarPorId(Integer id) {
		LOG.debug("Buscando Entidad con id >>" + id);
		return em.find(Factura.class, id);
	}

	public List<Factura> recuperarTodo() {
		Query query = em.createQuery("SELECT f FROM Factura f");
		@SuppressWarnings("unchecked")
		List<Factura> facturas = query.getResultList();
		return facturas;
	}
	
}
