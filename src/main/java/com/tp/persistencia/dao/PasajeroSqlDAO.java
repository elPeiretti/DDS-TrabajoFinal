package com.tp.persistencia.dao;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.swing.SortOrder;

import org.hibernate.*;

import com.tp.interfaz.dto.BusqPasajeroDTO;
import com.tp.interfaz.dto.PasajeroDTO;
import com.tp.logica.dominio.Pasajero;
import com.tp.logica.excepciones.NuevoPasajeroException;
import com.tp.persistencia.hibernate.HibernateUtil;

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
		
		@SuppressWarnings("unchecked")
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
		
		@SuppressWarnings("unchecked")
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
	
		@SuppressWarnings("unchecked")
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

    public void insertarPasajero(Pasajero pasajero) throws NuevoPasajeroException {
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
        	throw new NuevoPasajeroException();
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
		
		@SuppressWarnings("unchecked")
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
		
		@SuppressWarnings("unchecked")
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
		
		sqlStatement += "AND  (:hoy - p.fechaDeNacimiento)>= 365*18 ";
		
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
		
		@SuppressWarnings("unchecked")
		TypedQuery<Pasajero> hqlQuery = session.createQuery(sqlStatement);
		hqlQuery.setParameter("hoy", LocalDate.now());
		
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
		
		sqlStatement += "AND  (:hoy - p.fechaDeNacimiento)>= 365*18 ";
		
		@SuppressWarnings("unchecked")
		TypedQuery<Long> hqlQuery = session.createQuery(sqlStatement);
		hqlQuery.setParameter("hoy", LocalDate.now());
		
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
	public Pasajero getPasajeroById(Integer idPasajero) {
		
		Pasajero resultado;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		String sqlStatement = "SELECT p FROM Pasajero p JOIN p.tipoDocumento td WHERE p.idPasajero = :id ";
		
	
		@SuppressWarnings("unchecked")
		TypedQuery<Pasajero> hqlQuery = session.createQuery(sqlStatement);
		
		hqlQuery.setParameter("id", idPasajero);
			
		resultado = hqlQuery.getSingleResult();
		
		session.close();
		
		return resultado;
	}

	@Override
	public List<Pasajero> getPasajerosById(List<Integer> idPasajeros) {
		List<Pasajero> resultado;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		String sqlStatement = "SELECT p FROM Pasajero p JOIN p.tipoDocumento td WHERE p.idPasajero IN :id ";
		
		@SuppressWarnings("unchecked")
		TypedQuery<Pasajero> hqlQuery = session.createQuery(sqlStatement);
		
		hqlQuery.setParameter("id", idPasajeros);
			
		resultado = hqlQuery.getResultList();
		
		session.close();
		
		return resultado;
	}

	@Override
	public List<Pasajero> getPasajerosQueNoEstenOcupandoByCriteria(BusqPasajeroDTO criterios, int li, int cant) {
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
		
		sqlStatement += "AND p NOT IN (SELECT p2 FROM Ocupacion o2 JOIN o2.acompaniantes p2 WHERE o2.fechaEgreso >= :hoy)";
		
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
		
		@SuppressWarnings("unchecked")
		TypedQuery<Pasajero> hqlQuery = session.createQuery(sqlStatement);
		
		hqlQuery.setParameter("hoy", LocalDate.now());
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

	public Long getCountPasajerosQueNoEstenOcupandoByCriteria(BusqPasajeroDTO criterios) {
		Long resultado;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		String sqlStatement = "SELECT COUNT(p) FROM Pasajero p JOIN p.tipoDocumento td WHERE true = true ";
		
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
		
		sqlStatement += "AND p NOT IN (SELECT p2 FROM Ocupacion o2 JOIN o2.acompaniantes p2 WHERE o2.fechaEgreso >= :hoy)";
		
		@SuppressWarnings("unchecked")
		TypedQuery<Long> hqlQuery = session.createQuery(sqlStatement);
		
		hqlQuery.setParameter("hoy", LocalDate.now());
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
		
		resultado = hqlQuery.getSingleResult();
		
		session.close();
		
		return resultado;
	}

}
