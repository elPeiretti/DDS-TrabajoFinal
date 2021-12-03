package com.tp.logica.excepciones;

public class FacturaSinItemsException extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = -8764190128353387861L;

	public FacturaSinItemsException(){
        super("LA FACTURA QUE SE QUIERE CREAR NO CONTIENE ITEMS");
    }
}
