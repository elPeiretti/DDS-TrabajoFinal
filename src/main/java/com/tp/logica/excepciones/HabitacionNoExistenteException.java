package com.tp.logica.excepciones;

public class HabitacionNoExistenteException extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3399582671901494427L;

	public HabitacionNoExistenteException(){
        super("LA HABITACION NO EXISTE");
    }
}
