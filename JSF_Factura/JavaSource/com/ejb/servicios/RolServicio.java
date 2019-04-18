package com.ejb.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import com.ejb.entidades.Rol;

@Stateless
public class RolServicio {
	
	@PersistenceContext
	protected EntityManager em;
	protected static Logger LOG= Logger.getLogger(RolServicio.class);
	
	public void ingresarRol(Rol rol){
		LOG.debug("Ingresando rol" +rol);
		em.persist(rol);
	}
	
	public void actualizarRol(Rol rol){
		LOG.debug("Actualizando rol" +rol);
		em.merge(rol);
	}
	public void eliminarRol(Rol rol){
		LOG.debug("Eliminando Rol" +rol);
		em.remove(em.merge(rol));
	}
	public Rol buscarPorId(Integer id){
		LOG.debug("Buscando Entidad por id "+id );
		return em.find(Rol.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Rol> recuperarTodos(){
		Query query =em.createQuery("SELECT r FROM Rol r");
		List<Rol> rol=query.getResultList();
		return rol;
	}
}
