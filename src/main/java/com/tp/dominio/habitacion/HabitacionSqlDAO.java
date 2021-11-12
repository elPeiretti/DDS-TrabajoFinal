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

}
