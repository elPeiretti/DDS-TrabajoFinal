package com.tp.persistencia.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.tp.logica.dominio.ResponsablePagoTercero;
import com.tp.persistencia.hibernate.HibernateUtil;

public class ResponsablePagoTerceroSqlDAO implements ResponsablePagoTerceroDAO {
    @Override
    public ResponsablePagoTercero getByCuit(String cuit) {
        ResponsablePagoTercero resultado;
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sqlStatement = "SELECT r FROM ResponsablePagoTercero r WHERE r.cuit = :cuit";    

        @SuppressWarnings("unchecked")
		TypedQuery<ResponsablePagoTercero> hqlQuery = session.createQuery(sqlStatement); 
        
        hqlQuery.setParameter("cuit", cuit);
        List<ResponsablePagoTercero> aux = hqlQuery.getResultList();
        resultado = aux.size() == 0 ? null : aux.get(0);
        session.close();
        return resultado;
    }

    @Override
    public ResponsablePagoTercero getResponsableTerceroById(Integer idResponsable) {
        ResponsablePagoTercero resultado;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		String sqlStatement = "SELECT r FROM ResponsablePagoTercero r WHERE r.idResponsable = :id ";
	
		@SuppressWarnings("unchecked")
		TypedQuery<ResponsablePagoTercero> hqlQuery = session.createQuery(sqlStatement);
		
		hqlQuery.setParameter("id", idResponsable);
			
		resultado = hqlQuery.getSingleResult();
		
		session.close();
		
		return resultado;
    }
}
