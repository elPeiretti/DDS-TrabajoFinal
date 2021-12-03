package com.tp.persistencia.dao;

import java.util.List;

import com.tp.logica.dominio.PosicionIVA;

public interface PosicionIVADAO {

	public List<PosicionIVA> getAll();
	public PosicionIVA getById(Integer idPosicionIVA);

}
