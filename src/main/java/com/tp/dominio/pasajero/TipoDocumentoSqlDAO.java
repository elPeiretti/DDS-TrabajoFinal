package com.tp.dominio.pasajero;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.*;

import com.tp.dto.TipoDocumentoDTO;
import com.tp.hibernate.HibernateUtil;


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
		
		TypedQuery<TipoDocumento> hqlQuery = session.createQuery("SELECT td FROM TipoDocumento td");
		resultado = hqlQuery.getResultList();
		
		session.close();
		
		return resultado;
	}
	
	
	

}
