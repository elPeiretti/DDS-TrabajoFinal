package com.tp.persistencia.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.tp.logica.dominio.Servicio;
import com.tp.persistencia.hibernate.HibernateUtil;

public class ServicioSqlDAO implements ServicioDAO {

    @Override
    public List<Servicio> getServiciosNoFacturadosByIdHabitacion(Integer idHabitacion) {
        List<Servicio> resultado;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		@SuppressWarnings("unchecked")
		TypedQuery<Servicio> hqlQuery = session.createQuery("SELECT s FROM Habitacion h JOIN h.servicios s WHERE h.idHabitacion = :id AND s.cantidadPagada < s.cantidad ORDER BY s.descripcion ASC");
		hqlQuery.setParameter("id", idHabitacion);

		resultado = hqlQuery.getResultList();
		session.close();
		return resultado;
    }

	@Override
	public Long getCountServiciosNoFacturadosByIdHabitacion(Integer idHabitacion) {
		Long resultado;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		@SuppressWarnings("unchecked")
		TypedQuery<Long> hqlQuery = session.createQuery("SELECT COUNT(s) FROM Habitacion h JOIN h.servicios s WHERE h.idHabitacion = :id AND s.cantidadPagada < s.cantidad");
		hqlQuery.setParameter("id", idHabitacion);
		
		resultado = hqlQuery.getSingleResult();
		
		session.close();
		
		return resultado;
	}

	@Override
	public Servicio getServicioById(Integer idServicio) {
		Servicio resultado;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		String sqlStatement = "SELECT s FROM Servicio s WHERE s.idServicio = :id ";
	
		@SuppressWarnings("unchecked")
		TypedQuery<Servicio> hqlQuery = session.createQuery(sqlStatement);
		
		hqlQuery.setParameter("id", idServicio);
			
		resultado = hqlQuery.getSingleResult();
		
		session.close();
		
		return resultado;
	}

}
