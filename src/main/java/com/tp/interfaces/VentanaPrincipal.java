package com.tp.interfaces;

import java.awt.*;
import javax.swing.*;
import org.hibernate.Session;
import com.tp.hibernate.HibernateUtil;
import com.tp.interfaces.misc.Encabezado;
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
					frame.setResizable(false);
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
		UIManager.put("Button.defaultButtonFollowsFocus", Boolean.TRUE);
		//cambiarPanel(new MenuAutenticacion(this,encabezado), MenuAutenticacion.x_bound,MenuAutenticacion.y_bound,MenuAutenticacion.titulo);
		cambiarPanel(new MenuPrincipal(this,encabezado), MenuPrincipal.x_bound,MenuPrincipal.y_bound,MenuPrincipal.titulo);
	}

	public void cambiarPanel(JPanel panel, int x, int y, String title){
		setContentPane(panel);
		setSize(x, y); 
		setTitle(title);
		setVisible(true);
		((SeteableTab) panel).setDefaultTab();
	}

}
