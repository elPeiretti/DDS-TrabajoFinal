package com.tp.dominio.geo;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.tp.hibernate.HibernateUtil;

public class PaisSqlDAO implements PaisDAO {

	@Override
	public List<Pais> getAll() {
		
		List<Pais> resultado;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		@SuppressWarnings("unchecked")
		TypedQuery<Pais> hqlQuery = session.createQuery("SELECT p FROM Pais p");
		resultado = hqlQuery.getResultList();
		
		session.close();
		
		return resultado;
		
	}

	@Override
	public Pais getById(Integer nacionalidad) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		TypedQuery<Pais> hqlQuery = session.createQuery("SELECT p FROM Pais p WHERE p.idPais = :id");

		hqlQuery.setParameter("id", nacionalidad);
		Pais resultado = hqlQuery.getSingleResult();
		session.close();
		return resultado;
	}

}
