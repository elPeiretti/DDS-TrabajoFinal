package com.tp.interfaces.facturacion;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.*;
import javax.swing.RowSorter.SortKey;
import javax.swing.event.RowSorterEvent;
import javax.swing.event.RowSorterListener;
import javax.swing.text.MaskFormatter;

import com.tp.dto.BusqPasajeroDTO;
import com.tp.dto.FacturarDTO;
import com.tp.dto.PasajeroDTO;
import com.tp.gestores.GestorHabitaciones;
import com.tp.gestores.GestorPasajeros;
import com.tp.interfaces.MenuPrincipal;
import com.tp.interfaces.SeteableTab;
import com.tp.interfaces.VentanaPrincipal;
import com.tp.interfaces.misc.Encabezado;
import com.tp.interfaces.misc.JTextFieldLimit;
import com.tp.interfaces.misc.Mensaje;
import com.tp.interfaces.misc.ResultPane;
import com.tp.interfaces.misc.TabOrder;
import com.tp.interfaces.pasajeros.MenuAltaPasajero;
import com.tp.interfaces.pasajeros.MenuModificarPasajero;

public class MenuFacturar extends JPanel implements SeteableTab {

	public static String titulo = "Facturar";
	public static int x_bound = 655;
	public static int y_bound = 595;

	private JFrame ventana_contenedora;
	private JTextField jtf_num_hab;
	private JLabel lbl_num_hab;
	private Encabezado encabezado;
	private JButton jb_buscar;
	private JButton jb_siguiente;
	private JButton jb_cancelar;
	private ResultPane<PasajeroDTO> rp_pasajeros;
	private JFormattedTextField jftf_salida;
	private JCheckBox chbx_tercero;
	private JFormattedTextField jftf_cuit;
	private JLabel lbl_raz_social;
	private JLabel lbl_error_cuit;
	private JLabel lbl_error_salida;
	private JLabel lbl_error_num_hab;
	private HashMap<String,Boolean> campos_validos;
	private FacturarDTO criterios_actuales;
	private Map<Integer,FacturarDTO.columnaOrden> indice_columnas;
	private boolean cuit_activo;
	private boolean tabla_vacia;
	
