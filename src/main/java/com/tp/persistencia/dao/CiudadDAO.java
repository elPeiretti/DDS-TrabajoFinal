package com.tp.persistencia.dao;

import java.util.List;

import com.tp.logica.dominio.Ciudad;

public interface CiudadDAO {

	List<Ciudad> getByProvincia(Integer idProvincia);
	public Ciudad getById(Integer idCiudad);

}
