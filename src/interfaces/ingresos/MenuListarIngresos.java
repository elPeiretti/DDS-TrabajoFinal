package interfaces.ingresos;

import java.util.List;

import javax.swing.*;

import interfaces.misc.Encabezado;
import interfaces.misc.TabOrder;

public class MenuListarIngresos extends JPanel {

	private JFrame ventana_contenedora;
	private JPanel encabezado;
	
	public MenuListarIngresos(JFrame ventana_contenedora, Encabezado encabezado) {
		this.ventana_contenedora = ventana_contenedora;
		this.encabezado = encabezado;
		
		this.agregarTabOrder();
	}
	
	private void agregarTabOrder() {
		this.setFocusTraversalPolicy(new TabOrder(List.of(
			//componentes en orden
				)));
		this.setFocusTraversalPolicyProvider(true);
		}

}
