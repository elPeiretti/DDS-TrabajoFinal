package com.tp.persistencia.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.tp.logica.dominio.PosicionIVA;
import com.tp.persistencia.hibernate.HibernateUtil;

public class PosicionIVASqlDAO implements PosicionIVADAO {

	@Override
	public List<PosicionIVA> getAll() {
		
		List<PosicionIVA> resultado;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		@SuppressWarnings("unchecked")
		TypedQuery<PosicionIVA> hqlQuery = session.createQuery("SELECT p FROM PosicionIVA p");
		
		resultado = hqlQuery.getResultList();
		
		session.close();
		
		return resultado;
	}

    public PosicionIVA getById(Integer idPosicionIVA) {
        Session session = HibernateUtil.getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		TypedQuery<PosicionIVA> hqlQuery = session.createQuery("SELECT p FROM PosicionIVA p WHERE p.idPosicionIVA = :id");

		hqlQuery.setParameter("id", idPosicionIVA);
		PosicionIVA resultado = hqlQuery.getSingleResult();
		session.close();
		return resultado;
    }

}
