package com.tp.logica.excepciones;

import java.util.List;

public class CamposInvalidosException extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = -8803425089041561229L;
	public List<String> errores;
    public CamposInvalidosException(List<String> errores){
        super("Algunos de los campos ingresados son inválidos.");
        this.errores = errores;
    }
}
