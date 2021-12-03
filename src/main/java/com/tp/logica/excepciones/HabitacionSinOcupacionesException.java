package com.tp.logica.excepciones;

public class HabitacionSinOcupacionesException extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = -431585471962447796L;

	public HabitacionSinOcupacionesException(){
        super("LA HABITACION NO TIENE NINGUNA OCUPACION");
    }
    
}
