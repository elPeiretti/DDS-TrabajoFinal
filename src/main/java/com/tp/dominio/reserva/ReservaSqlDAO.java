package com.tp.dominio.reserva;

import java.time.Instant;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.tp.dominio.pasajero.Pasajero;
import com.tp.hibernate.HibernateUtil;

public class ReservaSqlDAO implements ReservaDAO {

	@Override
	public List<Reserva> getReservasInRange(Instant fecha_desde, Instant fecha_hasta) {
		List<Reserva> resultado;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		String sqlStatement = "SELECT r FROM Reserva r WHERE (r.fechaIngreso BETWEEN :fecha_desde AND :fecha_hasta) "
				+ "OR (r.fechaEgreso BETWEEN :fecha_desde AND :fecha_hasta) OR (:fecha_desde > r.fechaIngreso AND :fecha_hasta < r.fechaEgreso)";	

		TypedQuery<Reserva> hqlQuery = session.createQuery(sqlStatement); 
		
		hqlQuery.setParameter("fecha_desde", fecha_desde);
		hqlQuery.setParameter("fecha_hasta", fecha_hasta);
		
		resultado = hqlQuery.getResultList();
		session.close();
		return resultado;
	}
	
}
