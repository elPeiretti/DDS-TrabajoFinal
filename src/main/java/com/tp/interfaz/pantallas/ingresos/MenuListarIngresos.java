package com.tp.interfaz.pantallas.ingresos;

import java.awt.Color;
import java.util.List;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;
import com.tp.interfaz.pantallas.misc.Encabezado;
import com.tp.interfaz.pantallas.misc.ResultPane;
import com.tp.interfaz.pantallas.misc.TabOrder;

public class MenuListarIngresos extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1662236293627813143L;
	public static String titulo = "Listar Ingresos";
	public static int x_bound = 660;
	public static int y_bound = 500;

	@SuppressWarnings("unused")
	private JFrame ventana_contenedora;
	@SuppressWarnings("unused")
	private JPanel encabezado;
	private JLabel lbl_desde;
	private JButton jb_imp_salir;
	private JPanel rp_ingresos;
	private JDateChooser dc_desde;
	private JDateChooser dc_hasta;
	
	@SuppressWarnings("rawtypes")
	public MenuListarIngresos(JFrame ventana_contenedora, Encabezado encabezado) {

		this.ventana_contenedora = ventana_contenedora;
		this.encabezado = encabezado;
		
		setBackground(Color.WHITE);
		this.ventana_contenedora = ventana_contenedora;
		setLayout(null);
		
		lbl_desde = new JLabel("Desde Fecha");
		lbl_desde.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_desde.setBounds(81, 131, 76, 30);
		add(lbl_desde);
		
		encabezado = new Encabezado();
		encabezado.setSize(640, 110);
		encabezado.setLocation(0, 0);
		add(encabezado);
		
		jb_imp_salir = new JButton("Imprimir y Salir");
		jb_imp_salir.setBounds(428, 420, 122, 30);
		add(jb_imp_salir);
		
		rp_ingresos = new ResultPane();
		rp_ingresos.setBounds(10, 230, 620, 180);
		add(rp_ingresos);
		
		JLabel lbl_hasta = new JLabel("Hasta Fecha");
		lbl_hasta.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_hasta.setBounds(355, 131, 76, 30);
		add(lbl_hasta);
		
		
		dc_desde = new JDateChooser("dd/MM/yyyy", "##/##/####", '_');
		dc_desde.setBounds(158,131,122,30);
		add(dc_desde);
		
		dc_hasta = new JDateChooser("dd/MM/yyyy", "##/##/####", '_');
		dc_hasta.setBounds(428, 131, 122, 30);
		add(dc_hasta);
		
		this.agregarTabOrder();
	}
	
	private void agregarTabOrder() {
		this.setFocusTraversalPolicy(new TabOrder(List.of(
			dc_desde,dc_hasta,jb_imp_salir
				)));
		this.setFocusTraversalPolicyProvider(true);
		}
}
