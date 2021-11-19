package com.tp.dominio.ocupacion;

import java.time.Instant;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.tp.dominio.reserva.Reserva;
import com.tp.hibernate.HibernateUtil;

public class OcupacionSqlDAO implements OcupacionDAO {

	@Override
	public List<Ocupacion> getOcupacionesInRange(Instant fecha_desde, Instant fecha_hasta) {
		List<Ocupacion> resultado;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		String sqlStatement = "SELECT o FROM Ocupacion o WHERE (o.fechaIngreso BETWEEN :fecha_desde AND :fecha_hasta) "
				+ "OR (o.fechaEgreso BETWEEN :fecha_desde AND :fecha_hasta) OR (:fecha_desde > o.fechaIngreso AND :fecha_hasta < o.fechaEgreso)";	

		TypedQuery<Ocupacion> hqlQuery = session.createQuery(sqlStatement); 
		
		hqlQuery.setParameter("fecha_desde", fecha_desde);
		hqlQuery.setParameter("fecha_hasta", fecha_hasta);
		
		resultado = hqlQuery.getResultList();
		session.close();
		return resultado;
	}

	@Override
	public Ocupacion getUltimaOcupacion(String habitacion) {
		Ocupacion resultado;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		String sqlStatement = "SELECT o FROM Ocupacion o WHERE o.fechaEgreso = "
				+ "(SELECT MAX(oc.fechaEgreso) FROM Ocupacion oc WHERE oc.habitacion = "
				+ "(SELECT h.idHabitacion FROM Habitacion h WHERE h.numero = :numero))";	

		TypedQuery<Ocupacion> hqlQuery = session.createQuery(sqlStatement); 
		
		hqlQuery.setParameter("numero", habitacion);
		resultado = hqlQuery.getSingleResult();
		
		session.close();
		return resultado;
	}

}
