package com.tp.interfaz.pantallas.habitaciones;


import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import com.tp.interfaz.dto.FechaDTO;
import com.tp.interfaz.dto.HabitacionDTO;
import com.tp.interfaz.dto.OcupacionDTO;
import com.tp.interfaz.dto.ReservaDTO;
import com.tp.interfaz.pantallas.MenuPrincipal;
import com.tp.interfaz.pantallas.SeteableTab;
import com.tp.interfaz.pantallas.VentanaPrincipal;
import com.tp.interfaz.pantallas.habitaciones.ocupaciones.MenuBuscarResponsable;
import com.tp.interfaz.pantallas.misc.*;
import com.tp.interfaz.pantallas.misc.columngroup.*;
import com.tp.logica.gestores.GestorHabitaciones;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class MenuEstadoHabitaciones extends JPanel implements SeteableTab {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6469104100050406245L;
	public static String titulo = "Estado Habitaciones";
	public static int x_bound = 660;
	public static int y_bound = 500;

	private JFrame ventana_contenedora;
	private Encabezado encabezado;
	private JTable jtable_habitaciones;
	private DefaultTableModel jtable_habitaciones_contenido;
	private JScrollPane jspane_habitaciones;
	private JLabel lbl_fecha_desde;
	private JLabel lbl_fecha_hasta;
	private JButton jb_cancelar;
	private JButton jb_siguiente;
	private JLabel lbl_error_fecha_desde;
	private JLabel lbl_error_fecha_hasta;
	private JDateChooser dc_fecha_desde;
	private JDateChooser dc_fecha_hasta;
	private HashMap<String,Boolean> campos_validos;
	private HabitacionesTable fct_habitaciones;
	private Date prev_desde;
	private Date prev_hasta;
	
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
		
		jb_siguiente = new JButton("Siguiente");
		jb_siguiente.setBounds(526, 419, 89, 30);
		add(jb_siguiente);

		jtable_habitaciones_contenido = new HabitacionesTableModel();
		jtable_habitaciones = new PintableTable(jtable_habitaciones_contenido);

		jtable_habitaciones.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		jspane_habitaciones = new JScrollPane(jtable_habitaciones);
		jspane_habitaciones.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jspane_habitaciones.setBounds(10, 195, 620, 200);
		add(jspane_habitaciones);

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
		dc_fecha_desde.setDate(new Date());
		add(dc_fecha_desde);
		
		dc_fecha_hasta = new JDateChooser("dd/MM/yyyy", "##/##/####", '_');
		dc_fecha_hasta.setBounds(411, 142, 100, 20);
		dc_fecha_hasta.setMinSelectableDate(new Date());
		add(dc_fecha_hasta);

		EnterActionAssigner.setEnterAction(List.of(jb_cancelar,jb_siguiente));
		this.campos_validos = new HashMap<String,Boolean>();
		this.inicializarMapa();
		this.agregarActionListeners();
		this.agregarTabOrder();
	}
	
	private void agregarTabOrder() {
		this.setFocusTraversalPolicy(new TabOrder(List.of(
				dc_fecha_desde.getDateEditor().getUiComponent(),
				dc_fecha_hasta.getDateEditor().getUiComponent(),
				jb_siguiente,
				jb_cancelar
				)));
		this.setFocusTraversalPolicyProvider(true);
		}

	private void inicializarMapa() {
		campos_validos.put("fecha desde", true);
		campos_validos.put("fecha hasta", false);
	}

	private void agregarActionListeners() {
		MenuEstadoHabitaciones contexto = this;
		
		jb_siguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( ((PintableTable)jtable_habitaciones).isSeleccionando() || ((PintableTable)jtable_habitaciones).getCeldaFinal().getX() == -1 ){
					Mensaje.mensajeInformacion("Debe seleccionar al menos una habitación para continuar.");
					return;
				}

				Point inicio = ((PintableTable)jtable_habitaciones).getCeldaInicial();
				Point fin = ((PintableTable)jtable_habitaciones).getCeldaFinal();
				
				String numeroHabitacion = (String) jtable_habitaciones.getColumnModel().getColumn(inicio.x).getHeaderValue();
				LocalDate fechaInicio = LocalDate.parse(((String) fct_habitaciones.getFechasTable().getValueAt(inicio.y, 0)),DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				LocalDate fechaFin = LocalDate.parse(((String) fct_habitaciones.getFechasTable().getValueAt(fin.y, 0)),DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				List<ReservaDTO> reservas = GestorHabitaciones.getReservasVigentesInRange(fechaInicio,fechaFin,numeroHabitacion);
				
				if(!reservas.isEmpty()){
					if(Mensaje.mensajeOcuparIgual(reservas)!=1) return;
				}
								
				OcupacionDTO ocupacion = new OcupacionDTO();
				ocupacion.setFechaIngreso(fechaInicio);
				ocupacion.setFechaEgreso(fechaFin);
				ocupacion.getHabitacion().setNumero(numeroHabitacion);
				
				((VentanaPrincipal)ventana_contenedora).cambiarPanel(new MenuBuscarResponsable(ventana_contenedora,encabezado,ocupacion, contexto),MenuBuscarResponsable.x_bound,MenuBuscarResponsable.y_bound,MenuBuscarResponsable.titulo);
				
			}
			
		});
		
		jb_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 ((VentanaPrincipal)ventana_contenedora).cambiarPanel(new MenuPrincipal(ventana_contenedora,encabezado),MenuPrincipal.x_bound,MenuPrincipal.y_bound,MenuPrincipal.titulo);
			}
		});

		dc_fecha_desde.getDateEditor().getUiComponent().addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {}
			
			@SuppressWarnings("deprecation")
			public void focusLost(FocusEvent e) {
				Date desde = dc_fecha_desde.getDate();
				Date hasta = dc_fecha_hasta.getDate();
				Date hoy = new Date();
				hoy.setHours(0);
				if(desde != null) desde.setHours(0);
				if (hasta != null) hasta.setHours(0);
				
				if (dc_fecha_desde.getDate() == null){
					if (((JTextField) dc_fecha_desde.getDateEditor().getUiComponent()).getText().equals("__/__/____"))
						lbl_error_fecha_desde.setText("Este campo no puede estar vacío.");
					else lbl_error_fecha_desde.setText("La fecha posee un formato invalido.");
					campos_validos.put("fecha desde", false); 
				}
				else {
					campos_validos.put("fecha desde", true);
					dc_fecha_hasta.setMinSelectableDate(dc_fecha_desde.getDate());
					lbl_error_fecha_desde.setText("");
					if (campos_validos.get("fecha hasta") && hasta.toInstant().truncatedTo(ChronoUnit.DAYS).compareTo(desde.toInstant().truncatedTo(ChronoUnit.DAYS))<0){
						lbl_error_fecha_hasta.setText("La fecha no puede ser anterior a la fecha desde.");
						campos_validos.put("fecha hasta", false);
					}
					else if (campos_validos.get("fecha hasta")){
						if(desde.equals(prev_desde) && hasta.equals(prev_hasta)) return;
						prev_desde = desde;
						prev_hasta = hasta;
						((PintableTable) jtable_habitaciones).limpiarCeldasSeleccionadas();
						List<FechaDTO> l = GestorHabitaciones.buscarEstadoHabitaciones(desde.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), hasta.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
						llenarTabla(l);
					}
				}
				
			}
		});
		
		dc_fecha_desde.addPropertyChangeListener(new PropertyChangeListener() {
			@SuppressWarnings("deprecation")
			public void propertyChange(PropertyChangeEvent e) {
				Date desde = dc_fecha_desde.getDate();
				Date hasta = dc_fecha_hasta.getDate();
				if(desde != null) desde.setHours(0);
				if (hasta != null) hasta.setHours(0);

				if("date".equals(e.getPropertyName())) {
					campos_validos.put("fecha desde", true);
					lbl_error_fecha_desde.setText("");
					dc_fecha_hasta.setMinSelectableDate(dc_fecha_desde.getDate());
					if(!campos_validos.get("fecha hasta")) {
						if(hasta != null && hasta.toInstant().truncatedTo(ChronoUnit.DAYS).compareTo(desde.toInstant().truncatedTo(ChronoUnit.DAYS))>=0) {
						campos_validos.put("fecha hasta",true);
						lbl_error_fecha_hasta.setText("");
						}
					}
					if(campos_validos.get("fecha hasta")){
						if(hasta.toInstant().truncatedTo(ChronoUnit.DAYS).compareTo(desde.toInstant().truncatedTo(ChronoUnit.DAYS))<0){
							campos_validos.put("fecha hasta",false);
							lbl_error_fecha_hasta.setText("La fecha no puede ser anterior a la fecha desde.");
						}
						else{
							if(desde.equals(prev_desde) && hasta.equals(prev_hasta)) return;
							prev_desde = desde;
							prev_hasta = hasta;
							((PintableTable) jtable_habitaciones).limpiarCeldasSeleccionadas();
							List<FechaDTO> l = GestorHabitaciones.buscarEstadoHabitaciones(desde.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), hasta.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
							llenarTabla(l);
						}
					} 
				}
			}
		});
		
		dc_fecha_hasta.getDateEditor().getUiComponent().addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {}
			@SuppressWarnings("deprecation")
			public void focusLost(FocusEvent e) {
				Date desde = dc_fecha_desde.getDate();
				Date hasta = dc_fecha_hasta.getDate();
				Date hoy = new Date();
				hoy.setHours(0);
				if(desde != null) desde.setHours(0);
				if (hasta != null) hasta.setHours(0);

				if (dc_fecha_hasta.getDate() == null){
					if (((JTextField) dc_fecha_hasta.getDateEditor().getUiComponent()).getText().equals("__/__/____"))
						lbl_error_fecha_hasta.setText("Este campo no puede estar vacío.");
					else lbl_error_fecha_hasta.setText("La fecha posee un formato invalido.");
					campos_validos.put("fecha hasta", false); 
				} 
				else if (campos_validos.get("fecha desde") && hasta.toInstant().truncatedTo(ChronoUnit.DAYS).compareTo(desde.toInstant().truncatedTo(ChronoUnit.DAYS))<0){
					lbl_error_fecha_hasta.setText("La fecha no puede ser anterior a la fecha desde.");
					campos_validos.put("fecha hasta", false);
				}
				else {
					campos_validos.put("fecha hasta", true);
					lbl_error_fecha_hasta.setText("");

					if (campos_validos.get("fecha desde")){
						if(desde.equals(prev_desde) && hasta.equals(prev_hasta)) return;
						prev_desde = desde;
						prev_hasta = hasta;
						((PintableTable) jtable_habitaciones).limpiarCeldasSeleccionadas();
						List<FechaDTO> l = GestorHabitaciones.buscarEstadoHabitaciones(desde.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), hasta.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
						llenarTabla(l);
					}
				}
				
			}
		});
		
		dc_fecha_hasta.addPropertyChangeListener(new PropertyChangeListener() {
			@SuppressWarnings("deprecation")
			public void propertyChange(PropertyChangeEvent e) {
				Date desde = dc_fecha_desde.getDate();
				Date hasta = dc_fecha_hasta.getDate();
				if(desde != null) desde.setHours(0);
				if (hasta != null) hasta.setHours(0);

				if("date".equals(e.getPropertyName())) {
					if(campos_validos.get("fecha desde")){
						if(hasta != null && hasta.toInstant().truncatedTo(ChronoUnit.DAYS).compareTo(desde.toInstant().truncatedTo(ChronoUnit.DAYS))>=0) {
							campos_validos.put("fecha hasta", true);
							lbl_error_fecha_hasta.setText("");
						} else {
							campos_validos.put("fecha hasta", false);
							lbl_error_fecha_hasta.setText("La fecha no puede ser anterior a la fecha desde.");	
							return;
						}
						if(desde.equals(prev_desde) && hasta.equals(prev_hasta)) return;
						prev_desde = desde;
						prev_hasta = hasta;
						((PintableTable) jtable_habitaciones).limpiarCeldasSeleccionadas();
						List<FechaDTO> l = GestorHabitaciones.buscarEstadoHabitaciones(desde.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), hasta.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
						llenarTabla(l);
					}
				}
			}
		});
		
	}

	private void llenarTabla(List<FechaDTO> listaFechas) {

		jtable_habitaciones_contenido.setRowCount(0);
		jtable_habitaciones_contenido.setColumnCount(0);

		// agregar columnas y crear grupos
		jtable_habitaciones_contenido.addColumn("Fecha");	
		Map<String,ColumnGroup> grupos_columnas = new HashMap<String,ColumnGroup>();
		
		for(HabitacionDTO h : listaFechas.get(0).getHabitaciones().values().stream().sorted().collect(Collectors.toList())){
			jtable_habitaciones_contenido.addColumn(h.getNumero());
			ColumnGroup grupo = new ColumnGroup(h.getTipoHabitacion());

			List<ColumnGroup> listaGrupos = grupos_columnas.values().stream().collect(Collectors.toList());
			if(grupos_columnas.containsValue(grupo)){
				grupo = listaGrupos.get(listaGrupos.indexOf(grupo));
			}
			grupos_columnas.put(h.getNumero(), grupo);
		}

		//agregar filas
		for(FechaDTO f : listaFechas){
			jtable_habitaciones_contenido.addRow(f.getDataAsStringVector());
		}

		// crear agrupaciones
		TableColumnModel cm = jtable_habitaciones.getColumnModel();
		int i = 1;
		while (i<cm.getColumnCount()){
			TableColumn columna = cm.getColumn(i);
			ColumnGroup grupo = grupos_columnas.get((String)columna.getHeaderValue());
			grupo.add(columna);
			columna.setPreferredWidth(110);
			i++;
		}

		GroupableTableHeader header = (GroupableTableHeader) jtable_habitaciones.getTableHeader();
		for(ColumnGroup g : grupos_columnas.values()){
			header.addColumnGroup(g);
		}
		
		fct_habitaciones = new HabitacionesTable(jspane_habitaciones);
		fct_habitaciones.getFechasTable().setRowHeight(30);
		jtable_habitaciones.setRowHeight(30);
		jtable_habitaciones.requestFocus();
	}

	@Override
	public void setDefaultTab() {
		dc_fecha_desde.getDateEditor().getUiComponent().requestFocus();
	}
	
	
}
