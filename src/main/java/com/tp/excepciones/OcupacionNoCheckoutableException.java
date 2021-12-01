package com.tp.excepciones;

public class OcupacionNoCheckoutableException extends Exception{
    public OcupacionNoCheckoutableException(){
        super("LA ULTIMA OCUPACION DE LA HABITACION NO ES ACTUAL");
    }
}
