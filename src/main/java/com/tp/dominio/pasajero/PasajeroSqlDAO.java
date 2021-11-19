package com.tp.dominio.pasajero;

import java.util.List;
import javax.persistence.TypedQuery;
import javax.swing.SortOrder;

import org.hibernate.*;

import com.tp.dto.BusqPasajeroDTO;
import com.tp.dto.PasajeroDTO;
import com.tp.hibernate.HibernateUtil;
import com.tp.interfaces.misc.Mensaje;

public class PasajeroSqlDAO implements PasajeroDAO {

	@Override
	public List<Pasajero> getPasajerosByCriteria(BusqPasajeroDTO criterios, Integer li, Integer cant) {
		
		List<Pasajero> resultado;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		String sqlStatement = "SELECT p FROM Pasajero p JOIN p.tipoDocumento td WHERE true = true ";
		
		if(criterios.getNombres() != null) {
			sqlStatement += "AND p.nombres LIKE :nombres ";
		} 
		
		if(criterios.getApellido() != null) {
			sqlStatement += "AND p.apellido LIKE :apellido ";
		} 
		
		if(criterios.getTipoDocumentoDTO() != null) {
			sqlStatement += "AND td.idTipoDocumento = :id_tipo ";
		} 
		
		if(criterios.getNroDocumento() != null) {
			sqlStatement += "AND p.nroDocumento = :documento ";
		} 
		String orderBy = "ORDER BY p.apellido, p.nombres";
		
		if(criterios.getColumna() != null) {
			orderBy = "ORDER BY ";
			BusqPasajeroDTO.columnaOrden aux = criterios.getColumna();
			switch(aux) {
				case NOMBRES:
					orderBy += "p.nombres ";
					break;
				case APELLIDO:
					orderBy += "p.apellido ";
					break;
				case TIPODOC:
					orderBy += "td.tipo ";
					break;
				case NRODOC:
					orderBy += "p.nroDocumento ";
					break;
			}
			if(criterios.getSortOrder() != null) orderBy += criterios.getSortOrder() == SortOrder.ASCENDING ? "ASC" : "DESC";
		}
		sqlStatement += orderBy;
		
		TypedQuery<Pasajero> hqlQuery = session.createQuery(sqlStatement);
		
		if(criterios.getNombres() != null) {
			hqlQuery.setParameter("nombres", criterios.getNombres()+"%");
		} 
		
		if(criterios.getApellido() != null) {
			hqlQuery.setParameter("apellido", criterios.getApellido()+"%");
		} 
		
		if(criterios.getTipoDocumentoDTO() != null) {
			hqlQuery.setParameter("id_tipo", criterios.getTipoDocumentoDTO().getIdTipoDocumento());
		} 
		
		if(criterios.getNroDocumento() != null) {
			hqlQuery.setParameter("documento", criterios.getNroDocumento());
		}
		
		hqlQuery.setFirstResult(li);
		hqlQuery.setMaxResults(cant);
		
		resultado = hqlQuery.getResultList();
		
		session.close();
		
		return resultado;
	}

	@Override
	public Long getCountPasajerosByCriteria(BusqPasajeroDTO criterios) {
		
		Long resultado;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		String sqlStatement = "SELECT count(p) FROM Pasajero p JOIN p.tipoDocumento td WHERE true = true ";
		
		if(criterios.getNombres() != null) {
			sqlStatement += "AND p.nombres LIKE :nombres ";
		} 
		
		if(criterios.getApellido() != null) {
			sqlStatement += "AND p.apellido LIKE :apellido ";
		} 
		
		if(criterios.getTipoDocumentoDTO() != null) {
			sqlStatement += "AND td.idTipoDocumento = :id_tipo ";
		} 
		
		if(criterios.getNroDocumento() != null) {
			sqlStatement += "AND p.nroDocumento = :documento ";
		}
		
		TypedQuery<Long> hqlQuery = session.createQuery(sqlStatement);
		
		String aux;
		if((aux = criterios.getNombres()) != null) {
			hqlQuery.setParameter("nombres", aux+"%");
		} 
		
		if((aux = criterios.getApellido()) != null) {
			hqlQuery.setParameter("apellido", aux+"%");
		} 
		
		if(criterios.getTipoDocumentoDTO() != null) {
			hqlQuery.setParameter("id_tipo", criterios.getTipoDocumentoDTO().getIdTipoDocumento());
		} 
		if((aux = criterios.getNroDocumento()) != null) {
			hqlQuery.setParameter("documento", aux);
		}
		
		resultado = hqlQuery.getSingleResult();
		
		session.close();
		
		return resultado;
	}

