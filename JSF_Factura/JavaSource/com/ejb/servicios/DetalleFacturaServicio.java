package com.ejb.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import com.ejb.entidades.DetalleFactura;

@Stateless
public class DetalleFacturaServicio {

	@PersistenceContext
	protected EntityManager em;
	protected static Logger LOG= Logger.getLogger(DetalleFacturaServicio.class);
	
	public void ingresarDetalles(DetalleFactura detfactura) {
		LOG.debug("Ingresando detalle factura >>" + detfactura);
		em.persist(detfactura);
	}

	public void actualizar(DetalleFactura detfactura){
		LOG.debug("Actualizando detalle factura >>" + detfactura);
		em.merge(detfactura);
	}

	public void eliminar(DetalleFactura detfactura) {
		LOG.debug("Eliminando detalle factura >>" + detfactura);
		em.remove(em.merge(detfactura));
		em.flush();
	}

	public DetalleFactura buscarPorId(Integer id) {
		LOG.debug("Buscando Entidad con id >>" + id);
		return em.find(DetalleFactura.class, id);
	}

	public List<DetalleFactura> recuperarTodo() {
		Query query = em.createQuery("SELECT df FROM DetalleFactura df");
		@SuppressWarnings("unchecked")
		List<DetalleFactura> detfacturas = query.getResultList();
		return detfacturas;
	}
}
