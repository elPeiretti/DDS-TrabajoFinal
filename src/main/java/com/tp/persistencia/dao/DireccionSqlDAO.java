package com.tp.persistencia.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tp.logica.dominio.Direccion;
import com.tp.logica.excepciones.InsertDireccionException;
import com.tp.persistencia.hibernate.HibernateUtil;

public class DireccionSqlDAO implements DireccionDAO {

	@Override
	public void insertarDireccion(Direccion direccion) throws InsertDireccionException {
		Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
        	tx = session.beginTransaction();
        	session.saveOrUpdate(direccion);
        	tx.commit();
        }
        catch(HibernateException e) {
        	if (tx!=null) 
        		tx.rollback();
        	e.printStackTrace();
        	throw new InsertDireccionException();
        }
        finally {
        	session.close();
        }
	}

}
