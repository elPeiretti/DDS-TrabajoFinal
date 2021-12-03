package com.tp.persistencia.dao;

import java.util.List;

import com.tp.logica.dominio.Pais;

public interface PaisDAO {

	public List<Pais> getAll();
	public Pais getById(Integer nacionalidad);
	
}
