package com.tp.logica.excepciones;

public class OcupacionNoCheckoutableException extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2837819235725484855L;

	public OcupacionNoCheckoutableException(){
        super("LA ULTIMA OCUPACION DE LA HABITACION NO ES ACTUAL");
    }
}
