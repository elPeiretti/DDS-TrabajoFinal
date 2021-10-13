package com.tp.dominio.geo;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.tp.dominio.pasajero.TipoDocumento;
import com.tp.hibernate.HibernateUtil;

public class PaisSqlDAO implements PaisDAO {

	@Override
	public List<Pais> getAll() {
		
		List<Pais> resultado;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		TypedQuery<Pais> hqlQuery = session.createQuery("SELECT p FROM Pais p");
		resultado = hqlQuery.getResultList();
		
		session.close();
		
		return resultado;
		
	}

}
