package com.ejb.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import com.ejb.entidades.Nacionalidad;

@Stateless
public class NacionalidadServicio {
	
	@PersistenceContext
	protected EntityManager em;
	protected static Logger LOG= Logger.getLogger(NacionalidadServicio.class);
	
	public void ingresarNacionalidad(Nacionalidad nacionalidad){
		LOG.debug("Ingresando Nacionalidad" +nacionalidad);
		em.persist(nacionalidad);
	}
	
	public void actualizarNacionalidad(Nacionalidad nacionalidad){
		LOG.debug("Actualizando nacionalidad" +nacionalidad);
		em.merge(nacionalidad);
	}
	public void eliminarNacionalidad(Nacionalidad nacionalidad){
		LOG.debug("Eliminando Nacionalidad" +nacionalidad);
		em.remove(em.merge(nacionalidad));
	}
	public Nacionalidad buscarPorId(Integer id){
		LOG.debug("Buscando Entidad por id "+id );
		return em.find(Nacionalidad.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Nacionalidad> recuperarTodos(){
		Query query =em.createQuery("SELECT n FROM Nacionalidad n");
		List<Nacionalidad> nacionalidad=query.getResultList();
		return nacionalidad;
	}
}
