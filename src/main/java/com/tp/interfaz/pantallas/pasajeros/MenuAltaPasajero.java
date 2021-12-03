package com.tp.interfaz.pantallas.pasajeros;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import com.toedter.calendar.JDateChooser;
import com.tp.interfaz.dto.*;
import com.tp.interfaz.pantallas.SeteableTab;
import com.tp.interfaz.pantallas.VentanaPrincipal;
import com.tp.interfaz.pantallas.misc.*;
import com.tp.logica.excepciones.CamposInvalidosException;
import com.tp.logica.excepciones.DocumentoExistenteException;
import com.tp.logica.excepciones.NuevoPasajeroException;
import com.tp.logica.gestores.GestorGeografico;
import com.tp.logica.gestores.GestorPasajeros;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;



public class MenuAltaPasajero extends JPanel implements SeteableTab{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9057393943646040068L;
	public static String titulo = "Alta Pasajero";
	public static int x_bound = 660;
	public static int y_bound = 650;
	
	protected MenuAltaPasajero contexto;
	protected JFrame ventana_contenedora;
	protected JTextField jtf_ocupacion;
	protected Encabezado encabezado;
	protected JDateChooser dc_nacimiento;
	protected JTextField jtf_numero;
	protected JFormattedTextField jftf_cuit;
	protected JTextField jtf_nombres;
	protected JTextField jtf_apellido;
	protected JTextField jtf_telefono;
	protected JTextField jtf_piso;
	protected JTextField jtf_departamento;
	protected JTextField jtf_codigo_postal;
	protected JTextField jtf_email;
	protected JComboBox<PaisDTO> jcb_pais;
	protected JComboBox<CiudadDTO> jcb_ciudad;
	protected JComboBox<ProvinciaDTO> jcb_provincia;
	protected JComboBox<TipoDocumentoDTO> jcb_tipo_documento;
	protected JComboBox<PosicionIVADTO> jcb_factura;
	protected JLabel lbl_ocupacion;
	protected JButton jb_cancelar;
	protected JButton jb_siguiente;
	protected JComboBox<PaisDTO> jcb_nacionalidad; //ver comentario en Pasajero
	protected JLabel lbl_nacionalidad;
	protected JLabel lbl_error_ocupacion;
	protected JLabel lbl_numero;
	protected JLabel lbl_error_numero;
	protected JLabel lbl_calle;
	protected JTextField jtf_calle;
	protected JLabel lbl_error_calle;
	protected JLabel lbl_ciudad;
	protected JLabel lbl_error_ciudad;
	protected JLabel lbl_pais;
	protected JLabel lbl_error_pais;
	protected JLabel lbl_nacimiento;
	protected JLabel lbl_error_nacimiento;
	protected JLabel lbl_cuit;
	protected JLabel lbl_apellido;
	protected JLabel lbl_error_apellido;
	protected JLabel lbl_telefono;
	protected JLabel lbl_piso;
	protected JLabel lbl_departamento;
	protected JLabel lbl_factura;
	protected JLabel lbl_numero_documento;
	protected JTextField jtf_numero_documento;
	protected JLabel lbl_tipo_documento;
	protected JLabel lbl_email;
	protected JLabel lbl_codigo_postal;
	protected JLabel lbl_provincia;
	protected JLabel lbl_error_tipo_documento;
	protected JLabel lbl_error_numero_documento;
	protected JLabel lbl_error_factura;
	protected JLabel lbl_error_provincia;
	protected JLabel lbl_error_codigo_postal;
	protected JLabel lbl_error_piso;
	protected JLabel lbl_error_telefono;
	protected JLabel lbl_error_email;
	protected JLabel lbl_error_nacionalidad;
	protected MenuBusquedaPasajero menu_anterior;
	protected HashMap<String,Boolean> campos_validos;
	protected JLabel lbl_error_nombres;
	protected JLabel lbl_error_cuit;
	protected boolean cuit_obligatorio = false;
	
