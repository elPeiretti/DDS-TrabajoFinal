package com.tp.dominio.pasajero;

import java.util.List;

public interface TipoDocumentoDAO {

	public TipoDocumento getById(int id);
	
	public List<TipoDocumento> getAll();
	
}
