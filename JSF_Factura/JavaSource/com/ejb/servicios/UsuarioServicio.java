package com.ejb.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import com.ejb.entidades.Usuario;

@Stateless
public class UsuarioServicio {
	
	@PersistenceContext
	protected EntityManager em;
	protected static Logger LOG= Logger.getLogger(UsuarioServicio.class);
	
	public void ingresarUsuario(Usuario user){
		LOG.debug("Ingresando usuario" +user);
		em.persist(user);
	}
	
	public void actualizarUsuario(Usuario user){
		LOG.debug("Actualizando user" +user);
		em.merge(user);
	}
	public void eliminarUsuario(Usuario user){
		LOG.debug("Eliminando usuario" +user);
		em.remove(em.merge(user));
	}
	public Usuario buscarPorId(Integer id){
		LOG.debug("Buscando Entidad por id "+id );
		return em.find(Usuario.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> recuperarTodos(){
		Query query =em.createQuery("SELECT u FROM Usuario u");
		List<Usuario> user=query.getResultList();
		return user;
	}
}
