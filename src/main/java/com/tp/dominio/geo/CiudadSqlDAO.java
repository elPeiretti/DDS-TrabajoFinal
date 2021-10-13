package com.tp.dominio.geo;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.tp.hibernate.HibernateUtil;

public class CiudadSqlDAO implements CiudadDAO {

	@Override
	public List<Ciudad> getByProvincia(Integer idProvincia) {
		List<Ciudad> resultado;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		TypedQuery<Ciudad> hqlQuery = session.createQuery("SELECT c FROM Ciudad c WHERE c.provincia.idProvincia = :id");
		
		hqlQuery.setParameter("id", idProvincia);
		
		resultado = hqlQuery.getResultList();
		session.close();
		
		return resultado;
	}

}
