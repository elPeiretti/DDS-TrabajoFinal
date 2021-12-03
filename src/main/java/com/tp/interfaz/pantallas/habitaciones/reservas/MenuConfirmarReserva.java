package com.tp.interfaz.pantallas.habitaciones.reservas;

import javax.swing.*;

import com.tp.interfaz.dto.ReservaDTO;
import com.tp.interfaz.pantallas.misc.*;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

public class MenuConfirmarReserva extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5538032252893802363L;
	public static String titulo = "Confirmar Reserva";
	public static int x_bound = 660;
	public static int y_bound = 500;

	@SuppressWarnings("unused")
	private JFrame ventana_contenedora;
	private JPanel encabezado;
	private JLabel lbl_reserva_nombre;
	private JLabel lbl_apellido;
	private JLabel lbl_telefono;
	private JLabel lbl_reserva_hab;
	private JLabel lbl_nombres;
	private JButton btn_aceptar;
	private JButton btn_rechazar;
	private JTextField jtf_apellido;
	private JTextField jtf_telefono;
	private JTextField jtf_nombres;
	private JPanel rp_reservas;
	private JLabel lbl_error_apellido;
	private JLabel lbl_error_nombres;
	private JLabel lbl_error_telefono;
	
	public MenuConfirmarReserva(JFrame ventana_contenedora) {
		setBackground(Color.WHITE);
		this.ventana_contenedora = ventana_contenedora;
		setLayout(null);
		
		encabezado = new Encabezado();
		encabezado.setBounds(0, 0, 640, 110);
		add(encabezado);
		
		lbl_reserva_nombre = new JLabel("Reserva a nombre de:");
		lbl_reserva_nombre.setBounds(26, 115, 150, 14);
		add(lbl_reserva_nombre);
		
		lbl_apellido = new JLabel("Apellido (*):");
		lbl_apellido.setBounds(68, 140, 65, 14);
		add(lbl_apellido);
		
		lbl_telefono = new JLabel("Tel\u00E9fono (*):");
		lbl_telefono.setBounds(68, 180, 101, 14);
		add(lbl_telefono);
		
		lbl_nombres = new JLabel("Nombres (*):");
		lbl_nombres.setBounds(330, 140, 101, 14);
		add(lbl_nombres);
		
		lbl_reserva_hab = new JLabel("Reserva de habitaciones:");
		lbl_reserva_hab.setBounds(26, 218, 150, 14);
		add(lbl_reserva_hab);
		
		btn_aceptar = new JButton("Aceptar");
		btn_aceptar.setBounds(541, 421, 89, 30);
		add(btn_aceptar);
		
		btn_rechazar = new JButton("Rechazar");
		btn_rechazar.setBounds(442, 421, 89, 30);
		add(btn_rechazar);
		
		jtf_apellido = new JTextField();
		jtf_apellido.setBounds(143, 140, 150, 20);
		jtf_apellido.setColumns(30);
		jtf_apellido.setDocument(new JTextFieldLimit(30));
		add(jtf_apellido);
		
		jtf_telefono = new JTextField();
		jtf_telefono.setBounds(143, 177, 150, 20);
		jtf_telefono.setDocument(new JTextFieldLimit(15));
		jtf_telefono.setColumns(15);
		add(jtf_telefono);
		
		jtf_nombres = new JTextField();
		jtf_nombres.setBounds(409, 140, 150, 20);
		jtf_nombres.setColumns(50);
		jtf_nombres.setDocument(new JTextFieldLimit(50));
		add(jtf_nombres);
		
		
		rp_reservas = new ResultPane<ReservaDTO>();
		rp_reservas.setBounds(10, 240, 620, 180);
		add(rp_reservas);
		
		lbl_error_apellido = new JLabel("");
		lbl_error_apellido.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lbl_error_apellido.setForeground(Color.RED);
		lbl_error_apellido.setBounds(68, 163, 225, 10);
		add(lbl_error_apellido);
		
		lbl_error_nombres = new JLabel("");
		lbl_error_nombres.setForeground(Color.RED);
		lbl_error_nombres.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lbl_error_nombres.setBounds(330, 163, 225, 10);
		add(lbl_error_nombres);
		
		lbl_error_telefono = new JLabel("");
		lbl_error_telefono.setForeground(Color.RED);
		lbl_error_telefono.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lbl_error_telefono.setBounds(68, 200, 225, 10);
		add(lbl_error_telefono);
		
		this.agregarTabOrder();
	}
	private void agregarTabOrder() {
		this.setFocusTraversalPolicy(new TabOrder(List.of(
				jtf_apellido,
				jtf_nombres,
				jtf_telefono,
				btn_aceptar,
				btn_rechazar
				)));
		this.setFocusTraversalPolicyProvider(true);
		}
	

}
