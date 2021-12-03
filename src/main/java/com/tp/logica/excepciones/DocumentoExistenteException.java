package com.tp.logica.excepciones;

public class DocumentoExistenteException extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = -8884684625212379580L;

	public DocumentoExistenteException(){
        super("Ya existe un pasajero con el tipo de documento y numero de documento dados en la base de datos.");
    }
}
