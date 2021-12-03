package com.tp.persistencia.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.*;

import com.tp.logica.dominio.TipoDocumento;
import com.tp.persistencia.hibernate.HibernateUtil;


public class TipoDocumentoSqlDAO implements TipoDocumentoDAO {

	public TipoDocumento getById(int id) {
		
		
        Session session = HibernateUtil.getSessionFactory().openSession();
        TipoDocumento tipo_documento =  null;
		
       	tipo_documento = session.get(TipoDocumento.class, id);
        
       	session.close();
        
		
		return tipo_documento;
		
		
		
	}

	
	public List<TipoDocumento> getAll() {
		
		List<TipoDocumento> resultado;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		@SuppressWarnings("unchecked")
		TypedQuery<TipoDocumento> hqlQuery = session.createQuery("SELECT td FROM TipoDocumento td");
		resultado = hqlQuery.getResultList();
		
		session.close();
		
		return resultado;
	}
	
	
	

}
