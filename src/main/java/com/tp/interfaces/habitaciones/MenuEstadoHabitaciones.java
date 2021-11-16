package com.tp.interfaces.habitaciones;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;
import com.tp.gestores.GestorHabitaciones;
import com.tp.interfaces.MenuPrincipal;
import com.tp.interfaces.SeteableTab;
import com.tp.interfaces.misc.*;
import java.awt.*;
import java.awt.event.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.tp.interfaces.*;

import javax.swing.border.LineBorder;

public class MenuEstadoHabitaciones extends JPanel implements SeteableTab {

	public static String titulo = "Estado Habitaciones";
	public static int x_bound = 660;
	public static int y_bound = 500;

	private JFrame ventana_contenedora;
	private Encabezado encabezado;
	private JTable jtable_habitaciones;
	private JLabel lbl_fecha_desde;
	private JLabel lbl_fecha_hasta;
	private JButton jb_cancelar;
	private JButton btn_siguiente;
	private JLabel lbl_error_fecha_desde;
	private JLabel lbl_error_fecha_hasta;
	private JDateChooser dc_fecha_desde;
	private JDateChooser dc_fecha_hasta;
	private HashMap<String,Boolean> campos_validos;
	
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
		dc_fecha_desde.setMinSelectableDate(new Date());
		add(dc_fecha_desde);
		
		dc_fecha_hasta = new JDateChooser("dd/MM/yyyy", "##/##/####", '_');
		dc_fecha_hasta.setBounds(411, 142, 100, 20);
		dc_fecha_hasta.setMinSelectableDate(new Date());
		add(dc_fecha_hasta);

		this.campos_validos = new HashMap<String,Boolean>();
		this.inicializarMapa();
		this.agregarActionListeners();
	}

	private void inicializarMapa() {
		campos_validos.put("fecha desde", false);
		campos_validos.put("fecha hasta", false);
	}

	private void agregarActionListeners() {
		jb_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 ((VentanaPrincipal)ventana_contenedora).cambiarPanel(new MenuPrincipal(ventana_contenedora,encabezado),MenuPrincipal.x_bound,MenuPrincipal.y_bound,MenuPrincipal.titulo);
			}
		});

		dc_fecha_desde.getDateEditor().getUiComponent().addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {}
			
			public void focusLost(FocusEvent e) {

				if (dc_fecha_desde.getDate() == null){
					if (((JTextField) dc_fecha_desde.getDateEditor().getUiComponent()).getText().equals("__/__/____"))
						lbl_error_fecha_desde.setText("Este campo no puede estar vacío.");
					else lbl_error_fecha_desde.setText("La fecha posee un formato invalido.");
					campos_validos.put("fecha desde", false); 
				} 
				else if (dc_fecha_desde.getDate().toInstant().truncatedTo(ChronoUnit.DAYS).compareTo(new Date().toInstant().truncatedTo(ChronoUnit.DAYS))<0) {
					lbl_error_fecha_desde.setText("La fecha no puede ser anterior a la actual.");
					dc_fecha_hasta.setMinSelectableDate(new Date());
					campos_validos.put("fecha desde", false); 
				}
				else {
					campos_validos.put("fecha desde", true);
					dc_fecha_hasta.setMinSelectableDate(dc_fecha_desde.getDate());
					lbl_error_fecha_desde.setText("");
					if (campos_validos.get("fecha hasta") && dc_fecha_hasta.getDate().toInstant().truncatedTo(ChronoUnit.DAYS).compareTo(dc_fecha_desde.getDate().toInstant().truncatedTo(ChronoUnit.DAYS))<0){
						lbl_error_fecha_hasta.setText("La fecha no puede ser anterior a la fecha desde.");
						campos_validos.put("fecha hasta", false);
					}
					else if (campos_validos.get("fecha hasta")){
						GestorHabitaciones.buscarEstadoHabitaciones(dc_fecha_desde.getDate().toInstant(), dc_fecha_hasta.getDate().toInstant());
					}
				}
				
			}
		});
		
		dc_fecha_desde.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent e) {
				if("date".equals(e.getPropertyName())) {
					campos_validos.put("fecha desde", true);
					lbl_error_fecha_desde.setText("");
					dc_fecha_hasta.setMinSelectableDate(dc_fecha_desde.getDate());
				}
			}
		});

		dc_fecha_hasta.getDateEditor().getUiComponent().addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {}
			
			public void focusLost(FocusEvent e) {

				if (dc_fecha_hasta.getDate() == null){
					if (((JTextField) dc_fecha_hasta.getDateEditor().getUiComponent()).getText().equals("__/__/____"))
						lbl_error_fecha_hasta.setText("Este campo no puede estar vacío.");
					else lbl_error_fecha_hasta.setText("La fecha posee un formato invalido.");
					campos_validos.put("fecha hasta", false); 
				} 
				else if (dc_fecha_hasta.getDate().before(new Date())) {
					lbl_error_fecha_hasta.setText("La fecha no puede ser anterior a la actual.");
					campos_validos.put("fecha hasta", false); 
				}
				else if (campos_validos.get("fecha desde") && dc_fecha_hasta.getDate().toInstant().truncatedTo(ChronoUnit.DAYS).compareTo(dc_fecha_desde.getDate().toInstant().truncatedTo(ChronoUnit.DAYS))<0){
					lbl_error_fecha_hasta.setText("La fecha no puede ser anterior a la fecha desde.");
					campos_validos.put("fecha hasta", false);
				}
				else {
					campos_validos.put("fecha hasta", true);
					lbl_error_fecha_hasta.setText("");

					if (campos_validos.get("fecha desde")){
						GestorHabitaciones.buscarEstadoHabitaciones(dc_fecha_desde.getDate().toInstant(), dc_fecha_hasta.getDate().toInstant());
					}
				}
				
			}
		});
		
		dc_fecha_hasta.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent e) {
				if("date".equals(e.getPropertyName())) {
					campos_validos.put("fecha hasta", true);
					lbl_error_fecha_hasta.setText("");
				}
			}
		});
		
	}

	@Override
	public void setDefaultTab() {
		dc_fecha_desde.getDateEditor().getUiComponent().requestFocus();
	}
	
	
}