	@Override
	public List<Pasajero> getPasajerosByCriteria(BusqPasajeroDTO criterios) {
		List<Pasajero> resultado;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		String sqlStatement = "SELECT p FROM Pasajero p JOIN p.tipoDocumento td WHERE true = true ";
		
		if(criterios.getNombres() != null) {
			sqlStatement += "AND p.nombres LIKE :nombres ";
		} 
		if(criterios.getApellido() != null) {
			sqlStatement += "AND p.apellido LIKE :apellido ";
		} 
		if(criterios.getTipoDocumentoDTO() != null) {
			sqlStatement += "AND td.idTipoDocumento = :id_tipo ";
		} 
		if(criterios.getNroDocumento() != null) {
			sqlStatement += "AND p.nroDocumento = :documento ";
		}
	
		TypedQuery<Pasajero> hqlQuery = session.createQuery(sqlStatement);
		
		String aux;
		if((aux = criterios.getNombres()) != null) {
			hqlQuery.setParameter("nombres", aux+"%");
		} 
		if((aux = criterios.getApellido()) != null) {
			hqlQuery.setParameter("apellido", aux+"%");
		} 
		if(criterios.getTipoDocumentoDTO() != null) {
			hqlQuery.setParameter("id_tipo", criterios.getTipoDocumentoDTO().getIdTipoDocumento());
		} 
		if((aux = criterios.getNroDocumento()) != null) {
			hqlQuery.setParameter("documento", aux);
		}
		
		resultado = hqlQuery.getResultList();
		session.close();
		return resultado;
	}

