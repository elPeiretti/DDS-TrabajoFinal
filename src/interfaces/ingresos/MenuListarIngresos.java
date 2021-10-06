package interfaces.ingresos;

import javax.swing.*;

import interfaces.misc.Encabezado;

public class MenuListarIngresos extends JPanel {

	private JFrame ventana_contenedora;
	private JPanel encabezado;
	
	public MenuListarIngresos(JFrame ventana_contenedora, Encabezado encabezado) {
		this.ventana_contenedora = ventana_contenedora;
		this.encabezado = encabezado;
	}

}
