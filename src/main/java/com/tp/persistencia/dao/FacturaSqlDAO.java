package com.tp.persistencia.dao;

import com.tp.interfaz.pantallas.misc.Mensaje;
import com.tp.logica.dominio.Factura;
import com.tp.persistencia.hibernate.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class FacturaSqlDAO implements FacturaDAO {

    @Override
    public void insertarFactura(Factura factura) {
        
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
        	tx = session.beginTransaction();
        	session.saveOrUpdate(factura);
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

}
