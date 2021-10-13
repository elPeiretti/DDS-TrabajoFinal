package com.tp.dominio.conserje;

import com.tp.hibernate.HibernateUtil;
import org.hibernate.*;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class ConserjeSqlDAO implements ConserjeDAO {

    @Override
    public Conserje getConserjeByCriteria(String usuario, String pass) throws NoResultException {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		String sqlStatement = "SELECT c FROM Conserje c WHERE c.usuario = :usuario AND c.contrasenia = :pass ";

		TypedQuery<Conserje> hqlQuery = session.createQuery(sqlStatement);
        hqlQuery.setParameter("usuario", usuario);
        hqlQuery.setParameter("pass", pass);		
		
		Conserje c = hqlQuery.getSingleResult();
		session.close();
		
		return c;
    }
    
}
