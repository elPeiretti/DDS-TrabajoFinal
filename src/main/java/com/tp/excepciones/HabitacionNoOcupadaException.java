package com.tp.excepciones;

public class HabitacionNoOcupadaException extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = 2845034189705564287L;

	public HabitacionNoOcupadaException(){
        super("LA HABITACION NO SE ENCUENTRA OCUPADA");
    }
}
