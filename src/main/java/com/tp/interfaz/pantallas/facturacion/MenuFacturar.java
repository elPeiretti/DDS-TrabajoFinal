package com.tp.interfaz.pantallas.facturacion;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import javax.swing.*;
import javax.swing.RowSorter.SortKey;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.RowSorterEvent;
import javax.swing.event.RowSorterListener;
import javax.swing.text.MaskFormatter;

import com.tp.interfaz.dto.FacturarDTO;
import com.tp.interfaz.dto.HabitacionDTO;
import com.tp.interfaz.dto.OcupacionDTO;
import com.tp.interfaz.dto.PasajeroDTO;
import com.tp.interfaz.dto.ResponsablePagoTerceroDTO;
import com.tp.interfaz.pantallas.MenuPrincipal;
import com.tp.interfaz.pantallas.SeteableTab;
import com.tp.interfaz.pantallas.VentanaPrincipal;
import com.tp.interfaz.pantallas.misc.Encabezado;
import com.tp.interfaz.pantallas.misc.EnterActionAssigner;
import com.tp.interfaz.pantallas.misc.JTextFieldLimit;
import com.tp.interfaz.pantallas.misc.Mensaje;
import com.tp.interfaz.pantallas.misc.ResultPane;
import com.tp.interfaz.pantallas.misc.TabOrder;
import com.tp.logica.excepciones.HabitacionNoExistenteException;
import com.tp.logica.excepciones.HabitacionSinOcupacionesException;
import com.tp.logica.excepciones.NuevaHabitacionException;
import com.tp.logica.excepciones.OcupacionNoCheckoutableException;
import com.tp.logica.gestores.GestorFacturas;
import com.tp.logica.gestores.GestorHabitaciones;
public class MenuFacturar extends JPanel implements SeteableTab {

	private static final long serialVersionUID = 3727707501754797021L;
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
	private Map<Integer,FacturarDTO.columnaOrden> indice_columnas;
	private boolean cuit_activo;
	private boolean tabla_vacia;
	private JLabel lbl_cuit;
	private JLabel lbl_raz_social_tag;
	private FacturarDTO criterios_actuales;
	private boolean estadiaCalculada;
	
	public MenuFacturar(JFrame ventana_contenedora, Encabezado encabezado)  {
		setBackground(Color.WHITE);
		this.ventana_contenedora = ventana_contenedora;
		setLayout(null);
		criterios_actuales = new FacturarDTO();		
		this.campos_validos = new HashMap<String,Boolean>();
		cuit_activo = false;
		tabla_vacia = true;
		estadiaCalculada = false;
		
		lbl_num_hab = new JLabel("<html>Número de<br/>Habitación <font color='red'>(*)</font>: </html>");
		lbl_num_hab.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_num_hab.setBounds(82, 133, 81, 30);
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
		
		rp_pasajeros = new ResultPane<PasajeroDTO>();
		rp_pasajeros.setBounds(10, 230, 620, 180);
		rp_pasajeros.getNextBtn().setVisible(false);
		rp_pasajeros.getPrevBtn().setVisible(false);
		rp_pasajeros.getPageNumbers().setVisible(false);
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
		
		JLabel lbl_hora_salida = new JLabel("<html>Hora de<br/>Salida <font color='red'>(*)</font>:</html>");
		lbl_hora_salida.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_hora_salida.setBounds(334, 133, 67, 30);
		add(lbl_hora_salida);
		
		chbx_tercero = new JCheckBox("Factura a Tercero");
		chbx_tercero.setBounds(84, 421, 172, 23);
		add(chbx_tercero);
		
		lbl_cuit = new JLabel("<html>CUIT <font color='red'>(*)</font>:</html>");
		lbl_cuit.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_cuit.setBounds(356, 425, 48, 14);
		lbl_cuit.setEnabled(false);
		add(lbl_cuit);
		
		jftf_cuit = new JFormattedTextField();
		jftf_cuit.setColumns(10);
		jftf_cuit.setBounds(410, 421, 140, 23);
		jftf_cuit.setEnabled(false);
		add(jftf_cuit);
		try {
			MaskFormatter mf = new MaskFormatter("##-########-#");
			mf.setPlaceholderCharacter('_');
			mf.install(jftf_cuit);
		} catch (ParseException e) {
			Mensaje.mensajeError(new String[]{"Ha ocurrido un error en la creación de la interfaz."});
			e.printStackTrace();
		}
		
		lbl_raz_social_tag = new JLabel("Razón Social:");
		lbl_raz_social_tag.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_raz_social_tag.setBounds(284, 467, 118, 14);
		lbl_raz_social_tag.setEnabled(false);
		add(lbl_raz_social_tag);
		
		lbl_raz_social = new JLabel("");
		lbl_raz_social.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_raz_social.setBounds(410, 467, 200, 14);
		lbl_raz_social.setEnabled(false);
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
		
		EnterActionAssigner.setEnterAction(List.of(jb_buscar,jb_cancelar,jb_siguiente));
		this.inicializarMapa();
		this.inicializarCampos();
		this.agregarTabOrder();
		this.agregarActionListeners();
		this.agregarListenersValidacion();
	}

