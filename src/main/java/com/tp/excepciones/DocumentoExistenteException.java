package com.tp.excepciones;

public class DocumentoExistenteException extends Exception{
    public DocumentoExistenteException(){
        super("Ya existe un pasajero con el tipo de documento y numero de documento dados en la base de datos.");
    }
}
