package interfaces.misc;

import javax.swing.JOptionPane;

public class Mensaje {
	
	public static int mensajeConfirmacion(String msg) {
		String[] opt = {"Cancelar", "Confirmar"};
		return JOptionPane.showOptionDialog(null, msg, "Confirmar acción", JOptionPane.DEFAULT_OPTION, -1, null, opt, opt[1]);
	}
	
}