	public MenuAltaPasajero(JFrame ventana_contenedora, Encabezado encabezado, MenuBusquedaPasajero estado_anterior) {
		setBackground(Color.WHITE);
		this.menu_anterior = estado_anterior;
		this.ventana_contenedora = ventana_contenedora;
		this.contexto = this;
		ventana_contenedora.setSize(x_bound,y_bound);
		setLayout(null);
		
		this.campos_validos = new HashMap<String,Boolean>();
		inicializarMapa();
		
		jcb_pais = new JComboBox<PaisDTO>();
		jcb_pais.setBounds(128, 319, 122, 20);
		add(jcb_pais);
		
		jcb_ciudad = new JComboBox<CiudadDTO>();
		jcb_ciudad.setBounds(128, 361, 122, 20);
		add(jcb_ciudad);
		
		jcb_provincia = new JComboBox<ProvinciaDTO>();
		jcb_provincia.setBounds(495, 319, 122, 20);
		add(jcb_provincia);
		
		jcb_tipo_documento = new JComboBox<TipoDocumentoDTO>();
		jcb_tipo_documento.setBounds(495, 151, 122, 20);
		add(jcb_tipo_documento);
		
		jcb_factura = new JComboBox<PosicionIVADTO>();
		jcb_factura.setBounds(495, 235, 122, 20);
		add(jcb_factura);
		
		lbl_ocupacion = new JLabel("<html>Ocupación <font color='red'>(*)</font>:</html>");
		lbl_ocupacion.setBounds(334, 492, 84, 14);
		add(lbl_ocupacion);
		
		jtf_ocupacion = new JTextField();
		jtf_ocupacion.setColumns(10);
		jtf_ocupacion.setBounds(495, 489, 122, 20);
		jtf_ocupacion.setDocument(new JTextFieldLimit(30));
		add(jtf_ocupacion);
		
		jb_cancelar = new JButton("Cancelar");
		jb_cancelar.setBounds(400, 569, 91, 30);
		add(jb_cancelar);
		
		jb_siguiente = new JButton("Siguiente");
		jb_siguiente.setBounds(526, 569, 91, 30);
		add(jb_siguiente);
		
		jcb_nacionalidad = new JComboBox<PaisDTO>();
		jcb_nacionalidad.setBounds(128, 528, 122, 20);
		add(jcb_nacionalidad);
		
		lbl_nacionalidad = new JLabel("<html>Nacionalidad <font color='red'>(*)</font>:</html>");
		lbl_nacionalidad.setBounds(23, 531, 110, 14);
		add(lbl_nacionalidad);
		
		this.encabezado = encabezado;
		this.encabezado.setBounds(0, 0, 640, 110);
		add(encabezado);
		
		dc_nacimiento = new JDateChooser("dd/MM/yyyy", "##/##/####", '_');
		dc_nacimiento.setBounds(128,277,122,20);
		dc_nacimiento.setMaxSelectableDate(new Date());
		add(dc_nacimiento);
		
		lbl_error_ocupacion = new JLabel("");
		lbl_error_ocupacion.setForeground(Color.RED);
		lbl_error_ocupacion.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lbl_error_ocupacion.setBounds(334, 513, 227, 10);
		add(lbl_error_ocupacion);
		
		lbl_numero = new JLabel("<html>N\u00FAmero <font color='red'>(*)</font>:</html>");
		lbl_numero.setBounds(23, 447, 84, 14);
		add(lbl_numero);
		
		jtf_numero = new JTextField();
		jtf_numero.setColumns(10);
		jtf_numero.setBounds(128, 444, 122, 20);
		jtf_numero.setDocument(new JTextFieldLimit(6));
		add(jtf_numero);
		
		lbl_error_numero = new JLabel("");
		lbl_error_numero.setForeground(Color.RED);
		lbl_error_numero.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lbl_error_numero.setBounds(23, 468, 227, 10);
		add(lbl_error_numero);
		
		lbl_calle = new JLabel("<html>Calle <font color='red'>(*)</font>:</html>");
		lbl_calle.setBounds(23, 405, 84, 14);
		add(lbl_calle);
		
		jtf_calle = new JTextField();
		jtf_calle.setColumns(10);
		jtf_calle.setBounds(128, 402, 122, 20);
		jtf_calle.setDocument(new JTextFieldLimit(30));
		add(jtf_calle);
		
		lbl_error_calle = new JLabel("");
		lbl_error_calle.setForeground(Color.RED);
		lbl_error_calle.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lbl_error_calle.setBounds(23, 426, 227, 10);
		add(lbl_error_calle);
		
		lbl_ciudad = new JLabel("<html>Ciudad <font color='red'>(*)</font>:</html>");
		lbl_ciudad.setBounds(23, 364, 84, 14);
		add(lbl_ciudad);
		
		lbl_error_ciudad = new JLabel("");
		lbl_error_ciudad.setForeground(Color.RED);
		lbl_error_ciudad.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lbl_error_ciudad.setBounds(23, 385, 227, 10);
		add(lbl_error_ciudad);
		
		lbl_pais = new JLabel("<html>Pa\u00EDs <font color='red'>(*)</font>:</html>");
		lbl_pais.setBounds(23, 322, 84, 14);
		add(lbl_pais);
		
		lbl_error_pais = new JLabel("");
		lbl_error_pais.setForeground(Color.RED);
		lbl_error_pais.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lbl_error_pais.setBounds(23, 343, 227, 10);
		add(lbl_error_pais);
		
		lbl_nacimiento = new JLabel("<html>Nacimiento <font color='red'>(*)</font>:</html>");
		lbl_nacimiento.setBounds(23, 278, 95, 14);
		add(lbl_nacimiento);
		
		lbl_error_nacimiento = new JLabel("");
		lbl_error_nacimiento.setForeground(Color.RED);
		lbl_error_nacimiento.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lbl_error_nacimiento.setBounds(23, 301, 227, 10);
		add(lbl_error_nacimiento);
		
		lbl_cuit = new JLabel("CUIT:");
		lbl_cuit.setBounds(23, 238, 84, 14);
		add(lbl_cuit);
		
		jftf_cuit = new JFormattedTextField();
		jftf_cuit.setColumns(10);
		jftf_cuit.setBounds(128, 235, 122, 20);
		add(jftf_cuit);
		try {
			MaskFormatter mf = new MaskFormatter("##-########-#");
			mf.setPlaceholderCharacter('_');
			mf.install(jftf_cuit);
		} catch (ParseException e) {
			Mensaje.mensajeError(new String[]{"Ha ocurrido un error en la creación de la interfaz."});
			e.printStackTrace();
		}
		
		
		lbl_error_cuit = new JLabel("");
		lbl_error_cuit.setForeground(Color.RED);
		lbl_error_cuit.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lbl_error_cuit.setBounds(23, 259, 227, 10);
		add(lbl_error_cuit);
		
		JLabel lbl_nombres = new JLabel("<html>Nombres <font color='red'>(*)</font>:</html>");
		lbl_nombres.setBounds(23, 196, 84, 14);
		add(lbl_nombres);
		
		jtf_nombres = new JTextField();
		jtf_nombres.setColumns(10);
		jtf_nombres.setBounds(128, 193, 122, 20);
		jtf_nombres.setDocument(new JTextFieldLimit(50));
		add(jtf_nombres);
		
		lbl_error_nombres = new JLabel("");
		lbl_error_nombres.setForeground(Color.RED);
		lbl_error_nombres.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lbl_error_nombres.setBounds(23, 217, 227, 10);
		add(lbl_error_nombres);
		
		lbl_apellido = new JLabel("<html>Apellido <font color='red'>(*)</font>:</html>");
		lbl_apellido.setBounds(23, 154, 84, 14);
		add(lbl_apellido);
		
		jtf_apellido = new JTextField();
		jtf_apellido.setColumns(10);
		jtf_apellido.setBounds(128, 151, 122, 20);
		jtf_apellido.setDocument(new JTextFieldLimit(30));
		add(jtf_apellido);
		
		lbl_error_apellido = new JLabel("");
		lbl_error_apellido.setForeground(Color.RED);
		lbl_error_apellido.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lbl_error_apellido.setBounds(23, 175, 227, 10);
		add(lbl_error_apellido);
		
		lbl_telefono = new JLabel("<html>Tel\u00E9fono <font color='red'>(*)</font>:</html>");
		lbl_telefono.setBounds(334, 447, 84, 14);
		add(lbl_telefono);
		
		jtf_telefono = new JTextField();
		jtf_telefono.setColumns(10);
		jtf_telefono.setBounds(495, 444, 122, 20);
		jtf_telefono.setDocument(new JTextFieldLimit(15));
		add(jtf_telefono);
		
		lbl_piso = new JLabel("Piso:");
		lbl_piso.setBounds(334, 405, 62, 14);
		add(lbl_piso);
		
		jtf_piso = new JTextField();
		jtf_piso.setColumns(10);
		jtf_piso.setBounds(374, 402, 30, 20);
		jtf_piso.setDocument(new JTextFieldLimit(3));
		add(jtf_piso);
		
		lbl_departamento = new JLabel("Departamento:");
		lbl_departamento.setBounds(438, 406, 85, 14);
		add(lbl_departamento);
		
		jtf_departamento = new JTextField();
		jtf_departamento.setColumns(10);
		jtf_departamento.setBounds(533, 403, 84, 20);
		jtf_departamento.setDocument(new JTextFieldLimit(5));
		add(jtf_departamento);
		
		lbl_codigo_postal = new JLabel("<html>C\u00F3digo Postal <font color='red'>(*)</font>:</html>");
		lbl_codigo_postal.setBounds(334, 364, 110, 14);
		add(lbl_codigo_postal);
		
		jtf_codigo_postal = new JTextField();
		jtf_codigo_postal.setColumns(10);
		jtf_codigo_postal.setBounds(495, 361, 122, 20);
		jtf_codigo_postal.setDocument(new JTextFieldLimit(10));
		add(jtf_codigo_postal);
		
		lbl_provincia = new JLabel("<html>Provincia <font color='red'>(*)</font>:</html>");
		lbl_provincia.setBounds(334, 322, 84, 14);
		add(lbl_provincia);
		
		lbl_factura = new JLabel("<html>Factura <font color='red'>(*)</font>:</html>");
		lbl_factura.setBounds(334, 238, 84, 14);
		add(lbl_factura);
		
		lbl_numero_documento = new JLabel("<html>N\u00FAmero de Documento <font color='red'>(*)</font>:</html>");
		lbl_numero_documento.setBounds(334, 196, 157, 14);
		add(lbl_numero_documento);
		
		jtf_numero_documento = new JTextField();
		jtf_numero_documento.setColumns(10);
		jtf_numero_documento.setBounds(495, 193, 122, 20);
		jtf_numero_documento.setDocument(new JTextFieldLimit(15));
		add(jtf_numero_documento);
		
		lbl_tipo_documento = new JLabel("<html>Tipo de Documento <font color='red'>(*)</font>:</html>");
		lbl_tipo_documento.setBounds(334, 154, 139, 14);
		add(lbl_tipo_documento);
		
		lbl_email = new JLabel("E-mail:");
		lbl_email.setBounds(23, 489, 84, 14);
		add(lbl_email);
		
		jtf_email = new JTextField();
		jtf_email.setColumns(10);
		jtf_email.setBounds(128, 489, 122, 20);
		jtf_email.setDocument(new JTextFieldLimit(70));
		add(jtf_email);
		
		lbl_error_nacionalidad = new JLabel("");
		lbl_error_nacionalidad.setForeground(Color.RED);
		lbl_error_nacionalidad.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lbl_error_nacionalidad.setBounds(23, 552, 283, 10);
		add(lbl_error_nacionalidad);
		
		lbl_error_email = new JLabel("");
		lbl_error_email.setForeground(Color.RED);
		lbl_error_email.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lbl_error_email.setBounds(23, 510, 283, 10);
		add(lbl_error_email);
		
		lbl_error_telefono = new JLabel("");
		lbl_error_telefono.setForeground(Color.RED);
		lbl_error_telefono.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lbl_error_telefono.setBounds(334, 468, 283, 10);
		add(lbl_error_telefono);
		
		lbl_error_piso = new JLabel("");
		lbl_error_piso.setForeground(Color.RED);
		lbl_error_piso.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lbl_error_piso.setBounds(334, 427, 157, 10);
		add(lbl_error_piso);
		
		lbl_error_codigo_postal = new JLabel("");
		lbl_error_codigo_postal.setForeground(Color.RED);
		lbl_error_codigo_postal.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lbl_error_codigo_postal.setBounds(334, 385, 283, 10);
		add(lbl_error_codigo_postal);
		
		lbl_error_provincia = new JLabel("");
		lbl_error_provincia.setForeground(Color.RED);
		lbl_error_provincia.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lbl_error_provincia.setBounds(334, 343, 283, 10);
		add(lbl_error_provincia);
		
		lbl_error_factura = new JLabel("");
		lbl_error_factura.setForeground(Color.RED);
		lbl_error_factura.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lbl_error_factura.setBounds(334, 259, 283, 10);
		add(lbl_error_factura);
		
		lbl_error_numero_documento = new JLabel("");
		lbl_error_numero_documento.setForeground(Color.RED);
		lbl_error_numero_documento.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lbl_error_numero_documento.setBounds(334, 217, 283, 10);
		add(lbl_error_numero_documento);
		
		lbl_error_tipo_documento = new JLabel("");
		lbl_error_tipo_documento.setForeground(Color.RED);
		lbl_error_tipo_documento.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lbl_error_tipo_documento.setBounds(334, 175, 283, 10);
		add(lbl_error_tipo_documento);
		
		EnterActionAssigner.setEnterAction(List.of(jb_siguiente, jb_cancelar));
		EnterActionAssigner.setEnterActionComboBox(List.of(jcb_nacionalidad, jcb_pais, jcb_provincia, jcb_ciudad, jcb_tipo_documento, jcb_factura));
		
		this.inicializarCampos();
		this.agregarActionListeners();
		this.agregarTabOrder();
		this.agregarListenersValidacion();
	}
	
