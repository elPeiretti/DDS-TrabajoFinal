package interfaces;

import java.awt.*;
import java.text.SimpleDateFormat;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import interfaces.facturacion.*;
import interfaces.habitaciones.*;
import interfaces.habitaciones.ocupaciones.*;
import interfaces.habitaciones.reservas.*;
import interfaces.ingresos.*;
import interfaces.misc.Encabezado;
import interfaces.pagos.*;
import interfaces.pasajeros.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class VentanaPrincipal extends JFrame {

	private Encabezado encabezado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 660, 500);
		encabezado = new Encabezado();
		encabezado.lbl_fecha.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString());
		//MenuAutenticacion app = new MenuAutenticacion(this);
		MenuPrincipal app = new MenuPrincipal(this,encabezado);
		//app.setEncabezado(encabezado);
		setContentPane(app);
	}

}
