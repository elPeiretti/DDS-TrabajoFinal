package com.tp.interfaces.facturacion;

import java.awt.Color;
import java.util.List;

import javax.swing.*;

import com.tp.interfaces.misc.Encabezado;
import com.tp.interfaces.misc.JTextFieldLimit;
import com.tp.interfaces.misc.ResultPane;
import com.tp.interfaces.misc.TabOrder;

public class MenuFacturar extends JPanel {

	public static String titulo = "Facturar";
	public static int x_bound = 660;
	public static int y_bound = 500;

	private JFrame ventana_contenedora;
	private JTextField jtf_num_hab;
	private JLabel lbl_num_hab;
	private JPanel encabezado;
	private JButton jb_buscar;
	private JButton jb_siguiente;
	private JButton jb_cancelar;
	private JPanel rp_facturas;
	private JTextField tf_salida;
	private JCheckBox chbx_tercero;
	private JTextField tf_cuit;
	private JLabel lbl_raz_social;
	
	public MenuFacturar(JFrame ventana_contenedora, Encabezado encabezado)  {
		setBackground(Color.WHITE);
		this.ventana_contenedora = ventana_contenedora;
		setLayout(null);
		
		lbl_num_hab = new JLabel("<html>Número de<br/>Habitación (*):</html>");
		lbl_num_hab.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_num_hab.setBounds(81, 138, 118, 23);
		add(lbl_num_hab);
		
		jtf_num_hab = new JTextField();
		jtf_num_hab.setBounds(165, 137, 140, 24);
		add(jtf_num_hab);
		jtf_num_hab.setDocument(new JTextFieldLimit(30));
		jtf_num_hab.setColumns(30);
		
		this.encabezado = encabezado;
		encabezado.setSize(640, 110);
		encabezado.setLocation(0, 0);
		add(encabezado);
		
		jb_buscar = new JButton("Buscar");
		jb_buscar.setBounds(450, 189, 100, 30);
		add(jb_buscar);
		
		jb_siguiente = new JButton("Siguiente");
		jb_siguiente.setBounds(450, 488, 100, 30);
		add(jb_siguiente);
		
		jb_cancelar = new JButton("Cancelar");
		jb_cancelar.setBounds(81, 488, 100, 30);
		add(jb_cancelar);
		
		rp_facturas = new ResultPane();
		rp_facturas.setBounds(10, 230, 620, 180);
		add(rp_facturas);
		
		tf_salida = new JTextField();
		tf_salida.setColumns(30);
		tf_salida.setBounds(401, 137, 149, 24);
		add(tf_salida);
		
		JLabel lbl_hora_salida = new JLabel("<html>Hora de<br/>Salida (*):</html>");
		lbl_hora_salida.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_hora_salida.setBounds(326, 138, 118, 23);
		add(lbl_hora_salida);
		
		chbx_tercero = new JCheckBox("Factura a Tercero");
		chbx_tercero.setBounds(84, 421, 172, 23);
		add(chbx_tercero);
		
		JLabel lbl_cuit = new JLabel("CUIT (*)");
		lbl_cuit.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_cuit.setBounds(313, 425, 91, 14);
		add(lbl_cuit);
		
		tf_cuit = new JTextField();
		tf_cuit.setColumns(30);
		tf_cuit.setBounds(410, 421, 140, 23);
		add(tf_cuit);
		
		JLabel lbl_raz_social_tag = new JLabel("Razón Social:");
		lbl_raz_social_tag.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_raz_social_tag.setBounds(286, 456, 118, 14);
		add(lbl_raz_social_tag);
		
		lbl_raz_social = new JLabel("<razón social>");
		lbl_raz_social.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_raz_social.setBounds(420, 456, 130, 14);
		add(lbl_raz_social);
		
		this.agregarTabOrder();
	}
	private void agregarTabOrder() {
		this.setFocusTraversalPolicy(new TabOrder(List.of(
				jtf_num_hab,
				tf_salida,
				jb_buscar,
				chbx_tercero,
				tf_cuit,
				jb_siguiente,
				jb_cancelar
				)));
		this.setFocusTraversalPolicyProvider(true);
		}
}
