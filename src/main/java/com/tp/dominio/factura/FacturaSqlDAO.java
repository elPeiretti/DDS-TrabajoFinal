package com.tp.dominio.factura;

import com.tp.hibernate.HibernateUtil;
import com.tp.interfaces.misc.Mensaje;

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
