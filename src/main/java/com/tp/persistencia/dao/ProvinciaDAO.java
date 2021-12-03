package com.tp.persistencia.dao;

import java.util.List;

import com.tp.logica.dominio.Provincia;

public interface ProvinciaDAO {

	List<Provincia> getByPais(Integer idPais);

}
