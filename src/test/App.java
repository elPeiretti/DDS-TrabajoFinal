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
<<<<<<< HEAD
		JPanel ma = new MenuAltaPasajero(ventana);
=======
		JPanel ma = new MenuConfirmarReserva(ventana);
>>>>>>> 00d6f815b2cba28628b4cddb99b6e7b5785734a0
		ventana.setBounds(200,200,655,500);
		ventana.setVisible(true);
		
		ventana.add(ma);
	}

}