	private void inicializarCampos() {
		
		jtf_codigo_postal.setText("3000");
		
		List<PosicionIVADTO> lpi = GestorPasajeros.getAllPosicionIVA();
		jcb_factura.removeAllItems();
		for(PosicionIVADTO t : lpi) {
			jcb_factura.addItem(t);
			if(t.getPosicion().equals("C.F.")) jcb_factura.setSelectedItem(t);
		}
		
		List<TipoDocumentoDTO> ltd = GestorPasajeros.getAllTipoDocumento();
		jcb_tipo_documento.removeAllItems();
		for(TipoDocumentoDTO t : ltd) {
			jcb_tipo_documento.addItem(t);
			if(t.getTipo().equals("DNI")) jcb_tipo_documento.setSelectedItem(t);
		}
		
		List<PaisDTO> lPais = GestorGeografico.getAllPais();
		jcb_pais.removeAllItems();
		for(PaisDTO p : lPais) {
			jcb_nacionalidad.addItem(p);
			jcb_pais.addItem(p);
			if(p.getNombre().equals("Argentina")) {
				jcb_nacionalidad.setSelectedItem(p);
				jcb_pais.setSelectedItem(p);
			}
		}
		
		if(jcb_pais.getSelectedItem() != null)
			cargarListaProvincia(((PaisDTO) jcb_pais.getSelectedItem()).getIdPais(), true);
		
		if(jcb_provincia.getSelectedItem() != null)
			cargarListaCiudad(((ProvinciaDTO) jcb_provincia.getSelectedItem()).getIdProvincia(), true);
		
	}
	
