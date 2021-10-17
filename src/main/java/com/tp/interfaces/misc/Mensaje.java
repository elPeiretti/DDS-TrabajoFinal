package com.tp.interfaces.misc;

import javax.swing.JOptionPane;

public class Mensaje {
	
	public static int mensajeConfirmacion(String msg) {
		String[] opt = {"Cancelar", "Confirmar"};
		return JOptionPane.showOptionDialog(null, msg, "Confirmar acción", JOptionPane.DEFAULT_OPTION, -1, null, opt, opt[1]);
	}

	public static int mensajeInformacion(String msg) {
		String[] opt = {"Continuar"};
		return JOptionPane.showOptionDialog(null, msg, "Mensaje Informativo", JOptionPane.DEFAULT_OPTION, -1, null, opt, opt[0]);
	}

	public static int warningDocumentoExistente(){
		String[] opt = {"Corregir", "Aceptar Igualmente"};
		return JOptionPane.showOptionDialog(null, "¡CUIDADO! El tipo y número de documento ya existen en el sistema", "Alerta: Documento ya existente", JOptionPane.DEFAULT_OPTION, -1, null, opt, opt[1]);
	}
	
	public static int mensajeError(String[] msgs){
		String[] opt = {"Confirmar"};
		String msg = "Se produjeron los siguientes errores durante la ejecución:\n";
		Integer i = 1;
		for (String m : msgs){
			msg+=i.toString()+". "+m+"\n";
			i++;
		}
		return JOptionPane.showOptionDialog(null, msg, "Error", JOptionPane.DEFAULT_OPTION, -1, null, opt, opt[0]);
	}
}
