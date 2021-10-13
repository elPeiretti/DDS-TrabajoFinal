package com.tp.interfaces;

import java.awt.*;
import java.text.SimpleDateFormat;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.hibernate.Session;

import com.tp.hibernate.HibernateUtil;
import com.tp.interfaces.facturacion.*;
import com.tp.interfaces.habitaciones.*;
import com.tp.interfaces.habitaciones.ocupaciones.*;
import com.tp.interfaces.habitaciones.reservas.*;
import com.tp.interfaces.ingresos.*;
import com.tp.interfaces.misc.Encabezado;
import com.tp.interfaces.pagos.*;
import com.tp.interfaces.pasajeros.*;
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
					Session session = HibernateUtil.getSessionFactory().openSession();
					session.close();
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
		//MenuPrincipal app = new MenuPrincipal(this,encabezado);
		MenuAutenticacion app = new MenuAutenticacion(this,encabezado);
		setContentPane(app);
	}

}
