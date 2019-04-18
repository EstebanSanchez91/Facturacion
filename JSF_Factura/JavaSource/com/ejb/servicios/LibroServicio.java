package com.ejb.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import com.ejb.entidades.Libro;



@Stateless
public class LibroServicio {
	@PersistenceContext
	protected EntityManager em;
	protected static Logger LOG= Logger.getLogger(LibroServicio.class);
	
	public void ingresarEmpleados(Libro libro){
		LOG.debug("Ingresando libro" +libro);
		em.persist(libro);
	}
	
	public void actualizarEmpleados(Libro libro){
		LOG.debug("Actualizando libro" +libro);
		em.merge(libro);
	}
	public void eliminarEmpleados(Libro libro){
		LOG.debug("Eliminando libro" +libro);
		em.remove(em.merge(libro));
	}
	public Libro buscarPorId(Integer id){
		LOG.debug("Buscando Entidad por id "+id );
		return em.find(Libro.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Libro> recuperarTodos(){
		Query query =em.createQuery("SELECT l FROM Libro l");
		List<Libro> libros=query.getResultList();
		return libros;
	}
}
