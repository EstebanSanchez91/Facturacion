package com.ejb.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import com.ejb.entidades.Autor;

@Stateless
public class AutorServicio {

	@PersistenceContext
	protected EntityManager em; // utilizar los metodos para reralizar los
								// metodos que interactuan con la base.
	protected static Logger LOG = Logger.getLogger(AutorServicio.class);

	// Metodos
	public void ingresarAutores(Autor autor) {
		LOG.debug("Ingresando autor >>" + autor);
		em.persist(autor);
	}

	public void actualizar(Autor autor) {
		LOG.debug("Actualizando autor >>" + autor);
		em.merge(autor);
	}

	public void eliminar(Autor autor) {
		LOG.debug("Eliminando autor >>" + autor);
		em.remove(em.merge(autor));
		em.flush();
	}

	public Autor buscarPorId(Integer id) {
		LOG.debug("Buscando Entidad con id >>" + id);
		return em.find(Autor.class, id);
	}

	public List<Autor> recuperarTodo(Autor autor) {
		Query query = em.createQuery("SELECT c FROM Autor c");
		@SuppressWarnings("unchecked")
		List<Autor> autores = query.getResultList();
		return autores;
	}
}
