package com.tp;


import org.hibernate.*;

import com.hibernate.HibernateUtil;
import com.tp.dominio.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //agregarPaisProvinciaCiudad();
        
        
        
    }
    
    public static void agregarPaisProvinciaCiudad () {
    	
        Pais p_argentina = new Pais("Argentina");
        Pais p_colombia = new Pais("Colombia");
        Provincia prov_santafe = new Provincia("Santa Fe", p_argentina);
        Provincia prov_cordoba = new Provincia("Cordoba", p_argentina);
        Provincia prov_bogota = new Provincia("Bogota", p_colombia);
        Ciudad c_recreo = new Ciudad("Recreo", prov_santafe);
        Ciudad c_parana = new Ciudad("Parana", prov_cordoba);
        Ciudad c_bariloche = new Ciudad("Bariloche", prov_cordoba);
        Ciudad c_esperanza = new Ciudad("Esperanza", prov_bogota);
        
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
        	tx = session.beginTransaction();
        	session.saveOrUpdate(c_recreo);
        	session.saveOrUpdate(c_parana);
        	session.saveOrUpdate(c_bariloche);
        	session.saveOrUpdate(c_esperanza);
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
