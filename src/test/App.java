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
		JFrame ventana = new VentanaPrincipal();

		ventana.setBounds(200,200,640,480);
		ventana.setVisible(true);
	}

}
