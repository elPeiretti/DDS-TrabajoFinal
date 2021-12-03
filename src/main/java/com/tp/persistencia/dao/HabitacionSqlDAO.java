package com.tp.persistencia.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tp.logica.dominio.Habitacion;
import com.tp.logica.excepciones.NuevaHabitacionException;
import com.tp.persistencia.hibernate.HibernateUtil;

public class HabitacionSqlDAO implements HabitacionDAO {

	@Override
	public List<Habitacion> getAllHabitaciones() {

		List<Habitacion> resultado;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		@SuppressWarnings("unchecked")
		TypedQuery<Habitacion> hqlQuery = session.createQuery("SELECT h FROM Habitacion h ");
		resultado = hqlQuery.getResultList();
		
		session.close();
		
		return resultado;		
		
	}

	@Override
	public Habitacion getHabitacionByNumero(String numero) {
		Habitacion resultado;
		List<Habitacion> r;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		@SuppressWarnings("unchecked")
		TypedQuery<Habitacion> hqlQuery = session.createQuery("SELECT h FROM Habitacion h WHERE h.numero = :numero");
		hqlQuery.setParameter("numero", numero);
		r = hqlQuery.getResultList();
		resultado = r.size()==0? null : r.get(0);
		session.close();
		return resultado;
	}

	@Override
	public void insertarHabitacion(Habitacion hab) throws NuevaHabitacionException {
		Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
        	tx = session.beginTransaction();
        	session.saveOrUpdate(hab);
        	tx.commit();
        }
        catch(HibernateException e) {
        	if (tx!=null) 
        		tx.rollback();
        	e.printStackTrace();
        	throw new NuevaHabitacionException();

        }
        finally {
        	session.close();
        }
	}

	@Override
	public Habitacion getHabitacionByIdWithCostoVigenteEn(Integer id, LocalDate fechaIngreso) {
		Habitacion resultado;
		List<Habitacion> r;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		@SuppressWarnings("unchecked")
		TypedQuery<Habitacion> hqlQuery = session.createQuery("SELECT h FROM Habitacion h JOIN FETCH h.tipo t JOIN FETCH t.costo c WHERE h.idHabitacion = :id "
				+ "AND ((:fecha BETWEEN c.fechaInicioVigencia AND c.fechaFinVigencia) "
				+ "OR (c.fechaFinVigencia IS NULL AND :fecha >= c.fechaInicioVigencia))");
		
		hqlQuery.setParameter("id", id);
		hqlQuery.setParameter("fecha", fechaIngreso);
		r = hqlQuery.getResultList();
		resultado = r.size()==0? null : r.get(0);
		Hibernate.initialize(resultado.getServicios());
		session.close();
		return resultado;
	}
	public void cargarServicios(Habitacion hab) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Hibernate.initialize(hab.getServicios());
		session.close();
	}
}
