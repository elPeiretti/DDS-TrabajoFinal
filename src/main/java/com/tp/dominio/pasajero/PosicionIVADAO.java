package com.tp.dominio.pasajero;

import java.util.List;

public interface PosicionIVADAO {

	public List<PosicionIVA> getAll();
	public PosicionIVA getById(Integer idPosicionIVA);

}