	private void cargarListaProvincia(Integer idPais, Boolean inicializando) {
		
		List<ProvinciaDTO> lProvincia = GestorGeografico.getAllProvinciaByPais(idPais);
		jcb_provincia.removeAllItems();
		for(ProvinciaDTO p : lProvincia) {
			jcb_provincia.addItem(p);
			if(inicializando && p.getNombre().equals("Santa Fe")) {
				jcb_provincia.setSelectedItem(p);
			}
		}
		
	} 
	
	private void cargarListaCiudad(Integer idProvincia, Boolean inicializando) {
		List<CiudadDTO> lCiudad = GestorGeografico.getAllCiudadByProvincia(idProvincia);
		jcb_ciudad.removeAllItems();
		for(CiudadDTO p : lCiudad) {
			jcb_ciudad.addItem(p);
			if(inicializando && p.getNombre().equals("Santa Fe")) {
				jcb_ciudad.setSelectedItem(p);
			}
		}
		
	} 

	private void agregarTabOrder() {
		this.setFocusTraversalPolicy(new TabOrder(List.of(
				jtf_apellido, jcb_tipo_documento, jtf_nombres, jtf_numero_documento,
				jftf_cuit, jcb_factura, dc_nacimiento.getDateEditor().getUiComponent(),
				jcb_pais, jcb_provincia,
				jcb_ciudad, jtf_codigo_postal, jtf_calle, jtf_piso, jtf_departamento,
				jtf_numero, jtf_telefono, jtf_email, jtf_ocupacion, jcb_nacionalidad,
				jb_siguiente, jb_cancelar
				)));
		this.setFocusTraversalPolicyProvider(true);
	}
	
