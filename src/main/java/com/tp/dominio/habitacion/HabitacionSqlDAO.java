package com.tp.dominio.habitacion;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.tp.dominio.pasajero.TipoDocumento;
import com.tp.hibernate.HibernateUtil;

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

}
