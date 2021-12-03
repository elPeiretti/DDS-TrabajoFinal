package com.tp.persistencia.dao;

import com.tp.logica.dominio.Direccion;
import com.tp.logica.excepciones.InsertDireccionException;

public interface DireccionDAO {
	void insertarDireccion(Direccion direccion) throws InsertDireccionException;
}
