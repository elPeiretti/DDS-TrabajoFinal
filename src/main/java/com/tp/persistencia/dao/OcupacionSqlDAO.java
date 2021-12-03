package com.tp.persistencia.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tp.logica.dominio.Ocupacion;
import com.tp.logica.dominio.Reserva;
import com.tp.logica.excepciones.NuevaOcupacionException;
import com.tp.persistencia.hibernate.HibernateUtil;

public class OcupacionSqlDAO implements OcupacionDAO {

	@Override
	public List<Ocupacion> getOcupacionesInRange(LocalDate fecha_desde, LocalDate fecha_hasta) {
		List<Ocupacion> resultado;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		String sqlStatement = "SELECT o FROM Ocupacion o WHERE (o.fechaIngreso BETWEEN :fecha_desde AND :fecha_hasta) "
				+ "OR (o.fechaEgreso BETWEEN :fecha_desde AND :fecha_hasta) OR (:fecha_desde > o.fechaIngreso AND :fecha_hasta < o.fechaEgreso)";	

		@SuppressWarnings("unchecked")
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
		String sqlStatement = "SELECT o FROM Ocupacion o LEFT JOIN FETCH o.acompaniantes WHERE o.habitacion.numero = :numero AND o.fechaEgreso = "
				+ "(SELECT MAX(oc.fechaEgreso) FROM Ocupacion oc WHERE oc.habitacion.numero = :numero)";

		@SuppressWarnings("unchecked")
		TypedQuery<Ocupacion> hqlQuery = session.createQuery(sqlStatement); 
		
		hqlQuery.setParameter("numero", habitacion);
		List<Ocupacion> aux = hqlQuery.getResultList();
		resultado = aux.size()==0 ? null : aux.get(0);
		session.close();
		return resultado;
	}
	public void insertarOcupacionyCancelarReservas(Ocupacion ocupacion, List<Reserva> reservas) throws NuevaOcupacionException {
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
        	throw new NuevaOcupacionException();
        }
        finally {
        	session.close();
        }
	}

	@Override
	public Ocupacion getOcupacionById(Integer idOcupacion) {
		Ocupacion resultado;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		String sqlStatement = "SELECT o FROM Ocupacion o WHERE o.idOcupacion = :id";	

		@SuppressWarnings("unchecked")
		TypedQuery<Ocupacion> hqlQuery = session.createQuery(sqlStatement); 
		
		hqlQuery.setParameter("id", idOcupacion);
		List<Ocupacion> aux = hqlQuery.getResultList();
		resultado = aux == null ? null : aux.get(0);
		session.close();
		return resultado;
	}

	@Override
	public void insertarOcupacion(Ocupacion ocup){
		Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
        	tx = session.beginTransaction();
        	session.saveOrUpdate(ocup);
        	tx.commit();
        }
        catch(HibernateException e) {
        	if (tx!=null) 
        		tx.rollback();
        	e.printStackTrace();
        }
        finally {
        	session.close();
        }
	}
}
