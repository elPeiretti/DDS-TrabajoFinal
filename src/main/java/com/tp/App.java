package com.tp;


import org.hibernate.*;

import com.hibernate.HibernateUtil;
import com.tp.dominio.Pais;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Maria chucena su choza techaba" );
        Pais p = new Pais("Argentina");
        
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
        	tx = session.beginTransaction();
        	session.save(p);
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
