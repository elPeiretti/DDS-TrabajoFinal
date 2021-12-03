package com.tp.interfaz.pantallas.pagos;

import java.awt.Color;
import java.util.List;

import javax.swing.*;

import com.tp.interfaz.pantallas.misc.Encabezado;
import com.tp.interfaz.pantallas.misc.JTextFieldLimit;
import com.tp.interfaz.pantallas.misc.ResultPane;
import com.tp.interfaz.pantallas.misc.TabOrder;

public class MenuBusquedaFactura extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -992712718617522380L;
	public static String titulo = "Registrar Pago";
	public static int x_bound = 660;
	public static int y_bound = 500;

	@SuppressWarnings("unused")
	private JFrame ventana_contenedora;
	private JTextField jtf_num_hab;
	private JLabel lbl_num_hab;
	private JPanel encabezado;
	private JButton jb_buscar;
	private JButton jb_siguiente;
	private JButton jb_cancelar;
	private JPanel rp_facturas;
	
	@SuppressWarnings("rawtypes")
	public MenuBusquedaFactura(JFrame ventana_contenedora) {
		setBackground(Color.WHITE);
		this.ventana_contenedora = ventana_contenedora;
		setLayout(null);
		
		lbl_num_hab = new JLabel("<html>Número de<br/>Habitación:</html>");
		lbl_num_hab.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_num_hab.setBounds(103, 131, 118, 30);
		add(lbl_num_hab);
		
		jtf_num_hab = new JTextField();
		jtf_num_hab.setBounds(165, 131, 140, 30);
		add(jtf_num_hab);
		jtf_num_hab.setDocument(new JTextFieldLimit(30));
		jtf_num_hab.setColumns(30);
		
		encabezado = new Encabezado();
		encabezado.setSize(640, 110);
		encabezado.setLocation(0, 0);
		add(encabezado);
		
		jb_buscar = new JButton("Buscar");
		jb_buscar.setBounds(375, 131, 100, 30);
		add(jb_buscar);
		
		jb_siguiente = new JButton("Siguiente");
		jb_siguiente.setBounds(450, 420, 100, 30);
		add(jb_siguiente);
		
		jb_cancelar = new JButton("Cancelar");
		jb_cancelar.setBounds(81, 420, 100, 30);
		add(jb_cancelar);
		
		rp_facturas = new ResultPane();
		rp_facturas.setBounds(10, 230, 620, 180);
		add(rp_facturas);
		
		this.agregarTabOrder();
		
	}
	private void agregarTabOrder() {
		this.setFocusTraversalPolicy(new TabOrder(List.of(
				jtf_num_hab,
				jb_buscar,
				jb_siguiente,
				jb_cancelar
				)));
		this.setFocusTraversalPolicyProvider(true);
		}
	
	
}

