package com.tp.interfaz.pantallas.habitaciones.ocupaciones;

import javax.swing.*;
import javax.swing.RowSorter.SortKey;
import javax.swing.event.RowSorterEvent;
import javax.swing.event.RowSorterListener;

import com.tp.interfaz.dto.BusqPasajeroDTO;
import com.tp.interfaz.dto.HabitacionDTO;
import com.tp.interfaz.dto.OcupacionDTO;
import com.tp.interfaz.dto.PasajeroDTO;
import com.tp.interfaz.dto.TipoDocumentoDTO;
import com.tp.interfaz.pantallas.MenuPrincipal;
import com.tp.interfaz.pantallas.SeteableTab;
import com.tp.interfaz.pantallas.VentanaPrincipal;
import com.tp.interfaz.pantallas.misc.*;
import com.tp.logica.excepciones.HabitacionNoExistenteException;
import com.tp.logica.gestores.GestorHabitaciones;
import com.tp.logica.gestores.GestorPasajeros;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class MenuBuscarAcompaniantes extends JPanel implements SeteableTab {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3713679018825896719L;
	public static String titulo = "Buscar Acompañantes";
	public static int x_bound = 660;
	public static int y_bound = 720;

	private JFrame ventana_contenedora;
	private Encabezado encabezado;
	private JTextField jtf_numero_documento;
	private JTextField jtf_nombres;
	private JTextField jtf_apellido;
	private JComboBox<TipoDocumentoDTO> jcb_tipo_documento;
	private JLabel lbl_numero_documento;
	private JLabel lbl_apellido;
	private JLabel lbl_tipo_documento;
	private ResultPaneAcompaniantes<PasajeroDTO> rp_pasajeros_busqueda;
	private ResultPaneAcompaniantes<PasajeroDTO> rp_pasajeros_agregados;
	private JButton jb_buscar;
	private JButton jb_siguiente;
	private JButton jb_cancelar;
	private OcupacionDTO nuevaOcupacion;
	private Map<Integer,BusqPasajeroDTO.columnaOrden> indice_columnas;
	private BusqPasajeroDTO criterios_actuales;
	private MenuBuscarResponsable estadoAnterior;
	
	// setear ventana a 640x700
	public MenuBuscarAcompaniantes(JFrame ventana_contenedora, Encabezado encabezado, OcupacionDTO nuevaOcupacion, MenuBuscarResponsable estadoAnterior) {
		setBackground(Color.WHITE);
		this.ventana_contenedora = ventana_contenedora;
		this.nuevaOcupacion = nuevaOcupacion;
		this.estadoAnterior = estadoAnterior;
		setLayout(null);
		
		this.encabezado = encabezado;
		encabezado.setBounds(0, 0, 640, 110);
		add(encabezado);
		
		jb_buscar = new JButton("Buscar");
		jb_buscar.setBounds(452, 203, 100, 30);
		add(jb_buscar);
		
		jtf_numero_documento = new JTextField();
		jtf_numero_documento.setColumns(15);
		jtf_numero_documento.setBounds(412, 165, 140, 20);
		jtf_numero_documento.setDocument(new JTextFieldLimit(15));
		add(jtf_numero_documento);
		
		jtf_nombres = new JTextField();
		jtf_nombres.setColumns(50);
		jtf_nombres.setBounds(412, 126, 140, 20);
		jtf_nombres.setDocument(new JTextFieldLimit(50));
		add(jtf_nombres);
		
		JLabel lbl_nombres = new JLabel("Nombres:");
		lbl_nombres.setBounds(331, 129, 61, 14);
		add(lbl_nombres);
		
		lbl_numero_documento = new JLabel("<html>N\u00FAmero de<br>Documento:</html>");
		lbl_numero_documento.setBounds(331, 162, 100, 30);
		add(lbl_numero_documento);
		
		jcb_tipo_documento = new JComboBox<TipoDocumentoDTO>();
		jcb_tipo_documento.setBounds(157, 165, 140, 20);
		add(jcb_tipo_documento);
		
		jtf_apellido = new JTextField();
		jtf_apellido.setColumns(30);
		jtf_apellido.setBounds(157, 126, 140, 20);
		jtf_apellido.setDocument(new JTextFieldLimit(30));
		add(jtf_apellido);
		
		lbl_apellido = new JLabel("Apellido:");
		lbl_apellido.setBounds(83, 129, 64, 14);
		add(lbl_apellido);
		
		lbl_tipo_documento = new JLabel("<html>Tipo de<br>Documento:</html>");
		lbl_tipo_documento.setBounds(83, 162, 130, 30);
		add(lbl_tipo_documento);
		
		rp_pasajeros_busqueda = new ResultPaneAcompaniantes<PasajeroDTO>(this::llenarTablaBusquedaAcompaniantes);
		rp_pasajeros_busqueda.setBounds(10, 244, 620, 180);
		add(rp_pasajeros_busqueda);
		
		rp_pasajeros_agregados = new ResultPaneAcompaniantes<PasajeroDTO>();
		rp_pasajeros_agregados.setBounds(10, 435, 620, 180);
		rp_pasajeros_agregados.remove(rp_pasajeros_agregados.getNextBtn());
		rp_pasajeros_agregados.remove(rp_pasajeros_agregados.getPrevBtn());
		rp_pasajeros_agregados.remove(rp_pasajeros_agregados.getPageNumbers());
		add(rp_pasajeros_agregados);
		
		jb_siguiente = new JButton("Siguiente");
		jb_siguiente.setBounds(452, 640, 100, 30);
		add(jb_siguiente);
		
		jb_cancelar = new JButton("Cancelar");
		jb_cancelar.setBounds(83, 640, 100, 30);
		add(jb_cancelar);
		
		this.inicializarCampos();
		this.agregarActionListeners();
		this.agregarTabOrder();
		
		if(nuevaOcupacion.getAcompaniantes() != null) {
			this.llenarTablaAgregadosAcompaniantes();
		}
		
	}
	
	public void agregarActionListeners() {
		MenuBuscarAcompaniantes contexto = this;
		rp_pasajeros_busqueda.getTable().addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				
				int row = rp_pasajeros_busqueda.getTable().getSelectedRow();
				int col = rp_pasajeros_busqueda.getTable().getSelectedColumn();
				
				if(row == -1 || col == -1 || row == Integer.MAX_VALUE || col == Integer.MAX_VALUE) return;
				if(col != rp_pasajeros_busqueda.getContenido().getColumnCount()-1) return;
				
				Boolean check = (Boolean) rp_pasajeros_busqueda.getContenido().getValueAt(row, col);
				
				Integer capacidad = nuevaOcupacion.getHabitacion().getTipoHabitacionDTO().getCapacidad();
				if(!check && rp_pasajeros_agregados.getRowObjects().size() == capacidad){
					Mensaje.mensajeInformacion("<html><center>La capacidad de la habitación "+nuevaOcupacion.getHabitacion().getNumero()
												+" es de "+ capacidad.toString()+" personas.<br>No se pueden agregar más acompañantes.</center></html>");
					return;
				}

				rp_pasajeros_busqueda.getContenido().setValueAt(!check, row, col);
				check = !check;
				
				if(check) {
					Vector<Object> v = rp_pasajeros_busqueda.getRowObjects().get(row).asVector();
					v.add(true);
					int agregar = rp_pasajeros_agregados.getRowObjects().indexOf(rp_pasajeros_busqueda.getRowObjects().get(row));
					if(agregar != -1) return;
					rp_pasajeros_agregados.getRowObjects().add(rp_pasajeros_busqueda.getRowObjects().get(row));
					rp_pasajeros_agregados.getContenido().addRow(v);
					rp_pasajeros_agregados.sortRowObjects();
					
				} else {
					int removedRow = rp_pasajeros_agregados.getRowObjects().indexOf(rp_pasajeros_busqueda.getRowObjects().get(row));
					if(removedRow == -1) return;
					rp_pasajeros_agregados.getContenido().removeRow(removedRow);
					rp_pasajeros_agregados.getRowObjects().remove(removedRow);
				}
				
				rp_pasajeros_agregados.getContenido().fireTableDataChanged();
			}
		});
		
		
		rp_pasajeros_agregados.getTable().addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				int row = rp_pasajeros_agregados.getTable().getSelectedRow();
				int col = rp_pasajeros_agregados.getTable().getSelectedColumn();
								
				if(row == -1 || col == -1 || row == Integer.MAX_VALUE || col == Integer.MAX_VALUE) return;
				
				if(col != rp_pasajeros_agregados.getContenido().getColumnCount()-1) return;
					
				int removeTick = rp_pasajeros_busqueda.getRowObjects().indexOf(rp_pasajeros_agregados.getRowObjects().get(row));
				if(removeTick != -1) {
					rp_pasajeros_busqueda.getContenido().setValueAt(false, removeTick, rp_pasajeros_busqueda.getContenido().getColumnCount()-1);
					rp_pasajeros_busqueda.getContenido().fireTableDataChanged();
				} 
					
				rp_pasajeros_agregados.getContenido().removeRow(row);
				rp_pasajeros_agregados.getRowObjects().remove(row);
				
				rp_pasajeros_agregados.getContenido().fireTableDataChanged();
			}
		});
		
		jb_siguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rp_pasajeros_agregados.getRowObjects().size()==0){
					Mensaje.mensajeInformacion("Debe seleccionar al menos un pasajero.");
					return;
				}			

				nuevaOcupacion.setAcompaniantes(rp_pasajeros_agregados.getRowObjects());
				((VentanaPrincipal)ventana_contenedora).cambiarPanel(new MensajeConfirmarOcupacion(ventana_contenedora,encabezado,nuevaOcupacion,contexto),MensajeConfirmarOcupacion.x_bound,MensajeConfirmarOcupacion.y_bound,MensajeConfirmarOcupacion.titulo);
			}
		});
		
		jb_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					estadoAnterior.add(encabezado);
					((VentanaPrincipal)ventana_contenedora).cambiarPanel(estadoAnterior,MenuBuscarResponsable.x_bound,MenuBuscarResponsable.y_bound,MenuBuscarResponsable.titulo);
				}
		});
		
		jb_buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				criterios_actuales = new BusqPasajeroDTO();
				if(!jtf_nombres.getText().isEmpty()) {
					criterios_actuales.setNombres(jtf_nombres.getText());
				}
				if(!jtf_apellido.getText().isEmpty()) {
					criterios_actuales.setApellido(jtf_apellido.getText());
				}
				if(jcb_tipo_documento.getSelectedItem() != null) {
					criterios_actuales.setTipoDocumentoDTO((TipoDocumentoDTO) jcb_tipo_documento.getSelectedItem());
				}
				if(!jtf_numero_documento.getText().isEmpty()) {
					criterios_actuales.setNroDocumento(jtf_numero_documento.getText());
				}
				rp_pasajeros_busqueda.setPaginaActual(1);
				llenarTablaBusquedaAcompaniantes();
			}
		});
		
		rp_pasajeros_busqueda.agregarRowListener(new RowSorterListener() {
			public void sorterChanged(RowSorterEvent e) {
				if(e.getType() != RowSorterEvent.Type.SORT_ORDER_CHANGED) return;
				SortKey key = e.getSource().getSortKeys().get(0);
				criterios_actuales.setColumna(indice_columnas.get(key.getColumn()));
				criterios_actuales.setSortOrder(key.getSortOrder());
				e.getSource().setSortKeys(List.of(key));//es necesario eliminar las SortKey viejas manualmente
				llenarTablaBusquedaAcompaniantes();
			}
		});
		
		rp_pasajeros_agregados.agregarRowListener(new RowSorterListener() {
			public void sorterChanged(RowSorterEvent e) {
				if(e.getType() != RowSorterEvent.Type.SORT_ORDER_CHANGED) return;
				SortKey key = e.getSource().getSortKeys().get(0);
				rp_pasajeros_agregados.sortRowObjects(indice_columnas.get(key.getColumn()),key.getSortOrder());
				e.getSource().setSortKeys(List.of(key));//es necesario eliminar las SortKey viejas manualmente
			}
		});
	}
	
	private void llenarTablaBusquedaAcompaniantes() {
		rp_pasajeros_busqueda.getContenido().setRowCount(0);
		rp_pasajeros_busqueda.getRowObjects().clear();
		
		rp_pasajeros_busqueda.setCantPaginas((long) Math.ceil(GestorPasajeros.getCountPasajerosQueNoEstenOcupandoBy(criterios_actuales)/8.0));
		List<PasajeroDTO> lp = GestorPasajeros.getPasajerosQueNoEstenOcupandoBy(criterios_actuales, (rp_pasajeros_busqueda.getPaginaActual()-1)*8, 8);
		
		for(PasajeroDTO p : lp) {
			Vector<Object> v = p.asVector();
			if(rp_pasajeros_agregados.getRowObjects().contains(p))	v.add(true);
			else v.add(false);
			rp_pasajeros_busqueda.getContenido().addRow(v);
			rp_pasajeros_busqueda.getRowObjects().add(p);
		}
	}
	
	private void llenarTablaAgregadosAcompaniantes() {
		rp_pasajeros_agregados.getContenido().setRowCount(0);
		rp_pasajeros_agregados.getRowObjects().clear();
		
		List<PasajeroDTO> lp = nuevaOcupacion.getAcompaniantes();
		
		for(PasajeroDTO p : lp) {
			Vector<Object> v = p.asVector();
			v.add(true);
			
			rp_pasajeros_agregados.getContenido().addRow(v);
			rp_pasajeros_agregados.getRowObjects().add(p);
		}
	}
	
	private void inicializarCampos() {
		
		HabitacionDTO h=null;
		try {
			h = GestorHabitaciones.getHabitacionByNumero(nuevaOcupacion.getHabitacion().getNumero());
		} catch (HabitacionNoExistenteException e) {
			Mensaje.mensajeError(new String[]{"La habitación seleccionada no existe en el sistema."});
			((VentanaPrincipal)ventana_contenedora).cambiarPanel(new MenuPrincipal(ventana_contenedora, encabezado), MenuPrincipal.x_bound, MenuPrincipal.y_bound, MenuPrincipal.titulo);
			return;
		}
		nuevaOcupacion.setHabitacion(h);

		rp_pasajeros_busqueda.agregarColumnas(List.of("Apellido","Nombres","Tipo Documento","Número de Documento", "Acompañante"), List.of(4));
		indice_columnas = new HashMap<Integer,BusqPasajeroDTO.columnaOrden>();
		indice_columnas.put(0, BusqPasajeroDTO.columnaOrden.APELLIDO);//la clave debe coincidir con el orden en rp_pasajeros
		indice_columnas.put(1, BusqPasajeroDTO.columnaOrden.NOMBRES);
		indice_columnas.put(2, BusqPasajeroDTO.columnaOrden.TIPODOC);
		indice_columnas.put(3, BusqPasajeroDTO.columnaOrden.NRODOC);
		
		rp_pasajeros_agregados.agregarColumnas(List.of("Apellido","Nombres","Tipo Documento","Número de Documento", "Acompañante"), List.of(4));
		
		
		jcb_tipo_documento.addItem(null);
		jcb_tipo_documento.setSelectedItem(null);
		List<TipoDocumentoDTO> ltd = GestorPasajeros.getAllTipoDocumento();
		
		for(TipoDocumentoDTO t : ltd) {
			jcb_tipo_documento.addItem(t);
		}
		
	}
	//jtable solo string
	//jtable_contenido objetos
	//Insertas las filas y los objetos
	private void agregarTabOrder() {
		this.setFocusTraversalPolicy(new TabOrder(List.of(
				jtf_apellido,
				jtf_nombres,
				jcb_tipo_documento,
				jtf_numero_documento,
				jb_buscar,
				jb_siguiente,
				jb_cancelar
				)));
		this.setFocusTraversalPolicyProvider(true);
	}
	
	@Override
	public void setDefaultTab() {
		jtf_apellido.requestFocus();
	}
}
