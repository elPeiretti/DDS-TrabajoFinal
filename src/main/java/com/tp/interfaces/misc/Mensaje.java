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
	
}
