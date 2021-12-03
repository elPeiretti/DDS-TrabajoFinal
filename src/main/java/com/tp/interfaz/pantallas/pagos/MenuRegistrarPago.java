package com.tp.interfaz.pantallas.pagos;

import java.awt.Color;
import java.util.List;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;
import com.tp.interfaz.pantallas.misc.Encabezado;
import com.tp.interfaz.pantallas.misc.JTextFieldLimit;
import com.tp.interfaz.pantallas.misc.ResultPane;
import com.tp.interfaz.pantallas.misc.TabOrder;
@SuppressWarnings("all")
public class MenuRegistrarPago extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7782651949132883191L;
	public static String titulo = "Registrar Pago";
	public static int x_bound = 660;
	public static int y_bound = 500;

	@SuppressWarnings("unused")
	private JFrame ventana_contenedora;
	private JTextField tf_nro_factura;
	@SuppressWarnings("unused")
	private JPanel encabezado;
	private JButton jb_agregar_pago;
	private JButton jb_siguiente;
	private JButton jb_cancelar;
	private JPanel rp_pagos;
	private JTextField tf_nombre_de;
	private JTextField tf_total_pagar;
	private JComboBox cb_medio_pago;
	private JTextField tf_cotiz;
	private JTextField tf_monto_recibido;
	private JTextField tf_cvv;
	private JTextField tf_vencimiento;
	private JTextField tf_nro_tarj;
	private JTextField tf_nro_cheque;
	private JTextField tf_plaza;
	private JComboBox cb_banco;
	private JComboBox cb_cuotas;
	private JComboBox cb_tarj_deb;
	private JComboBox cb_tarj_cred;
	private JComboBox cb_moneda;
	private JDateChooser dc_fecha_cobro;
	
	public MenuRegistrarPago(JFrame ventana_contenedora, Encabezado encabezado) {
		setBackground(Color.WHITE);
		this.ventana_contenedora = ventana_contenedora;
		setLayout(null);
		
		JLabel lbl_factura_nro = new JLabel("Factura N°");
		lbl_factura_nro.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_factura_nro.setBounds(81, 107, 118, 30);
		add(lbl_factura_nro);
		
		tf_nro_factura = new JTextField();
		tf_nro_factura.setBounds(59, 135, 100, 20);
		add(tf_nro_factura);
		tf_nro_factura.setDocument(new JTextFieldLimit(30));
		tf_nro_factura.setColumns(30);
		
		this.encabezado = encabezado;
		encabezado.setSize(640, 110);
		encabezado.setLocation(0, 0);
		add(encabezado);
		
		jb_agregar_pago = new JButton("Agregar Pago");
		jb_agregar_pago.setBounds(512, 321, 118, 30);
		add(jb_agregar_pago);
		
		jb_siguiente = new JButton("Siguiente");
		jb_siguiente.setBounds(450, 563, 100, 30);
		add(jb_siguiente);
		
		jb_cancelar = new JButton("Cancelar");
		jb_cancelar.setBounds(81, 563, 100, 30);
		add(jb_cancelar);
		
		rp_pagos = new ResultPane();
		rp_pagos.setBounds(10, 373, 620, 180);
		add(rp_pagos);
		
		tf_nombre_de = new JTextField();
		tf_nombre_de.setColumns(30);
		tf_nombre_de.setBounds(239, 135, 155, 20);
		add(tf_nombre_de);
		
		JLabel lbl_nombre_de = new JLabel("A nombre de");
		lbl_nombre_de.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_nombre_de.setBounds(273, 114, 83, 16);
		add(lbl_nombre_de);
		
		tf_total_pagar = new JTextField();
		tf_total_pagar.setColumns(30);
		tf_total_pagar.setBounds(475, 135, 111, 20);
		add(tf_total_pagar);
		
		JLabel lbl_total_pagar = new JLabel("Total a Pagar (AR$)");
		lbl_total_pagar.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_total_pagar.setBounds(475, 114, 111, 16);
		add(lbl_total_pagar);
		
		JLabel lbl_medio_pago = new JLabel("Medio de pago");
		lbl_medio_pago.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_medio_pago.setBounds(10, 177, 83, 16);
		add(lbl_medio_pago);
		
		JLabel lbl_moneda = new JLabel("Moneda");
		lbl_moneda.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_moneda.setBounds(10, 211, 83, 16);
		add(lbl_moneda);
		
		JLabel lbl_cotiz = new JLabel("Cotización (AR$)");
		lbl_cotiz.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_cotiz.setBounds(10, 245, 83, 16);
		add(lbl_cotiz);
		
		JLabel lbl_monto_recibido = new JLabel("Monto recibido (*)");
		lbl_monto_recibido.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_monto_recibido.setBounds(10, 279, 94, 16);
		add(lbl_monto_recibido);
		
		cb_medio_pago = new JComboBox();
		cb_medio_pago.setBounds(103, 177, 100, 24);
		add(cb_medio_pago);
		
		cb_moneda = new JComboBox();
		cb_moneda.setBounds(103, 211, 65, 24);
		add(cb_moneda);
		
		tf_cotiz = new JTextField();
		tf_cotiz.setColumns(30);
		tf_cotiz.setBounds(103, 245, 65, 20);
		add(tf_cotiz);
		
		tf_monto_recibido = new JTextField();
		tf_monto_recibido.setColumns(30);
		tf_monto_recibido.setBounds(103, 279, 100, 20);
		add(tf_monto_recibido);
		
		JLabel lbl_tarj_deb = new JLabel("Tarjéta de débito");
		lbl_tarj_deb.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_tarj_deb.setBounds(218, 177, 83, 16);
		add(lbl_tarj_deb);
		
		JLabel lbl_tarj_cred = new JLabel("Tarjéta de crédito");
		lbl_tarj_cred.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_tarj_cred.setBounds(218, 211, 100, 16);
		add(lbl_tarj_cred);
		
		JLabel lbl_nro_tarjeta = new JLabel("Nro. Tarjeta");
		lbl_nro_tarjeta.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_nro_tarjeta.setBounds(218, 245, 83, 16);
		add(lbl_nro_tarjeta);
		
		JLabel lbl_cvv = new JLabel("CVV");
		lbl_cvv.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_cvv.setBounds(218, 279, 34, 16);
		add(lbl_cvv);
		
		JLabel lbl_cuotas = new JLabel("Cuotas");
		lbl_cuotas.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_cuotas.setBounds(218, 313, 83, 16);
		add(lbl_cuotas);
		
		cb_tarj_deb = new JComboBox();
		cb_tarj_deb.setBounds(310, 177, 110, 24);
		add(cb_tarj_deb);
		
		cb_tarj_cred = new JComboBox();
		cb_tarj_cred.setBounds(310, 211, 110, 24);
		add(cb_tarj_cred);
		
		cb_cuotas = new JComboBox();
		cb_cuotas.setBounds(310, 313, 110, 24);
		add(cb_cuotas);
		
		tf_cvv = new JTextField();
		tf_cvv.setColumns(30);
		tf_cvv.setBounds(253, 279, 41, 20);
		add(tf_cvv);
		
		tf_vencimiento = new JTextField();
		tf_vencimiento.setColumns(30);
		tf_vencimiento.setBounds(366, 279, 54, 20);
		add(tf_vencimiento);
		
		JLabel lbl_vencimiento = new JLabel("Vencimiento");
		lbl_vencimiento.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_vencimiento.setBounds(304, 279, 65, 16);
		add(lbl_vencimiento);
		
		tf_nro_tarj = new JTextField();
		tf_nro_tarj.setColumns(30);
		tf_nro_tarj.setBounds(290, 245, 130, 20);
		add(tf_nro_tarj);
		
		JLabel lbl_nro_cheque = new JLabel("N° de cheque");
		lbl_nro_cheque.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_nro_cheque.setBounds(430, 177, 83, 16);
		add(lbl_nro_cheque);
		
		JLabel lbl_plaza = new JLabel("Plaza");
		lbl_plaza.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_plaza.setBounds(430, 211, 83, 16);
		add(lbl_plaza);
		
		JLabel lbl_banco = new JLabel("Banco");
		lbl_banco.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_banco.setBounds(430, 245, 83, 16);
		add(lbl_banco);
		
		JLabel lbl_fecha_cobro = new JLabel("Fecha de Cobro");
		lbl_fecha_cobro.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_fecha_cobro.setBounds(430, 279, 83, 16);
		add(lbl_fecha_cobro);
		
		tf_nro_cheque = new JTextField();
		tf_nro_cheque.setColumns(30);
		tf_nro_cheque.setBounds(512, 177, 118, 20);
		add(tf_nro_cheque);
		
		tf_plaza = new JTextField();
		tf_plaza.setColumns(30);
		tf_plaza.setBounds(512, 211, 118, 20);
		add(tf_plaza);
		
		cb_banco = new JComboBox();
		cb_banco.setBounds(512, 245, 118, 24);
		add(cb_banco);
		
		dc_fecha_cobro = new JDateChooser("yyyy/MM/dd", "####/##/##", '_');
		dc_fecha_cobro.setBounds(530,279,100,20);
		add(dc_fecha_cobro);
		
		this.agregarTabOrder();
	}
	private void agregarTabOrder() {
		this.setFocusTraversalPolicy(new TabOrder(List.of(
				cb_medio_pago,
				cb_moneda,
				tf_cotiz,
				tf_monto_recibido,
				cb_tarj_deb,
				cb_tarj_cred,
				tf_nro_tarj,
				tf_cvv,
				tf_vencimiento,
				cb_cuotas,
				tf_nro_cheque,
				tf_plaza,
				cb_banco,
				dc_fecha_cobro,
				jb_agregar_pago,
				jb_siguiente,
				jb_cancelar
				)));
		this.setFocusTraversalPolicyProvider(true);
		}
}
