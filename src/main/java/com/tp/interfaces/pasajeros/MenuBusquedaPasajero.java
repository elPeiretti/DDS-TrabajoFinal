package com.tp.interfaces.pasajeros;

import javax.swing.*;

import com.tp.dominio.pasajero.Pasajero;
import com.tp.interfaces.MenuPrincipal;
import com.tp.interfaces.facturacion.MenuFacturar;
import com.tp.interfaces.misc.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MenuBusquedaPasajero extends JPanel {

	private JFrame ventana_contenedora;
	private Encabezado encabezado;
	private JButton jb_siguiente;
	private JButton jb_cancelar;
	private ResultPane rp_pasajeros;
	private JButton jb_buscar;
	private JTextField jtf_numero_documento;
	private JLabel lbl_numero_documento;
	private JComboBox jcb_tipo_documento;
	private JLabel lbl_tipo_documento;
	private JLabel lbl_apellido;
	private JTextField jtf_apellido;
	private JLabel lbl_nombres;
	private JTextField jtf_nombres;
	private List objetos_en_tabla;
	
	
	public MenuBusquedaPasajero(JFrame ventana_contenedora, Encabezado encabezado) {
		setBackground(Color.WHITE);
		this.ventana_contenedora = ventana_contenedora;
		setLayout(null);
		
		this.encabezado = encabezado;	
		encabezado.setSize(640, 110);
		encabezado.setLocation(0, 0);
		add(encabezado);
		
		jb_siguiente = new JButton("Siguiente");
		jb_siguiente.setBounds(450, 420, 100, 30);
		add(jb_siguiente);
		
		jb_cancelar = new JButton("Cancelar");
		jb_cancelar.setBounds(81, 420, 100, 30);
		add(jb_cancelar);
		
		rp_pasajeros = new ResultPane();
		rp_pasajeros.setBounds(10, 230, 620, 180);
		add(rp_pasajeros);
		
		jb_buscar = new JButton("Buscar");
		jb_buscar.setBounds(450, 187, 100, 30);
		add(jb_buscar);
		
		jtf_numero_documento = new JTextField();
		jtf_numero_documento.setColumns(15);
		jtf_numero_documento.setBounds(410, 149, 140, 20);
		jtf_numero_documento.setDocument(new JTextFieldLimit(15));
		add(jtf_numero_documento);
		
		lbl_numero_documento = new JLabel("<html>N\u00FAmero de<br>Documento:</html>");
		lbl_numero_documento.setBounds(329, 146, 100, 30);
		add(lbl_numero_documento);
		
		jcb_tipo_documento = new JComboBox();
		jcb_tipo_documento.setBounds(155, 149, 140, 20);
		add(jcb_tipo_documento);
		
		lbl_tipo_documento = new JLabel("<html>Tipo de<br>Documento:</html>");
		lbl_tipo_documento.setBounds(81, 146, 130, 30);
		add(lbl_tipo_documento);
		
		lbl_apellido = new JLabel("Apellido:");
		lbl_apellido.setBounds(81, 113, 64, 14);
		add(lbl_apellido);
		
		jtf_apellido = new JTextField();
		jtf_apellido.setColumns(30);
		jtf_apellido.setBounds(155, 110, 140, 20);
		jtf_apellido.setDocument(new JTextFieldLimit(30));
		add(jtf_apellido);
		
		lbl_nombres = new JLabel("Nombres:");
		lbl_nombres.setBounds(329, 113, 61, 14);
		add(lbl_nombres);
		
		jtf_nombres = new JTextField();
		jtf_nombres.setColumns(50);
		jtf_nombres.setBounds(410, 110, 140, 20);
		jtf_nombres.setDocument(new JTextFieldLimit(50));
		add(jtf_nombres);
	
		this.agregarActionListeners();
		this.agregarTabOrder();
	}
	
	private void agregarTabOrder() {
		this.setFocusTraversalPolicy(new TabOrder(List.of(
				jtf_apellido, jtf_nombres, jcb_tipo_documento,
				jtf_numero_documento, jb_buscar, jb_siguiente, jb_cancelar
				)));
		this.setFocusTraversalPolicyProvider(true);
	}
	
	public void agregarActionListeners() {
		MenuBusquedaPasajero contexto = this;
		jb_siguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel m;
				int fila = rp_pasajeros.jtable_resultados.getSelectedRow();
				if(fila == -1) {
					m = new MenuAltaPasajero(ventana_contenedora,encabezado,contexto);
				}
				else {
					m = new MenuModificarPasajero(ventana_contenedora,encabezado);
					//((MenuModificarPasajero) m).cargarPasajero(objetos_en_tabla.get(fila));
				}
				ventana_contenedora.setContentPane(m);
				ventana_contenedora.setVisible(true);
			}
		});
		
		jb_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana_contenedora.setContentPane(new MenuPrincipal(ventana_contenedora, encabezado));
				ventana_contenedora.setVisible(true);
				
			}
		});
	}

}
