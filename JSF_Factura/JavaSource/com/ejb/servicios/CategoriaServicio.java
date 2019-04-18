package com.ejb.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import com.ejb.entidades.Categoria;

@Stateless
public class CategoriaServicio {

	@PersistenceContext
	protected EntityManager em; // utilizar los metodos para reralizar los
								// metodos que interactuan con la base.
	protected static Logger LOG = Logger.getLogger(AutorServicio.class);

	// Metodos
	public void ingresarautors(Categoria categoria) {
		LOG.debug("Ingresando autor >>" + categoria);
		em.persist(categoria);
	}

	public void actualizar(Categoria categoria) {
		LOG.debug("Actualizando autor >>" + categoria);
		em.merge(categoria);
	}

	public void eliminar(Categoria categoria) {
		LOG.debug("Eliminando autor >>" + categoria);
		em.remove(em.merge(categoria));
		em.flush();
	}

	public Categoria buscarPorId(Integer id) {
		LOG.debug("Buscando Entidad con id >>" + id);
		return em.find(Categoria.class, id);
	}

	public List<Categoria> recuperarTodo(Categoria categoria) {
		Query query = em.createQuery("SELECT c FROM Categoria c");
		@SuppressWarnings("unchecked")
		List<Categoria> categorias = query.getResultList();
		return categorias;
	}

}
