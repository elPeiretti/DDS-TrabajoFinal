package com.tp.dominio;

public class Ciudad {
	private String nombre;
	private String idCiudad; 
	private Provincia provincia;
	public Provincia getProvincia() {
		return provincia;
	}
	public Pais getPais() {
		return provincia.getPais();
	}
}