    public void insertarPasajero(Pasajero pasajero) {
		Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
        	tx = session.beginTransaction();
        	session.saveOrUpdate(pasajero);
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

	@Override
	public List<Pasajero> getPasajerosByCriteria(BusqPasajeroDTO criterios, Integer li, Integer cant, PasajeroDTO responsable) {

		List<Pasajero> resultado;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		String sqlStatement = "SELECT p FROM Pasajero p JOIN p.tipoDocumento td WHERE true = true ";
		
		if(criterios.getNombres() != null) {
			sqlStatement += "AND p.nombres LIKE :nombres ";
		} 
		
		if(criterios.getApellido() != null) {
			sqlStatement += "AND p.apellido LIKE :apellido ";
		} 
		
		if(criterios.getTipoDocumentoDTO() != null) {
			sqlStatement += "AND td.idTipoDocumento = :id_tipo ";
		} 
		
		if(criterios.getNroDocumento() != null) {
			sqlStatement += "AND p.nroDocumento = :documento ";
		} 
		
		sqlStatement += "AND p.idPasajero != :id_responsable ";
		
		String orderBy = "ORDER BY p.apellido, p.nombres";
		
		if(criterios.getColumna() != null) {
			orderBy = "ORDER BY ";
			BusqPasajeroDTO.columnaOrden aux = criterios.getColumna();
			switch(aux) {
				case NOMBRES:
					orderBy += "p.nombres ";
					break;
				case APELLIDO:
					orderBy += "p.apellido ";
					break;
				case TIPODOC:
					orderBy += "td.tipo ";
					break;
				case NRODOC:
					orderBy += "p.nroDocumento ";
					break;
			}
			if(criterios.getSortOrder() != null) orderBy += criterios.getSortOrder() == SortOrder.ASCENDING ? "ASC" : "DESC";
		}
		sqlStatement += orderBy;
		
		TypedQuery<Pasajero> hqlQuery = session.createQuery(sqlStatement);
		
		if(criterios.getNombres() != null) {
			hqlQuery.setParameter("nombres", criterios.getNombres()+"%");
		} 
		
		if(criterios.getApellido() != null) {
			hqlQuery.setParameter("apellido", criterios.getApellido()+"%");
		} 
		
		if(criterios.getTipoDocumentoDTO() != null) {
			hqlQuery.setParameter("id_tipo", criterios.getTipoDocumentoDTO().getIdTipoDocumento());
		} 
		
		if(criterios.getNroDocumento() != null) {
			hqlQuery.setParameter("documento", criterios.getNroDocumento());
		}
		
		hqlQuery.setParameter("id_responsable", responsable.getIdPasajero());
		
		hqlQuery.setFirstResult(li);
		hqlQuery.setMaxResults(cant);
		
		resultado = hqlQuery.getResultList();
		
		session.close();
		
		return resultado;
	}

	@Override
	public Long getCountPasajerosByCriteria(BusqPasajeroDTO criterios, PasajeroDTO responsable) {

		Long resultado;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		String sqlStatement = "SELECT count(p) FROM Pasajero p JOIN p.tipoDocumento td WHERE true = true ";
		
		if(criterios.getNombres() != null) {
			sqlStatement += "AND p.nombres LIKE :nombres ";
		} 
		
		if(criterios.getApellido() != null) {
			sqlStatement += "AND p.apellido LIKE :apellido ";
		} 
		
		if(criterios.getTipoDocumentoDTO() != null) {
			sqlStatement += "AND td.idTipoDocumento = :id_tipo ";
		} 
		
		if(criterios.getNroDocumento() != null) {
			sqlStatement += "AND p.nroDocumento = :documento ";
		}
		
		sqlStatement += "AND p.idPasajero != :id_responsable ";
		
		TypedQuery<Long> hqlQuery = session.createQuery(sqlStatement);
		
		String aux;
		if((aux = criterios.getNombres()) != null) {
			hqlQuery.setParameter("nombres", aux+"%");
		} 
		
		if((aux = criterios.getApellido()) != null) {
			hqlQuery.setParameter("apellido", aux+"%");
		} 
		
		if(criterios.getTipoDocumentoDTO() != null) {
			hqlQuery.setParameter("id_tipo", criterios.getTipoDocumentoDTO().getIdTipoDocumento());
		} 
		if((aux = criterios.getNroDocumento()) != null) {
			hqlQuery.setParameter("documento", aux);
		}
		
		hqlQuery.setParameter("id_responsable", responsable.getIdPasajero());
		
		resultado = hqlQuery.getSingleResult();
		
		session.close();
		
		return resultado;
	}

	@Override
	public List<Pasajero> getPasajerosAdultosByCriteria(BusqPasajeroDTO criterios, Integer li, Integer cant) {

		List<Pasajero> resultado;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		String sqlStatement = "SELECT p FROM Pasajero p JOIN p.tipoDocumento td WHERE true = true ";
		
		if(criterios.getNombres() != null) {
			sqlStatement += "AND p.nombres LIKE :nombres ";
		} 
		
		if(criterios.getApellido() != null) {
			sqlStatement += "AND p.apellido LIKE :apellido ";
		} 
		
		if(criterios.getTipoDocumentoDTO() != null) {
			sqlStatement += "AND td.idTipoDocumento = :id_tipo ";
		} 
		
		if(criterios.getNroDocumento() != null) {
			sqlStatement += "AND p.nroDocumento = :documento ";
		} 
		
		//sqlstatement += "and (select date_part('day',current_date - p.fechadenacimiento))/365 >= 18 ";
		
		String orderBy = "ORDER BY p.apellido, p.nombres";
		
		if(criterios.getColumna() != null) {
			orderBy = "ORDER BY ";
			BusqPasajeroDTO.columnaOrden aux = criterios.getColumna();
			switch(aux) {
				case NOMBRES:
					orderBy += "p.nombres ";
					break;
				case APELLIDO:
					orderBy += "p.apellido ";
					break;
				case TIPODOC:
					orderBy += "td.tipo ";
					break;
				case NRODOC:
					orderBy += "p.nroDocumento ";
					break;
			}
			if(criterios.getSortOrder() != null) orderBy += criterios.getSortOrder() == SortOrder.ASCENDING ? "ASC" : "DESC";
		}
		sqlStatement += orderBy;
		
		TypedQuery<Pasajero> hqlQuery = session.createQuery(sqlStatement);
		
		if(criterios.getNombres() != null) {
			hqlQuery.setParameter("nombres", criterios.getNombres()+"%");
		} 
		
		if(criterios.getApellido() != null) {
			hqlQuery.setParameter("apellido", criterios.getApellido()+"%");
		} 
		
		if(criterios.getTipoDocumentoDTO() != null) {
			hqlQuery.setParameter("id_tipo", criterios.getTipoDocumentoDTO().getIdTipoDocumento());
		} 
		
		if(criterios.getNroDocumento() != null) {
			hqlQuery.setParameter("documento", criterios.getNroDocumento());
		}
		
		hqlQuery.setFirstResult(li);
		hqlQuery.setMaxResults(cant);
		
		resultado = hqlQuery.getResultList();
		
		session.close();
		
		return resultado;
	}

	@Override
	public Long getCountPasajerosAdultosByCriteria(BusqPasajeroDTO criterios) {
		// TODO Auto-generated method stub
		Long resultado;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		String sqlStatement = "SELECT count(p) FROM Pasajero p JOIN p.tipoDocumento td WHERE true = true ";
		
		if(criterios.getNombres() != null) {
			sqlStatement += "AND p.nombres LIKE :nombres ";
		} 
		
		if(criterios.getApellido() != null) {
			sqlStatement += "AND p.apellido LIKE :apellido ";
		} 
		
		if(criterios.getTipoDocumentoDTO() != null) {
			sqlStatement += "AND td.idTipoDocumento = :id_tipo ";
		} 
		
		if(criterios.getNroDocumento() != null) {
			sqlStatement += "AND p.nroDocumento = :documento ";
		}
		
		//sqlStatement += "AND (SELECT extract(YEAR from (Select AGE(p.fechaDeNacimiento)))) >= 18 ";
		
		TypedQuery<Long> hqlQuery = session.createQuery(sqlStatement);
		
		String aux;
		if((aux = criterios.getNombres()) != null) {
			hqlQuery.setParameter("nombres", aux+"%");
		} 
		
		if((aux = criterios.getApellido()) != null) {
			hqlQuery.setParameter("apellido", aux+"%");
		} 
		
		if(criterios.getTipoDocumentoDTO() != null) {
			hqlQuery.setParameter("id_tipo", criterios.getTipoDocumentoDTO().getIdTipoDocumento());
		} 
		if((aux = criterios.getNroDocumento()) != null) {
			hqlQuery.setParameter("documento", aux);
		}
		
		resultado = hqlQuery.getSingleResult();
		
		session.close();
		
		return resultado;
		
		
		
	}

}
