package com.tp.interfaces.habitaciones;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;
import com.tp.interfaces.misc.*;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.LineBorder;

public class MenuEstadoHabitaciones extends JPanel {

	public static String titulo = "Estado Habitaciones";
	public static int x_bound = 660;
	public static int y_bound = 500;

	private JFrame ventana_contenedora;
	private JPanel encabezado;
	private JTable jtable_habitaciones;
	private JLabel lbl_fecha_desde;
	private JLabel lbl_fecha_hasta;
	private JButton jb_cancelar;
	private JButton btn_siguiente;
	private JLabel lbl_error_fecha_desde;
	private JLabel lbl_error_fecha_hasta;
	private JDateChooser dc_fecha_desde;
	private JDateChooser dc_fecha_hasta;
	
	public MenuEstadoHabitaciones(JFrame ventana_contenedora, Encabezado encabezado) {
		setBackground(Color.WHITE);
		this.ventana_contenedora = ventana_contenedora;
		setLayout(null);
		
		this.encabezado = encabezado;
		encabezado.setBounds(0, 0, 640, 110);
		add(encabezado);
		
		lbl_fecha_desde = new JLabel("Desde Fecha:");
		lbl_fecha_desde.setBounds(123, 145, 110, 14);
		add(lbl_fecha_desde);
		
		lbl_fecha_hasta = new JLabel("Hasta Fecha:");
		lbl_fecha_hasta.setBounds(327, 145, 110, 14);
		add(lbl_fecha_hasta);
		
		jb_cancelar = new JButton("Cancelar");
		jb_cancelar.setBounds(427, 419, 89, 30);
		add(jb_cancelar);
		
		btn_siguiente = new JButton("Siguiente");
		btn_siguiente.setBounds(526, 419, 89, 30);
		add(btn_siguiente);
		
		jtable_habitaciones = new JTable();
		jtable_habitaciones.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jtable_habitaciones.setBounds(10, 195, 620, 200);
		add(jtable_habitaciones);
		
		lbl_error_fecha_desde = new JLabel("");
		lbl_error_fecha_desde.setForeground(Color.RED);
		lbl_error_fecha_desde.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lbl_error_fecha_desde.setBounds(142, 163, 180, 10);
		add(lbl_error_fecha_desde);
		
		lbl_error_fecha_hasta = new JLabel("");
		lbl_error_fecha_hasta.setForeground(Color.RED);
		lbl_error_fecha_hasta.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lbl_error_fecha_hasta.setBounds(346, 163, 180, 10);
		add(lbl_error_fecha_hasta);
		
		dc_fecha_desde = new JDateChooser("dd/MM/yyyy", "##/##/####", '_');
		dc_fecha_desde.setBounds(212, 142, 100, 20);
		add(dc_fecha_desde);
		
		dc_fecha_hasta = new JDateChooser("dd/MM/yyyy", "##/##/####", '_');
		dc_fecha_hasta.setBounds(411, 142, 100, 20);
		add(dc_fecha_hasta);
	}
}
