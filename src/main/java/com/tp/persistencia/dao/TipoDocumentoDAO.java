package com.tp.persistencia.dao;

import java.util.List;

import com.tp.logica.dominio.TipoDocumento;

public interface TipoDocumentoDAO {

	public TipoDocumento getById(int id);
	
	public List<TipoDocumento> getAll();
	
}
