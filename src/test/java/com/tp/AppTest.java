package com.tp;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.tp.logica.dominio.*;
import com.tp.persistencia.hibernate.HibernateUtil;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
	public static void main( String[] args )
    {
        //agregarPaisProvinciaCiudad();
        
        //agregarHabitacionTipoHabitacionYCostoPorNoche();
        
		
		
		//Prueba Carga CostoPorNoche Insertada
		/*
		Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        CostoPorNoche c_noche =  null;
		try {
        	tx = session.beginTransaction();
        	c_noche = session.get(CostoPorNoche.class, 1);
        	//session.saveOrUpdate(c_parana);
        	//session.saveOrUpdate(c_bariloche);
        	//session.saveOrUpdate(c_esperanza);
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
		
		System.out.println(c_noche.toString());*/
		
    }
    @SuppressWarnings("unused")
    private static void agregarHabitacionTipoHabitacionYCostoPorNoche() {
    	
    	CostoPorNoche c_noche = new CostoPorNoche(LocalDate.now(),LocalDate.now(),20.5);
    	
    	Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
        	tx = session.beginTransaction();
        	session.saveOrUpdate(c_noche);
        	//session.saveOrUpdate(c_parana);
        	//session.saveOrUpdate(c_bariloche);
        	//session.saveOrUpdate(c_esperanza);
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
        
        //Prueba Actualizacion Nombre
        
        tx = null;
        session = HibernateUtil.getSessionFactory().openSession();
        
        try {
        	tx = session.beginTransaction();
        	c_esperanza.setNombre("Santo Tome");
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
    
    
    
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
}
