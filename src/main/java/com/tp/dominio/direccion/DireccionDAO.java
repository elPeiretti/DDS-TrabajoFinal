package com.tp.dominio.direccion;

import com.tp.excepciones.InsertDireccionException;

public interface DireccionDAO {
	void insertarDireccion(Direccion direccion) throws InsertDireccionException;
}
