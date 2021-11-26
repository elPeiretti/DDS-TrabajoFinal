package com.tp.dominio.habitacion;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tp.dominio.pasajero.TipoDocumento;
import com.tp.hibernate.HibernateUtil;
import com.tp.interfaces.misc.Mensaje;

public class HabitacionSqlDAO implements HabitacionDAO {

	@Override
	public List<Habitacion> getAllHabitaciones() {

		List<Habitacion> resultado;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		TypedQuery<Habitacion> hqlQuery = session.createQuery("SELECT h FROM Habitacion h");
		resultado = hqlQuery.getResultList();
		
		session.close();
		
		return resultado;		
		
	}

	@Override
	public Habitacion getHabitacionByNumero(String numero) {
		Habitacion resultado;
		List<Habitacion> r;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		TypedQuery<Habitacion> hqlQuery = session.createQuery("SELECT h FROM Habitacion h WHERE h.numero = :numero");
		hqlQuery.setParameter("numero", numero);
		r = hqlQuery.getResultList();
		resultado = r.size()==0? null : r.get(0);
		session.close();
		return resultado;
	}

	@Override
	public void insertarHabitacion(Habitacion hab) {
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
			Mensaje.mensajeError(new String[]{"No se ha podido cargar el pasajero en la base de datos."});
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
		
		TypedQuery<Habitacion> hqlQuery = session.createQuery("SELECT h FROM Habitacion h WHERE h.idHabitacion = :id");
		hqlQuery.setParameter("id", id);
		r = hqlQuery.getResultList();
		resultado = r.size()==0? null : r.get(0);
		
		TypedQuery<CostoPorNoche> hqlQuery2 = session.createQuery("SELECT c FROM CostoPorNoche AS c WHERE c IN "
				+ "(SELECT t.costo FROM TipoHabitacion AS t JOIN Habitacion AS h WHERE h.idHabitacion = :id) "
				+ "AND :fecha BETWEEN c.FechaInicioVigencia AND c.FechaFinVigencia");
		
		hqlQuery2.setParameter("id", id);
		hqlQuery2.setParameter("fecha", fechaIngreso);
		List<CostoPorNoche> aux = hqlQuery2.getResultList();
		if(aux.size() != 0) {
			resultado.getTipoHabitacion().setCosto(List.of(aux.get(0)));
		}else {
			resultado = null;
		}
		session.close();
		return resultado;
	}

}
