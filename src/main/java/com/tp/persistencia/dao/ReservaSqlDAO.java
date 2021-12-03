package com.tp.persistencia.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.tp.logica.dominio.EstadoReserva;
import com.tp.logica.dominio.Reserva;
import com.tp.persistencia.hibernate.HibernateUtil;

public class ReservaSqlDAO implements ReservaDAO {

	@Override
	public List<Reserva> getReservasInRange(LocalDate fecha_desde, LocalDate fecha_hasta) {
		List<Reserva> resultado;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		String sqlStatement = "SELECT r FROM Reserva r WHERE r.estado != :estado AND ((r.fechaIngreso BETWEEN :fecha_desde AND :fecha_hasta) "
				+ "OR (r.fechaEgreso BETWEEN :fecha_desde AND :fecha_hasta) OR (:fecha_desde > r.fechaIngreso AND :fecha_hasta < r.fechaEgreso))";	

		@SuppressWarnings("unchecked")
		TypedQuery<Reserva> hqlQuery = session.createQuery(sqlStatement); 
		
		hqlQuery.setParameter("fecha_desde", fecha_desde);
		hqlQuery.setParameter("fecha_hasta", fecha_hasta);
		hqlQuery.setParameter("estado", EstadoReserva.CANCELADA);
		
		resultado = hqlQuery.getResultList();
		session.close();
		return resultado;
	}

	@Override
	public List<Reserva> getReservasInRange(LocalDate fecha_desde, LocalDate fecha_hasta, String numeroHabitacion) {
		List<Reserva> resultado;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		String sqlStatement = "SELECT r FROM Reserva r WHERE r.habitacion.numero = :numero AND r.estado != :estado AND ((r.fechaIngreso BETWEEN :fecha_desde AND :fecha_hasta) "
				+ "OR (r.fechaEgreso BETWEEN :fecha_desde AND :fecha_hasta) OR (:fecha_desde > r.fechaIngreso AND :fecha_hasta < r.fechaEgreso))";	

		@SuppressWarnings("unchecked")
		TypedQuery<Reserva> hqlQuery = session.createQuery(sqlStatement); 
		
		hqlQuery.setParameter("numero", numeroHabitacion);
		hqlQuery.setParameter("fecha_desde", fecha_desde);
		hqlQuery.setParameter("fecha_hasta", fecha_hasta);
		hqlQuery.setParameter("estado", EstadoReserva.CANCELADA);
		
		resultado = hqlQuery.getResultList();
		session.close();
		return resultado;
	}
	
}
