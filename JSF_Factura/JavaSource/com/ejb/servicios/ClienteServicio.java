package com.ejb.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import com.ejb.entidades.Cliente;

@Stateless
public class ClienteServicio {
	@PersistenceContext
	protected EntityManager em; // utilizar los metodos para reralizar los
								// metodos que interactuan con la base.
	protected static Logger LOG = Logger.getLogger(AutorServicio.class);

	// Metodos
	public void ingresarautors(Cliente cliente) {
		LOG.debug("Ingresando autor >>" + cliente);
		em.persist(cliente);
	}

	public void actualizar(Cliente cliente) {
		LOG.debug("Actualizando autor >>" + cliente);
		em.merge(cliente);
	}

	public void eliminar(Cliente cliente) {
		LOG.debug("Eliminando autor >>" + cliente);
		em.remove(em.merge(cliente));
		em.flush();
	}

	public Cliente buscarPorId(Integer id) {
		LOG.debug("Buscando Entidad con id >>" + id);
		return em.find(Cliente.class, id);
	}

	public List<Cliente> recuperarTodo(Cliente cliente) {
		Query query = em.createQuery("SELECT c FROM Cliente c");
		@SuppressWarnings("unchecked")
		List<Cliente> clientes = query.getResultList();
		return clientes;
	}

}