	private void inicializarCampos() {
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
					jftf_cuit.setEnabled(true);
					lbl_cuit.setEnabled(true);
					lbl_raz_social_tag.setEnabled(true);
					lbl_raz_social.setEnabled(true);
					validarCuit();

					rp_pasajeros.getTable().getSelectionModel().clearSelection();
					rp_pasajeros.setEnabled(false);
				}else {
					cuit_activo = false;
					jftf_cuit.setEnabled(false);
					lbl_cuit.setEnabled(false);
					lbl_raz_social_tag.setEnabled(false);
					lbl_raz_social.setEnabled(false);
					campos_validos.put("cuit", true);
					lbl_error_cuit.setText("");

					rp_pasajeros.setEnabled(true);
				}
			}
		});

		jftf_cuit.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				validarYBuscar();
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				validarYBuscar();
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				validarYBuscar();
			}
			
			private void validarYBuscar(){
				if(cuit_activo && validarCuit()){
					criterios_actuales.setResponsable(GestorFacturas.getResponsableTerceroByCuit(jftf_cuit.getText()));
					if(criterios_actuales.getResponsable() == null){
						Mensaje.mensajeInformacion("No existe un responsable con el cuit indicado registrado en el sistema.");
					}
					else{
						lbl_raz_social.setText(criterios_actuales.getResponsable().getRazonSocial());
					}
				}
				else{
					if(cuit_activo){
						criterios_actuales.setResponsable(null);
					}
					lbl_raz_social.setText("");
				}
			}
		});

		jb_buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!campos_validos.get("habitacion")) {
					indicarCamposIncompletos();
					return;
				}
				criterios_actuales.setNumeroHabitacion(jtf_num_hab.getText());
				try{
					criterios_actuales.setOcupacion(GestorHabitaciones.buscarUltimaOcupacion(criterios_actuales.getNumeroHabitacion()));
					llenarTabla();
				}
				catch(HabitacionNoExistenteException exc){
					Mensaje.mensajeInformacion("La habitación seleccionada no existe en el sistema.");
					rp_pasajeros.limpiarTabla();
				}
				catch(OcupacionNoCheckoutableException exc){
					Mensaje.mensajeInformacion("La habitación seleccionada no posee ocupaciones actuales.");
					return;
				}
				catch(HabitacionSinOcupacionesException exc){
					Mensaje.mensajeInformacion("La habitación seleccionada no posee ocupaciones.");
					rp_pasajeros.limpiarTabla();
				}

			}
		});

		jb_siguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(campos_validos.get("habitacion") && campos_validos.get("salida"))){
					indicarCamposIncompletos();
					return;
				}		

				if(cuit_activo) {
					if(!validarCuit()) return;

					ResponsablePagoTerceroDTO resp = criterios_actuales.getResponsable();
					if(resp == null) {
						Mensaje.mensajeInformacion("AQUI DEBERIA EJECUTARSE CU03");
						return;
					}else {
						cambiarPantalla(-2);
					}
				}
				else{ // debe seleccionar de la tabla
					int fila = rp_pasajeros.getTable().getSelectedRow();
					if(fila != -1) {
						cambiarPantalla(fila);
					}
					else{
						Mensaje.mensajeInformacion("Debe seleccionar un responsable para poder facturar.");
					}
				}
			}
		});
		
		jb_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				int opt = Mensaje.mensajeConfirmacion("¿Desea cancelar la facturación?");
				if (opt == 1) { 
					((VentanaPrincipal)ventana_contenedora).cambiarPanel(new MenuPrincipal(ventana_contenedora,encabezado),
							 MenuPrincipal.x_bound,MenuPrincipal.y_bound,MenuPrincipal.titulo);
				}*/
				((VentanaPrincipal)ventana_contenedora).cambiarPanel(new MenuPrincipal(ventana_contenedora,encabezado),
						 MenuPrincipal.x_bound,MenuPrincipal.y_bound,MenuPrincipal.titulo);
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

		rp_pasajeros.getTable().addMouseListener(new MouseListener(){

			public void mouseClicked(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {
				int row = rp_pasajeros.getTable().getSelectedRow();
				if(row==-1) return;
				if (Period.between(rp_pasajeros.getRowObjects().get(row).getFechaDeNacimiento(), LocalDate.now()).getYears() < 18){
					Mensaje.mensajeInformacion("La persona seleccionada es menor de edad. Por favor elija otra.");
					rp_pasajeros.getTable().getSelectionModel().clearSelection();
				}
			}

		});
	}
	
	private void cambiarPantalla(int fila) {
		JPanel m;
		criterios_actuales.setHoraSalida(jftf_salida.getText());
		HabitacionDTO hDto = null;

		try {
			OcupacionDTO ultima_ocup = GestorHabitaciones.buscarUltimaOcupacion(jtf_num_hab.getText());
			hDto = ultima_ocup.getHabitacion();
			criterios_actuales.setNumeroHabitacion(ultima_ocup.getHabitacion().getNumero());
			criterios_actuales.setOcupacion(ultima_ocup);

			if(!estadiaCalculada && !LocalDate.now().equals(criterios_actuales.getOcupacion().getFechaEgreso().plusDays(1))) 
				if(Mensaje.mensajeConfirmacion("La ocupación de esta habitación termina el " + criterios_actuales.getOcupacion().getFechaEgreso().plusDays(1) + 
						". Desea continuar?", "Atención!", new String[]{"Sí","No"}) == 1) return;
			
			if(!estadiaCalculada)
				GestorHabitaciones.calcularEstadia(criterios_actuales.getHoraSalida(),criterios_actuales.getOcupacion());
		}
		catch(HabitacionNoExistenteException exc){
			Mensaje.mensajeInformacion("La habitación seleccionada no existe en el sistema.");
			return;
		}	
		catch(HabitacionSinOcupacionesException exc){
			Mensaje.mensajeInformacion("La habitación seleccionada no posee ocupaciones.");
			return;
		}
		catch(OcupacionNoCheckoutableException exc){
			Mensaje.mensajeInformacion("La habitación seleccionada no posee ocupaciones actuales.");
			return;
		}
		catch(NuevaHabitacionException e) {
			Mensaje.mensajeError(new String[]{"Hubo un error al generar el servicio de estadía."});
			return;
		}

		if(fila == -2) {
			m = new MenuConsumosPorHabitacion(ventana_contenedora,encabezado,criterios_actuales.getResponsable(),hDto,this);
		}else {
			m = new MenuConsumosPorHabitacion(ventana_contenedora,encabezado,rp_pasajeros.getRowObjects().get(fila),hDto,this);
		}

		// deshabilitar habitacion y hora de salida
		jtf_num_hab.setEnabled(false);
		jftf_salida.setEnabled(false);
		jb_cancelar.setEnabled(false);
		estadiaCalculada = true;

		((VentanaPrincipal)ventana_contenedora).cambiarPanel(m,MenuConsumosPorHabitacion.x_bound,MenuConsumosPorHabitacion.y_bound,MenuConsumosPorHabitacion.titulo);
	}

	private void llenarTabla() {
		rp_pasajeros.getContenido().setRowCount(0);
		rp_pasajeros.getRowObjects().clear();
		
		rp_pasajeros.setCantPaginas(1L);
		List<PasajeroDTO> lp = criterios_actuales.getOcupacion().getPasajeros();
		GestorHabitaciones.ordenarLista(lp, criterios_actuales);
		
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

	private boolean validarCuit() {
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
		return campos_validos.get("cuit");
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

	private void indicarCamposIncompletos() {
		if(cuit_activo && jftf_cuit.getText().equals("__-________-_"))
		lbl_error_cuit.setText("Este campo no puede estar vacío.");
		if (jtf_num_hab.getText().isBlank())
			lbl_error_num_hab.setText("Este campo no puede estar vacío.");
		if (jftf_salida.getText().equals("__:__"))
			lbl_error_salida.setText("Este campo no puede estar vacío.");
	}
	
	private void inicializarMapa() {
		campos_validos.put("habitacion", false);
		campos_validos.put("salida", false);
		campos_validos.put("cuit",true);
	}
	
}
