package com.tp.interfaces.pasajeros;

import javax.swing.*;

import com.tp.dominio.pasajero.Pasajero;
import com.tp.dto.PasajeroDTO;
import com.tp.dto.TipoDocumentoDTO;
import com.tp.gestores.GestorPasajeros;
import com.tp.interfaces.MenuPrincipal;
import com.tp.interfaces.facturacion.MenuFacturar;
import com.tp.interfaces.misc.*;
import com.tp.interfaces.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class MenuBusquedaPasajero extends JPanel {

	public static String titulo = "Gestionar Pasajero";
	public static int x_bound = 660;
	public static int y_bound = 500;

	private JFrame ventana_contenedora;
	private Encabezado encabezado;
	private JButton jb_siguiente;
	private JButton jb_cancelar;
	private ResultPane<PasajeroDTO> rp_pasajeros;
	private JButton jb_buscar;
	private JTextField jtf_numero_documento;
	private JLabel lbl_numero_documento;
	private JComboBox<TipoDocumentoDTO> jcb_tipo_documento;
	private JLabel lbl_tipo_documento;
	private JLabel lbl_apellido;
	private JTextField jtf_apellido;
	private JLabel lbl_nombres;
	private JTextField jtf_nombres;
	private Map<String,Object> criterios_actuales;
			
	
	public MenuBusquedaPasajero(JFrame ventana_contenedora, Encabezado encabezado) {
		setBackground(Color.WHITE);
		setSize(x_bound, y_bound);
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
		
		rp_pasajeros = new ResultPane<PasajeroDTO>();
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
		
		jcb_tipo_documento = new JComboBox<TipoDocumentoDTO>();
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
		
		rp_pasajeros.getContenido().addColumn("Apellido");
		rp_pasajeros.getContenido().addColumn("Nombres");
		rp_pasajeros.getContenido().addColumn("Tipo Documento");
		rp_pasajeros.getContenido().addColumn("Numero de Documento");
	
		this.inicializarCampos();
		
		this.agregarActionListeners();
		this.agregarTabOrder();
	}
	
	private void inicializarCampos() {
		
		List<TipoDocumentoDTO> ltd = GestorPasajeros.getAllTipoDocumento();
		
		for(TipoDocumentoDTO t : ltd) {
			jcb_tipo_documento.addItem(t);
			if(t.getTipo().equals("DNI")) jcb_tipo_documento.setSelectedItem(t);
		}
		
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
				String nom;
				int fila = rp_pasajeros.getTable().getSelectedRow();
				if(fila == -1) {
					m = new MenuAltaPasajero(ventana_contenedora,encabezado,contexto);
					nom = MenuAltaPasajero.titulo;
				}
				else {
					m = new MenuModificarPasajero(ventana_contenedora,encabezado,contexto,rp_pasajeros.getRowObjects().get(fila));
					nom = MenuModificarPasajero.titulo;
				}
				((VentanaPrincipal)ventana_contenedora).cambiarPanel(m,MenuAltaPasajero.x_bound,MenuAltaPasajero.y_bound,nom);
			}
		});
		
		jb_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((VentanaPrincipal)ventana_contenedora).cambiarPanel(new MenuPrincipal(ventana_contenedora,encabezado),
																	 MenuPrincipal.x_bound,MenuPrincipal.y_bound,MenuPrincipal.titulo);
			}
		});
		
		jb_buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				criterios_actuales = new HashMap<String,Object>();
				if(!jtf_nombres.getText().isEmpty()) {
					criterios_actuales.put("nombres", jtf_nombres.getText());
				}
				if(!jtf_apellido.getText().isEmpty()) {
					criterios_actuales.put("apellido", jtf_apellido.getText());
				}
				if(!jtf_numero_documento.getText().isEmpty()) {
					criterios_actuales.put("tipo_documento", ((TipoDocumentoDTO) jcb_tipo_documento.getSelectedItem()).getIdTipoDocumento());
					criterios_actuales.put("documento", jtf_numero_documento.getText());
				}
				rp_pasajeros.setPaginaActual(1);
				
				llenarTabla();
				
			}
		});
		
		rp_pasajeros.getNextBtn().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				if(rp_pasajeros.getPaginaActual() >= rp_pasajeros.getCantPaginas()) return;
				
				rp_pasajeros.setPaginaActual(rp_pasajeros.getPaginaActual()+1);
				llenarTabla();
				
			}
			
		});
		
		rp_pasajeros.getPrevBtn().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				if(rp_pasajeros.getPaginaActual().equals(1)) return;
				
				rp_pasajeros.setPaginaActual(rp_pasajeros.getPaginaActual()-1);
				llenarTabla();
			}
			
		});
	}
	
	private void llenarTabla() {
		
		rp_pasajeros.getContenido().setRowCount(0);
		rp_pasajeros.getRowObjects().clear();
		
		rp_pasajeros.setCantPaginas((long) Math.ceil(GestorPasajeros.getCountPasajerosBy(criterios_actuales)/8.0));
		List<PasajeroDTO> lp = GestorPasajeros.getPasajerosBy(criterios_actuales, (rp_pasajeros.getPaginaActual()-1)*8, 8);
		
		for(PasajeroDTO p : lp) {
			Vector<String> v = new Vector<String>();
			v.add(p.getApellido());
			v.add(p.getNombres());
			v.add(p.getTipoDocumentoDTO().getTipo());
			v.add(p.getNroDocumento());
			rp_pasajeros.getContenido().addRow(v);
			rp_pasajeros.getRowObjects().add(p);
		}
	}

}
