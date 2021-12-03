package com.tp.persistencia.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.tp.logica.dominio.Ciudad;
import com.tp.persistencia.hibernate.HibernateUtil;

public class CiudadSqlDAO implements CiudadDAO {

	@Override
	public List<Ciudad> getByProvincia(Integer idProvincia) {
		List<Ciudad> resultado;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		@SuppressWarnings("unchecked")
		TypedQuery<Ciudad> hqlQuery = session.createQuery("SELECT c FROM Ciudad c WHERE c.provincia.idProvincia = :id");
		
		hqlQuery.setParameter("id", idProvincia);
		
		resultado = hqlQuery.getResultList();
		session.close();
		
		return resultado;
	}

	@Override
	public Ciudad getById(Integer idCiudad) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		TypedQuery<Ciudad> hqlQuery = session.createQuery("SELECT c FROM Ciudad c WHERE c.idCiudad = :id");
		
		hqlQuery.setParameter("id", idCiudad);
		Ciudad resultado = hqlQuery.getSingleResult();
		session.close();
		return resultado;
	}

}
