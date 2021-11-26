package com.tp.excepciones;

public class HabitacionSinOcupacionesException extends Exception{
    public HabitacionSinOcupacionesException(){
        super("LA HABITACION NO TIENE NINGUNA OCUPACION");
    }
    
}