	private void agregarActionListeners() {
		
		jcb_pais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(jcb_pais.getSelectedItem() != null) {
					jcb_provincia.removeAllItems();
					jcb_ciudad.removeAllItems();
					contexto.cargarListaProvincia(((PaisDTO) jcb_pais.getSelectedItem()).getIdPais(), false);
				}
			}
		});
		
		jcb_provincia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(jcb_provincia.getSelectedItem() != null) {
					jcb_ciudad.removeAllItems();
					contexto.cargarListaCiudad(((ProvinciaDTO) jcb_provincia.getSelectedItem()).getIdProvincia(), false);
				}
			}
			
		});
		
		jb_siguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (campos_validos.values().stream().filter(t->!t).collect(Collectors.toList()).size() != 0) {
					indicarCamposIncompletos();
					return;
				}

				DireccionDTO direc = new DireccionDTO(null, jtf_codigo_postal.getText(), jtf_calle.getText(),
														  jtf_numero.getText().isBlank()? null:Integer.valueOf(jtf_numero.getText()), 
														  jtf_piso.getText().isBlank()? null:Integer.valueOf(jtf_piso.getText()), 
														  jtf_departamento.getText(),
														  ((CiudadDTO)jcb_ciudad.getSelectedItem()).getIdCiudad(),
														  ((ProvinciaDTO)jcb_provincia.getSelectedItem()).getIdProvincia(),
														  ((PaisDTO)jcb_pais.getSelectedItem()).getIdPais());
														  
				PasajeroDTO p = new PasajeroDTO(null,jtf_nombres.getText(), jtf_apellido.getText(), jftf_cuit.getText().equals("__-________-_")? null :jftf_cuit.getText(),
												jtf_numero_documento.getText(), dc_nacimiento.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
												((PaisDTO) jcb_nacionalidad.getSelectedItem()).getIdPais(),
												jtf_email.getText(), jtf_telefono.getText(), jtf_ocupacion.getText(),
												(TipoDocumentoDTO) jcb_tipo_documento.getSelectedItem(), (PosicionIVADTO) jcb_factura.getSelectedItem(),
												direc);

				try {			
					GestorPasajeros.darAltaPasajero(p,true, cuit_obligatorio);
					int opt = Mensaje.mensajeConfirmacion("El pasajero "+p.getNombres()+", "+p.getApellido()+" ha sido satisfactoriamente cargado al sistema. ¿Desea cargar otro?", 
															"Alta Exitosa" , new String[]{"No", "Si"});
					if (opt == 1) {
						limpiarCampos();
						inicializarMapa();
					}
					else {
						((VentanaPrincipal)ventana_contenedora).cambiarPanel(new MenuBusquedaPasajero(ventana_contenedora, encabezado),
																			MenuBusquedaPasajero.x_bound,MenuBusquedaPasajero.y_bound,MenuBusquedaPasajero.titulo);
					}
				}
				catch(CamposInvalidosException exc){
					Mensaje.mensajeError(exc.errores.toArray(new String[]{}));
				}
				catch(DocumentoExistenteException exc) {
					int opt = Mensaje.warningDocumentoExistente();
					if(opt == 1) {
						try{GestorPasajeros.darAltaPasajero(p,false, cuit_obligatorio);}
						catch(DocumentoExistenteException exc2){} // nunca va a ser lanzada
						catch(CamposInvalidosException exc3){
							Mensaje.mensajeError(exc3.errores.toArray(new String[]{}));
						}catch(NuevoPasajeroException exc4){
							Mensaje.mensajeError(new String[] {"Hubo un error al registrar un nuevo pasajero."});
						}
						
						int opt2 = Mensaje.mensajeConfirmacion("El pasajero "+p.getNombres()+", "+p.getApellido()+" ha sido satisfactoriamente cargado al sistema. ¿Desea cargar otro?",
																"Alta Exitosa" , new String[]{"No", "Si"});
						if (opt2 == 1) {
							limpiarCampos();
							inicializarMapa();
						}
						else {
							((VentanaPrincipal)ventana_contenedora).cambiarPanel(new MenuBusquedaPasajero(ventana_contenedora, encabezado),
																											MenuBusquedaPasajero.x_bound,MenuBusquedaPasajero.y_bound,MenuBusquedaPasajero.titulo);
						}
					}
					else{
						jcb_tipo_documento.requestFocus();
					}
				} catch (NuevoPasajeroException exc) {
					Mensaje.mensajeError(new String[]{"Hubo un error al registrar un nuevo pasajero"});
				}
				
			}
		});
		
		jb_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opt = Mensaje.mensajeConfirmacion("¿Desea cancelar el alta del pasajero?");
				if (opt == 1) { 
					menu_anterior.add(encabezado);
					((VentanaPrincipal)ventana_contenedora).cambiarPanel(menu_anterior,MenuBusquedaPasajero.x_bound,
																		MenuBusquedaPasajero.y_bound,MenuBusquedaPasajero.titulo);
				}
			}
		});
		
		jcb_factura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(jcb_factura.getSelectedItem() == null) return;

				if (((PosicionIVADTO)jcb_factura.getSelectedItem()).getPosicion().equals("R.I.")) {
					cuit_obligatorio = true;
					if(jftf_cuit.getText().equals("__-________-_")) {
						lbl_error_cuit.setText("Este campo no puede estar vacío");
						campos_validos.put("cuit", false);
					} else if(!jftf_cuit.getText().matches("[0-9][0-9]-[0-9]{8}-[0-9]")) {
						lbl_error_cuit.setText("El CUIT ingresado es invalido.");
						campos_validos.put("cuit", false);
					}
				}else{
					cuit_obligatorio = false;
					if (lbl_error_cuit.getText().equals("Este campo no puede estar vacío"))
						lbl_error_cuit.setText("");
				}
				
			}
		});
	}
	
	private void limpiarCampos() {
		for(Component componente : this.getComponents()) {
			if (componente instanceof JTextField) {
				((JTextField) componente).setText("");
			}
		}
		dc_nacimiento.setDate(null);
		this.inicializarCampos();
	}
	
	protected void indicarCamposIncompletos() {
		if (jtf_apellido.getText().isBlank())
			lbl_error_apellido.setText("Este campo no puede estar vacío.");
		if (jtf_nombres.getText().isBlank())
			lbl_error_nombres.setText("Este campo no puede estar vacío.");
		if (jtf_calle.getText().isBlank())
			lbl_error_calle.setText("Este campo no puede estar vacío.");
		if (jtf_numero.getText().isBlank())
			lbl_error_numero.setText("Este campo no puede estar vacío.");
		if (jtf_ocupacion.getText().isBlank())
			lbl_error_ocupacion.setText("Este campo no puede estar vacío.");
		if (jtf_numero_documento.getText().isBlank())
			lbl_error_numero_documento.setText("Este campo no puede estar vacío.");
		if (jtf_codigo_postal.getText().isBlank())
			lbl_error_codigo_postal.setText("Este campo no puede estar vacío.");
		if (jtf_telefono.getText().isBlank())
			lbl_error_telefono.setText("Este campo no puede estar vacío.");
		if (jcb_pais.getSelectedItem() == null)
			lbl_error_pais.setText("Este campo no puede estar vacío.");
		if (jcb_provincia.getSelectedItem() == null)
			lbl_error_provincia.setText("Este campo no puede estar vacío.");
		if (jcb_ciudad.getSelectedItem() == null)
			lbl_error_ciudad.setText("Este campo no puede estar vacío.");
		if(dc_nacimiento.getDate() == null)
			lbl_error_nacimiento.setText("Este campo no puede estar vacío.");

		if(cuit_obligatorio && jftf_cuit.getText().equals("__-________-_"))
			lbl_error_cuit.setText("Este campo no puede estar vacío");
	}
	
	private void inicializarMapa() {
		campos_validos.put("apellido", false);
		campos_validos.put("nombres", false);
		campos_validos.put("cuit", true);
		campos_validos.put("nacimiento", false);
		campos_validos.put("calle", false);
		campos_validos.put("numero", false);
		campos_validos.put("ocupacion", false);
		campos_validos.put("numero documento", false);
		campos_validos.put("codigo postal", true);
		campos_validos.put("piso", true);
		campos_validos.put("pais", true);
		campos_validos.put("provincia", true);
		campos_validos.put("ciudad", true);
		//campos_validos.put("departamento", true);
		campos_validos.put("telefono", false);
		campos_validos.put("email", true);
	}
	
	private void agregarListenersValidacion() {
		jtf_apellido.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {}
			
			public void focusLost(FocusEvent e) {
				String data = jtf_apellido.getText();
				if (data.isBlank()){
					lbl_error_apellido.setText("Este campo no puede estar vacío.");
					campos_validos.put("apellido", false);
				}
				else if(!data.matches("^[a-zA-ZÀ-ÿ\\u00f1\\u00d1]+(\\s*[a-zA-ZÀ-ÿ\\u00f1\\u00d1]*)*[a-zA-ZÀ-ÿ\\u00f1\\u00d1]+$")) {
					lbl_error_apellido.setText("El apellido solo puede contener letras.");
					campos_validos.put("apellido", false);
				}
				else {
					campos_validos.put("apellido", true);
					lbl_error_apellido.setText("");
				}
				
			}
		});
		
		jtf_nombres.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {}
			
			public void focusLost(FocusEvent e) {
				String data = jtf_nombres.getText();
				if (data.isBlank()){
					lbl_error_nombres.setText("Este campo no puede estar vacío.");
					campos_validos.put("nombres", false);
				}
				else if(!data.matches("^[a-zA-ZÀ-ÿ\\u00f1\\u00d1]+(\\s*[a-zA-ZÀ-ÿ\\u00f1\\u00d1]*)*[a-zA-ZÀ-ÿ\\\u00f1\\u00d1]+$")) {
					lbl_error_nombres.setText("Los nombres solo pueden contener letras.");
					campos_validos.put("nombres", false);
				}
				else {
					campos_validos.put("nombres", true);
					lbl_error_nombres.setText("");
				}
				
			}
		});
		
		jftf_cuit.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {}
			
			public void focusLost(FocusEvent e) {
				String data = jftf_cuit.getText();
				if(cuit_obligatorio && data.equals("__-________-_")) {
					lbl_error_cuit.setText("Este campo no puede estar vacío.");
					campos_validos.put("cuit", false);
				}
				else if(!data.matches("__-________-_|[0-9][0-9]-[0-9]{8}-[0-9]")) {
					lbl_error_cuit.setText("El CUIT ingresado es invalido.");
					campos_validos.put("cuit", false);
				}
				else {
					campos_validos.put("cuit", true);
					lbl_error_cuit.setText("");
				}
				
			}
		});
		
		dc_nacimiento.getDateEditor().getUiComponent().addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {}
			
			public void focusLost(FocusEvent e) {

				if (dc_nacimiento.getDate() == null){
					if (((JTextField) dc_nacimiento.getDateEditor().getUiComponent()).getText().equals("__/__/____"))
						lbl_error_nacimiento.setText("Este campo no puede estar vacío.");
					else lbl_error_nacimiento.setText("La fecha posee un formato invalido.");
					campos_validos.put("nacimiento", false); 
				} 
				else if (dc_nacimiento.getDate().after(new Date())) {
					lbl_error_nacimiento.setText("La fecha no puede ser posterior a la actual.");
					campos_validos.put("nacimiento", false); 
				}
				else {
					campos_validos.put("nacimiento", true);
					lbl_error_nacimiento.setText("");
				}
				
			}
		});
		
		dc_nacimiento.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent e) {
				if("date".equals(e.getPropertyName())) {
					campos_validos.put("nacimiento", true);
					lbl_error_nacimiento.setText("");
				}
			}
		});
		
		jtf_calle.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {}
			
			public void focusLost(FocusEvent e) {
				String data = jtf_calle.getText();
				if (data.isBlank()){
					lbl_error_calle.setText("Este campo no puede estar vacío.");
					campos_validos.put("calle", false);
				}
				else {
					campos_validos.put("calle", true);
					lbl_error_calle.setText("");
				}
				
			}
		});
		
		jtf_numero.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {}
			
			public void focusLost(FocusEvent e) {
				String data = jtf_numero.getText();
				if (data.isBlank()){
					lbl_error_numero.setText("Este campo no puede estar vacío.");
					campos_validos.put("numero", false);
				}
				else if(!data.matches("[0-9]+")) {
					lbl_error_numero.setText("El número de la calle debe ser un número.");
					campos_validos.put("numero", false);
				}
				else {
					campos_validos.put("numero", true);
					lbl_error_numero.setText("");
				}
				
			}
		});
		
		jtf_ocupacion.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {}
			
			public void focusLost(FocusEvent e) {
				String data = jtf_ocupacion.getText();
				if (data.isBlank()){
					lbl_error_ocupacion.setText("Este campo no puede estar vacío.");
					campos_validos.put("ocupacion", false);
				}
				else {
					campos_validos.put("ocupacion", true);
					lbl_error_ocupacion.setText("");
				}
				
			}
		});
		
		jtf_numero_documento.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {}
			
			public void focusLost(FocusEvent e) {
				String data = jtf_numero_documento.getText();
				if (data.isBlank()){
					lbl_error_numero_documento.setText("Este campo no puede estar vacío.");
					campos_validos.put("numero documento", false);
				}
				else {
					campos_validos.put("numero documento", true);
					lbl_error_numero_documento.setText("");
				}
			}
		});
		
		
		jtf_codigo_postal.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {}
			
			public void focusLost(FocusEvent e) {
				String data = jtf_codigo_postal.getText();
				if (data.isBlank()){
					lbl_error_codigo_postal.setText("Este campo no puede estar vacío.");
					campos_validos.put("codigo postal", false);
				}
				else if(!data.matches("[A-Z]*[0-9]+[A-Z]*")) {
					lbl_error_codigo_postal.setText("El código postal posee un formato invalido.");
					campos_validos.put("codigo postal", false);
				}
				else {
					campos_validos.put("codigo postal", true);
					lbl_error_codigo_postal.setText("");
				}
				
			}
		});
		
		jtf_piso.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {}
			
			public void focusLost(FocusEvent e) {
				String data = jtf_piso.getText();
				if(!data.matches("([0-9]|[1-9][0-9]|[1-9][0-9][0-9])?")) {
					lbl_error_piso.setText("El piso posee un formato invalido.");
					campos_validos.put("piso", false);
				}
				else {
					campos_validos.put("piso", true);
					lbl_error_piso.setText("");
				}
				
			}
		});
		
		jtf_telefono.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {}
			
			public void focusLost(FocusEvent e) {
				String data = jtf_telefono.getText();
				if (data.isBlank()){
					lbl_error_telefono.setText("Este campo no puede estar vacío.");
					campos_validos.put("telefono", false);
				}
				else if(!data.matches("(\\+)?([0-9]){7,15}")) {
					lbl_error_telefono.setText("El teléfono posee un formato invalido.");
					campos_validos.put("telefono", false);
				}
				else {
					campos_validos.put("telefono", true);
					lbl_error_telefono.setText("");
				}
				
			}
		});
		
		jtf_email.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {}
			
			public void focusLost(FocusEvent e) {
				String data = jtf_email.getText();
				
				if(!data.matches("(^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?!-)(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6})?")) {
					lbl_error_email.setText("El email posee un formato invalido.");
					campos_validos.put("email", false);
				}
				else {
					campos_validos.put("email", true);
					lbl_error_email.setText("");
				}
				
			}
		});
		
		jcb_pais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(jcb_pais.getSelectedItem() == null) {
					lbl_error_pais.setText("Este campo no puede estar vacío.");
					campos_validos.put("pais",false);
				} else {
					lbl_error_pais.setText("");
					campos_validos.put("pais",true);
				}
				
			}
			
		});
		
		jcb_provincia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(jcb_provincia.getSelectedItem() == null) {
					lbl_error_provincia.setText("Este campo no puede estar vacío.");
					campos_validos.put("provincia",false);
				} else {
					lbl_error_provincia.setText("");
					campos_validos.put("provincia",true);
				}
				
			}
			
		});
		
		jcb_ciudad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(jcb_ciudad.getSelectedItem() == null) {
					lbl_error_ciudad.setText("Este campo no puede estar vacío.");
					campos_validos.put("ciudad",false);
				} else {
					lbl_error_ciudad.setText("");
					campos_validos.put("ciudad",true);
				}
				
			}
			
		});
		
	}

	@Override
	public void setDefaultTab() {
		jtf_apellido.requestFocus();
	}
}
