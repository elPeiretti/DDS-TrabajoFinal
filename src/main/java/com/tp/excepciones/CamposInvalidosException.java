package com.tp.excepciones;

import java.util.List;

public class CamposInvalidosException extends Exception{
    public List<String> errores;
    public CamposInvalidosException(List<String> errores){
        super("Algunos de los campos ingresados son inválidos.");
        this.errores = errores;
    }
}
