package com.tp.excepciones;

public class HabitacionNoOcupadaException extends Exception{
    public HabitacionNoOcupadaException(){
        super("LA HABITACION NO SE ENCUENTRA OCUPADA");
    }
}
