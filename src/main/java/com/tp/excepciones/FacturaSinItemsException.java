package com.tp.excepciones;

public class FacturaSinItemsException extends Exception{
    public FacturaSinItemsException(){
        super("LA FACTURA QUE SE QUIERE CREAR NO CONTIENE ITEMS");
    }
}
