package com.tp.excepciones;

public class HabitacionNoExistenteException extends Exception{
    public HabitacionNoExistenteException(){
        super("LA HABITACION NO EXISTE");
    }
}
