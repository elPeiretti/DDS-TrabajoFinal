package com.tp.dominio.ocupacion;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tp.dominio.reserva.Reserva;
import com.tp.hibernate.HibernateUtil;
import com.tp.interfaces.misc.Mensaje;

public class OcupacionSqlDAO implements OcupacionDAO {

	@Override
	public List<Ocupacion> getOcupacionesInRange(LocalDate fecha_desde, LocalDate fecha_hasta) {
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
		List<Ocupacion> aux = hqlQuery.getResultList();
		resultado = aux == null ? null : aux.get(0);
		session.close();
		return resultado;
	}
	public void insertarOcupacionyCancelarReservas(Ocupacion ocupacion, List<Reserva> reservas) {
		Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
        	tx = session.beginTransaction();
        	session.saveOrUpdate(ocupacion);
        	for(Reserva r : reservas) session.saveOrUpdate(r);
        	tx.commit();
        }
        catch(HibernateException e) {
        	if (tx!=null) 
        		tx.rollback();
        	e.printStackTrace();
			Mensaje.mensajeError(new String[]{"No se ha podido cargar la ocupacion en la base de datos."});
        }
        finally {
        	session.close();
        }
	}

}
