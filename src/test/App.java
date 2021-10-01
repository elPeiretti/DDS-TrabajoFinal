package test;
import interfaces.*;
import interfaces.habitaciones.MenuEstadoHabitaciones;
import interfaces.habitaciones.ocupaciones.*;
import interfaces.habitaciones.reservas.MenuConfirmarReserva;
import interfaces.pasajeros.*;

import javax.swing.*;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame ventana = new JFrame();
		JPanel ma = new MenuConfirmarReserva(ventana);
		ventana.setBounds(200,200,655,500);
		ventana.setVisible(true);
		
		ventana.add(ma);
	}

}