	public MenuFacturar(JFrame ventana_contenedora, Encabezado encabezado)  {
		setBackground(Color.WHITE);
		this.ventana_contenedora = ventana_contenedora;
		setLayout(null);
		
		this.campos_validos = new HashMap<String,Boolean>();
		cuit_activo = false;
		tabla_vacia = true;
		
		lbl_num_hab = new JLabel("<html>Número de<br/>Habitación (*):</html>");
		lbl_num_hab.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_num_hab.setBounds(82, 133, 80, 30);
		add(lbl_num_hab);
		
		jtf_num_hab = new JTextField();
		jtf_num_hab.setBounds(165, 137, 140, 24);
		add(jtf_num_hab);
		jtf_num_hab.setDocument(new JTextFieldLimit(5));
		jtf_num_hab.setColumns(5);
		
		this.encabezado = encabezado;
		encabezado.setSize(640, 110);
		encabezado.setLocation(0, 0);
		add(encabezado);
		
		jb_buscar = new JButton("Buscar");
		jb_buscar.setBounds(450, 189, 100, 30);
		add(jb_buscar);
		
		jb_siguiente = new JButton("Siguiente");
		jb_siguiente.setBounds(450, 513, 100, 30);
		add(jb_siguiente);
		
		jb_cancelar = new JButton("Cancelar");
		jb_cancelar.setBounds(81, 513, 100, 30);
		add(jb_cancelar);
		
		rp_pasajeros = new ResultPane();
		rp_pasajeros.setBounds(10, 230, 620, 180);
		add(rp_pasajeros);
		
		jftf_salida = new JFormattedTextField();
		jftf_salida.setColumns(5);
		jftf_salida.setBounds(401, 137, 149, 24);
		add(jftf_salida);
		try {
			MaskFormatter mf = new MaskFormatter("##:##");
			mf.setPlaceholderCharacter('_');
			mf.install(jftf_salida);
		} catch (ParseException e) {
			Mensaje.mensajeError(new String[]{"Ha ocurrido un error en la creación de la interfaz."});
			e.printStackTrace();
		}
		
		JLabel lbl_hora_salida = new JLabel("<html>Hora de<br/>Salida (*):</html>");
		lbl_hora_salida.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_hora_salida.setBounds(334, 133, 67, 30);
		add(lbl_hora_salida);
		
		chbx_tercero = new JCheckBox("Factura a Tercero");
		chbx_tercero.setBounds(84, 421, 172, 23);
		add(chbx_tercero);
		
		JLabel lbl_cuit = new JLabel("CUIT (*)");
		lbl_cuit.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_cuit.setBounds(356, 425, 48, 14);
		add(lbl_cuit);
		
		jftf_cuit = new JFormattedTextField();
		jftf_cuit.setColumns(10);
		jftf_cuit.setBounds(410, 421, 140, 23);
		add(jftf_cuit);
		try {
			MaskFormatter mf = new MaskFormatter("##-########-#");
			mf.setPlaceholderCharacter('_');
			mf.install(jftf_cuit);
		} catch (ParseException e) {
			Mensaje.mensajeError(new String[]{"Ha ocurrido un error en la creación de la interfaz."});
			e.printStackTrace();
		}
		
		JLabel lbl_raz_social_tag = new JLabel("Razón Social:");
		lbl_raz_social_tag.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_raz_social_tag.setBounds(284, 487, 118, 14);
		add(lbl_raz_social_tag);
		
		lbl_raz_social = new JLabel("<razón social>");
		lbl_raz_social.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_raz_social.setBounds(418, 487, 130, 14);
		add(lbl_raz_social);
		
		lbl_error_num_hab = new JLabel("");
		lbl_error_num_hab.setForeground(Color.RED);
		lbl_error_num_hab.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lbl_error_num_hab.setBounds(165, 165, 172, 14);
		add(lbl_error_num_hab);
		
		lbl_error_salida = new JLabel("");
		lbl_error_salida.setForeground(Color.RED);
		lbl_error_salida.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lbl_error_salida.setBounds(401, 165, 179, 14);
		add(lbl_error_salida);
		
		lbl_error_cuit = new JLabel("");
		lbl_error_cuit.setForeground(Color.RED);
		lbl_error_cuit.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lbl_error_cuit.setBounds(410, 450, 164, 14);
		add(lbl_error_cuit);
		

		this.inicializarCampos();
		this.agregarTabOrder();
		this.agregarActionListeners();
		this.agregarListenersValidacion();
	}
	private void inicializarCampos() {
		campos_validos.put("habitacion", false);
		campos_validos.put("salida", false);
		campos_validos.put("cuit", true);
		
		rp_pasajeros.agregarColumnas(List.of("Apellido","Nombres","Tipo Documento","Número de Documento"), null);
		indice_columnas = new HashMap<Integer,FacturarDTO.columnaOrden>();
		indice_columnas.put(0, FacturarDTO.columnaOrden.APELLIDO);//la clave debe coincidir con el orden en rp_pasajeros
		indice_columnas.put(1, FacturarDTO.columnaOrden.NOMBRES);
		indice_columnas.put(2, FacturarDTO.columnaOrden.TIPODOC);
		indice_columnas.put(3, FacturarDTO.columnaOrden.NRODOC);
	}
	private void agregarListenersValidacion() {
		jftf_cuit.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {}
			public void focusLost(FocusEvent e) {
				validarCuit();
			}
		});
		jtf_num_hab.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {}
			public void focusLost(FocusEvent e) {
				validarNum();
			}
		});
		jftf_salida.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {}
			public void focusLost(FocusEvent e) {
				String data = jftf_salida.getText();
				if(data == "__:__") {
					campos_validos.put("salida", false);
					lbl_error_salida.setText("Este campo no puede estar vacío.");
				}else if (!data.matches("[0-1][0-9]:[0-5][0-9]|2[0-3]:[0-5][0-9]")) {
					campos_validos.put("salida", false);
					lbl_error_salida.setText("La hora ingresada es inválida.");
				}else {
					campos_validos.put("salida",true);
					lbl_error_salida.setText("");
				}
			}
		});
		
	}
	private void agregarActionListeners() {
		chbx_tercero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chbx_tercero.isSelected()) {
					cuit_activo = true;
					validarCuit();
				}else {
					cuit_activo = false;
					campos_validos.put("cuit", true);
					lbl_error_cuit.setText("");
				}
			}
		});
		jb_buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Boolean.TRUE.equals(campos_validos.get("habitacion"))) {//porque .get() puede retornar null
					criterios_actuales = new FacturarDTO();
					criterios_actuales.setHabitacion(jtf_num_hab.getText());
					criterios_actuales.setIdOcupacion(null);
					criterios_actuales.setCantOcupantes(0);
					if(GestorHabitaciones.cargarOcupacionActual(criterios_actuales)) llenarTabla();
				}else {
					validarNum();
				}
			}
		});
		jb_siguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*if(seleccionResponsable()) {
					((VentanaPrincipal)ventana_contenedora).cambiarPanel(new MenuConsumosPorHabitacion(ventana_contenedora,responsableDTO),
							MenuConsumosPorHabitacion.x_bound,MenuConsumosPorHabitacion.y_bound,MenuConsumosPorHabitacion.titulo);
				}*/
			}
		});
		/*jb_siguiente.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				JPanel m;
				String nom;
				int fila = rp_pasajeros.getTable().getSelectedRow();
				if(fila == -1) {
					if(chbx_tercero.isSelected() && campos_validos.get("cuit")) {
						//pasar tercero
					}else {
						//mensaje error
					}
					
				}else {
					//que pasa si hay fila seleccionada y tambien tercero
					//m = new MenuConsumosPorHabitacion(ventana_contenedora,rp_pasajeros.getRowObjects().get(fila));
					//nom = MenuConsumosPorHabitacion.titulo;
				}
				((VentanaPrincipal)ventana_contenedora).cambiarPanel(m,MenuConsumosPorHabitacion.x_bound,MenuConsumosPorHabitacion.y_bound,nom);
			}
		});*/
		
		jb_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opt = Mensaje.mensajeConfirmacion("¿Desea cancelar la facturación?");
				if (opt == 1) { 
					((VentanaPrincipal)ventana_contenedora).cambiarPanel(new MenuPrincipal(ventana_contenedora,encabezado),
							 MenuPrincipal.x_bound,MenuPrincipal.y_bound,MenuPrincipal.titulo);
				}
			}
		});
		rp_pasajeros.agregarRowListener(new RowSorterListener() {
			public void sorterChanged(RowSorterEvent e) {
				if(tabla_vacia) return;
				if(e.getType()  != RowSorterEvent.Type.SORT_ORDER_CHANGED) return;
				SortKey key = e.getSource().getSortKeys().get(0);
				criterios_actuales.setColumna(indice_columnas.get(key.getColumn()));
				criterios_actuales.setSortOrder(key.getSortOrder());
				e.getSource().setSortKeys(List.of(key));//es necesario eliminar las SortKey viejas manualmente
				llenarTabla();
			}
		});
	}
	private void llenarTabla() {
		rp_pasajeros.getContenido().setRowCount(0);
		rp_pasajeros.getRowObjects().clear();
		
		rp_pasajeros.setCantPaginas((long) Math.ceil(criterios_actuales.getCantOcupantes()/8.0));
		List<PasajeroDTO> lp = GestorHabitaciones.getOcupantesBy(criterios_actuales,(rp_pasajeros.getPaginaActual()-1)*8,8);
		
		for(PasajeroDTO p : lp) {
			Vector<String> v = new Vector<String>();
			v.add(p.getApellido());
			v.add(p.getNombres());
			v.add(p.getTipoDocumentoDTO().getTipo());
			v.add(p.getNroDocumento());
			rp_pasajeros.getContenido().addRow(v);
			rp_pasajeros.getRowObjects().add(p);
		}
		tabla_vacia = false;
	}
	private void validarNum() {
		String data = jtf_num_hab.getText();
		if(data.equals("")) {
			campos_validos.put("habitacion", false);
			lbl_error_num_hab.setText("Este campo no puede estar vacío.");
		}else if(!data.matches("([A-Z]*[0-9]*)*")) {
			campos_validos.put("habitacion", false);
			lbl_error_num_hab.setText("El valor ingresado es inválido.");
		}else {
			campos_validos.put("habitacion", true);
			lbl_error_num_hab.setText("");
		}
	}
	private void validarCuit() {
		String data = jftf_cuit.getText();
		if(cuit_activo && data.equals("__-________-_")) {
			lbl_error_cuit.setText("Este campo no puede estar vacío.");
			campos_validos.put("cuit", false);
		}
		else if(cuit_activo && !data.matches("__-________-_|[0-9][0-9]-[0-9]{8}-[0-9]")) {
			lbl_error_cuit.setText("El CUIT ingresado es invalido.");
			campos_validos.put("cuit", false);
		}
		else {
			campos_validos.put("cuit", true);
			lbl_error_cuit.setText("");
		}
	}
	private void agregarTabOrder() {
		this.setFocusTraversalPolicy(new TabOrder(List.of(
				jtf_num_hab,
				jftf_salida,
				jb_buscar,
				chbx_tercero,
				jftf_cuit,
				jb_siguiente,
				jb_cancelar
				)));
		this.setFocusTraversalPolicyProvider(true);
		}
	@Override
	public void setDefaultTab() {
		jtf_num_hab.requestFocus();
	}
}
