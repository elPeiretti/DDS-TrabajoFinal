package test;
import interfaces.*;
import interfaces.habitaciones.ocupaciones.*;
import interfaces.pasajeros.*;

import javax.swing.*;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame ventana = new JFrame();
		MenuBuscarResponsable ma = new MenuBuscarResponsable(ventana);
		ventana.setBounds(200,200,655,500);
		ventana.setVisible(true);
		
		ventana.add(ma);
	}

}