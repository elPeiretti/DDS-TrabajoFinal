package com.tp.persistencia.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.tp.logica.dominio.Provincia;
import com.tp.persistencia.hibernate.HibernateUtil;

public class ProvinciaSqlDAO implements ProvinciaDAO {

	@Override
	public List<Provincia> getByPais(Integer idPais) {
		List<Provincia> resultado;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		@SuppressWarnings("unchecked")
		TypedQuery<Provincia> hqlQuery = session.createQuery("SELECT p FROM Provincia p WHERE p.pais.idPais = :id");
		
		hqlQuery.setParameter("id", idPais);
		
		resultado = hqlQuery.getResultList();
		session.close();
		
		return resultado;
	}

}
