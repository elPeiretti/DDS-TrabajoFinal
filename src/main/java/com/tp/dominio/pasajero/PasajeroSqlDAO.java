package com.tp.dominio.pasajero;

import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.tp.hibernate.HibernateUtil;

public class PasajeroSqlDAO implements PasajeroDAO {

	@Override
	public List<Pasajero> getPasajerosByCriteria(Map<String, String> criterios, Integer li, Integer cant) {
		
		List<Pasajero> resultado;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		String sqlStatement = "SELECT p FROM Pasajero p WHERE true = true ";
		
		if(criterios.containsKey("nombres")) {
			sqlStatement += "AND p.nombres LIKE :nombres ";
		} 
		
		if(criterios.containsKey("apellido")) {
			sqlStatement += "AND p.apellido LIKE :apellido ";
		} 
		
		if(criterios.containsKey("documento")) {
			sqlStatement += "AND p.id_tipo_documento = :id_tipo AND p.nro_documento = :documento ";
		} 
		
		TypedQuery<Pasajero> hqlQuery = session.createQuery(sqlStatement);
		
		if(criterios.containsKey("nombres")) {
			hqlQuery.setParameter("nombres", criterios.get("nombres")+"%");
		} 
		
		if(criterios.containsKey("apellido")) {
			hqlQuery.setParameter("apellido", criterios.get("apellidos")+"%");
		} 
		
		if(criterios.containsKey("documento")) {
			hqlQuery.setParameter("id_tipo", criterios.get("tipo_documento"));
			hqlQuery.setParameter("documento", criterios.get("documento"));
		} 
		
		hqlQuery.setFirstResult(li);
		hqlQuery.setMaxResults(cant);
		
		resultado = hqlQuery.getResultList();
		
		session.close();
		
		return resultado;
	}

	@Override
	public Long getCountPasajerosByCriteria(Map<String, String> criterios) {
		
		Long resultado;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		String sqlStatement = "SELECT count(p) FROM Pasajero p WHERE true = true ";
		
		if(criterios.containsKey("nombres")) {
			sqlStatement += "AND p.nombres LIKE :nombres ";
		} 
		
		if(criterios.containsKey("apellido")) {
			sqlStatement += "AND p.apellido LIKE :apellido ";
		} 
		
		if(criterios.containsKey("documento")) {
			sqlStatement += "AND p.id_tipo_documento = :id_tipo AND p.nro_documento = :documento ";
		} 
		
		TypedQuery<Long> hqlQuery = session.createQuery(sqlStatement);
		
		if(criterios.containsKey("nombres")) {
			hqlQuery.setParameter("nombres", criterios.get("nombres")+"%");
		} 
		
		if(criterios.containsKey("apellido")) {
			hqlQuery.setParameter("apellido", criterios.get("apellidos")+"%");
		} 
		
		if(criterios.containsKey("documento")) {
			hqlQuery.setParameter("id_tipo", criterios.get("tipo_documento"));
			hqlQuery.setParameter("documento", criterios.get("documento"));
		} 
		
		resultado = hqlQuery.getSingleResult();
		
		session.close();
		
		return resultado;
	}

}
