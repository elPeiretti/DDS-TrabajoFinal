package com.tp.dominio.direccion;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tp.hibernate.HibernateUtil;
import com.tp.interfaces.misc.Mensaje;

public class DireccionSqlDAO implements DireccionDAO {

	@Override
	public void insertarDireccion(Direccion direccion) {
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
			Mensaje.mensajeError(new String[]{"No se ha podido cargar la dirección en la base de datos."});
        }
        finally {
        	session.close();
        }
	}

}
