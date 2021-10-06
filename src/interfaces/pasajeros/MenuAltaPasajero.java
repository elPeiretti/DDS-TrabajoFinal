package interfaces.pasajeros;

import javax.swing.*;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JSpinnerDateEditor;

import dominio.Ciudad;
import dominio.Pais;
import dominio.PosicionIVA;
import dominio.Provincia;
import dominio.TipoDocumento;
import interfaces.misc.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;



public class MenuAltaPasajero extends JPanel {

	private JFrame ventana_contenedora;
	private JTextField jtf_ocupacion;
	private JPanel encabezado;
	private JDateChooser dc_nacimiento;
	private JTextField jtf_numero;
	private JTextField jtf_cuit;
	private JTextField jtf_nombres;
	private JTextField jtf_apellido;
	private JTextField jtf_telefono;
	private JTextField jtf_piso;
	private JTextField jtf_departamento;
	private JTextField jtf_codigo_postal;
	private JTextField jtf_email;
	private JComboBox<Pais> jcb_pais;
	private JComboBox<Ciudad> jcb_ciudad;
	private JComboBox<Provincia> jcb_provincia;
	private JComboBox<TipoDocumento> jcb_tipo_documento;
	private JComboBox<PosicionIVA> jcb_factura;
	private JLabel lbl_ocupacion;
	private JButton jb_cancelar;
	private JButton jb_siguiente;
	private JComboBox jcb_nacionalidad; //ver comentario en Pasajero
	private JLabel lbl_nacionalidad;
	private JLabel lbl_error_ocupacion;
	private JLabel lbl_numero;
	private JLabel lbl_error_numero;
	private JLabel lbl_calle;
	private JTextField jtf_calle;
	private JLabel lbl_error_calle;
	private JLabel lbl_ciudad;
	private JLabel lbl_error_ciudad;
	private JLabel lbl_pais;
	private JLabel lbl_error_pais;
	private JLabel lbl_nacimiento;
	private JLabel lbl_error_nacimiento;
	private JLabel lbl_cuit;
	private JLabel lbl_apellido;
	private JLabel lbl_error_apellido;
	private JLabel lbl_telefono;
	private JLabel lbl_piso;
	private JLabel lbl_departamento;
	private JLabel lbl_factura;
	private JLabel lbl_numero_documento;
	private JTextField jtf_numero_documento;
	private JLabel lbl_tipo_documento;
	private JLabel lbl_email;
	private JLabel lbl_codigo_postal;
	private JLabel lbl_provincia;
	private JLabel lbl_error_tipo_documento;
	private JLabel lbl_error_numero_documento;
	private JLabel lbl_error_factura;
	private JLabel lbl_error_provincia;
	private JLabel lbl_error_codigo_postal;
	private JLabel lbl_error_piso;
	private JLabel lbl_error_departamento;
	private JLabel lbl_error_telefono;
	private JLabel lbl_error_email;
	private JLabel lbl_error_nacionalidad;
	private MenuBusquedaPasajero menu_anterior;
	private HashMap<String,Boolean> campos_validos;
	private JLabel lbl_error_nombres;
	private JLabel lbl_error_cuit;
	
	// fijar ventana contenedora a 640x620
	public MenuAltaPasajero(JFrame ventana_contenedora, JPanel encabezado, MenuBusquedaPasajero estado_anterior) {
		setBackground(Color.WHITE);
		this.menu_anterior = estado_anterior;
		this.ventana_contenedora = ventana_contenedora;
		ventana_contenedora.setSize(660,620);
		setLayout(null);
		
		this.campos_validos = new HashMap<String,Boolean>();
		inicializarMapa();
		
		jcb_pais = new JComboBox();
		jcb_pais.setBounds(128, 319, 122, 20);
		add(jcb_pais);
		
		jcb_ciudad = new JComboBox();
		jcb_ciudad.setBounds(128, 361, 122, 20);
		add(jcb_ciudad);
		
		jcb_provincia = new JComboBox();
		jcb_provincia.setBounds(495, 277, 122, 20);
		add(jcb_provincia);
		
		jcb_tipo_documento = new JComboBox();
		jcb_tipo_documento.setBounds(495, 151, 122, 20);
		add(jcb_tipo_documento);
		
		jcb_factura = new JComboBox();
		jcb_factura.setBounds(495, 235, 122, 20);
		add(jcb_factura);
		
		lbl_ocupacion = new JLabel("<html>Ocupacion <font color='red'>(*)</font>:</html>");
		lbl_ocupacion.setBounds(23, 489, 84, 14);
		add(lbl_ocupacion);
		
		jtf_ocupacion = new JTextField();
		jtf_ocupacion.setColumns(10);
		jtf_ocupacion.setBounds(128, 486, 122, 20);
		jtf_ocupacion.setDocument(new JTextFieldLimit(30));
		add(jtf_ocupacion);
		
		jb_cancelar = new JButton("Cancelar");
		jb_cancelar.setBounds(400, 539, 91, 30);
		add(jb_cancelar);
		
		jb_siguiente = new JButton("Siguiente");
		jb_siguiente.setBounds(526, 539, 91, 30);
		add(jb_siguiente);
		
		jcb_nacionalidad = new JComboBox();
		jcb_nacionalidad.setBounds(495, 486, 122, 20);
		add(jcb_nacionalidad);
		
		lbl_nacionalidad = new JLabel("<html>Nacionalidad <font color='red'>(*)</font>:</html>");
		lbl_nacionalidad.setBounds(334, 489, 110, 14);
		add(lbl_nacionalidad);
		
		this.encabezado = encabezado;
		encabezado.setBounds(0, 0, 640, 110);
		add(encabezado);
		
		dc_nacimiento = new JDateChooser("dd/MM/yyyy", "##/##/####", '_');
		dc_nacimiento.setBounds(128,277,122,20);
		add(dc_nacimiento);
		
		lbl_error_ocupacion = new JLabel("");
		lbl_error_ocupacion.setForeground(Color.RED);
		lbl_error_ocupacion.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lbl_error_ocupacion.setBounds(23, 510, 227, 10);
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
		lbl_nacimiento.setBounds(23, 278, 84, 14);
		add(lbl_nacimiento);
		
		lbl_error_nacimiento = new JLabel("");
		lbl_error_nacimiento.setForeground(Color.RED);
		lbl_error_nacimiento.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lbl_error_nacimiento.setBounds(23, 301, 227, 10);
		add(lbl_error_nacimiento);
		
		lbl_cuit = new JLabel("CUIT:");
		lbl_cuit.setBounds(23, 238, 84, 14);
		add(lbl_cuit);
		
		jtf_cuit = new JTextField();
		jtf_cuit.setColumns(10);
		jtf_cuit.setBounds(128, 235, 122, 20);
		jtf_cuit.setDocument(new JTextFieldLimit(11));
		add(jtf_cuit);
		
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
		lbl_telefono.setBounds(334, 405, 84, 14);
		add(lbl_telefono);
		
		jtf_telefono = new JTextField();
		jtf_telefono.setColumns(10);
		jtf_telefono.setBounds(495, 402, 122, 20);
		jtf_telefono.setDocument(new JTextFieldLimit(15));
		add(jtf_telefono);
		
		lbl_piso = new JLabel("Piso:");
		lbl_piso.setBounds(334, 363, 62, 14);
		add(lbl_piso);
		
		jtf_piso = new JTextField();
		jtf_piso.setColumns(10);
		jtf_piso.setBounds(374, 360, 30, 20);
		jtf_piso.setDocument(new JTextFieldLimit(3));
		add(jtf_piso);
		
		lbl_departamento = new JLabel("Departamento:");
		lbl_departamento.setBounds(438, 364, 85, 14);
		add(lbl_departamento);
		
		jtf_departamento = new JTextField();
		jtf_departamento.setColumns(10);
		jtf_departamento.setBounds(533, 361, 84, 20);
		jtf_departamento.setDocument(new JTextFieldLimit(5));
		add(jtf_departamento);
		
		lbl_codigo_postal = new JLabel("<html>C\u00F3digo Postal <font color='red'>(*)</font>:</html>");
		lbl_codigo_postal.setBounds(334, 322, 110, 14);
		add(lbl_codigo_postal);
		
		jtf_codigo_postal = new JTextField();
		jtf_codigo_postal.setColumns(10);
		jtf_codigo_postal.setBounds(495, 319, 122, 20);
		jtf_codigo_postal.setDocument(new JTextFieldLimit(10));
		add(jtf_codigo_postal);
		
		lbl_provincia = new JLabel("<html>Provincia <font color='red'>(*)</font>:</html>");
		lbl_provincia.setBounds(334, 280, 84, 14);
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
		lbl_email.setBounds(334, 447, 84, 14);
		add(lbl_email);
		
		jtf_email = new JTextField();
		jtf_email.setColumns(10);
		jtf_email.setBounds(495, 444, 122, 20);
		jtf_email.setDocument(new JTextFieldLimit(70));
		add(jtf_email);
		
		lbl_error_nacionalidad = new JLabel("");
		lbl_error_nacionalidad.setForeground(Color.RED);
		lbl_error_nacionalidad.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lbl_error_nacionalidad.setBounds(334, 510, 283, 10);
		add(lbl_error_nacionalidad);
		
		lbl_error_email = new JLabel("");
		lbl_error_email.setForeground(Color.RED);
		lbl_error_email.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lbl_error_email.setBounds(334, 468, 283, 10);
		add(lbl_error_email);
		
		lbl_error_telefono = new JLabel("");
		lbl_error_telefono.setForeground(Color.RED);
		lbl_error_telefono.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lbl_error_telefono.setBounds(334, 426, 283, 10);
		add(lbl_error_telefono);
		
		lbl_error_piso = new JLabel("");
		lbl_error_piso.setForeground(Color.RED);
		lbl_error_piso.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lbl_error_piso.setBounds(334, 385, 91, 10);
		add(lbl_error_piso);
		
		lbl_error_codigo_postal = new JLabel("");
		lbl_error_codigo_postal.setForeground(Color.RED);
		lbl_error_codigo_postal.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lbl_error_codigo_postal.setBounds(334, 343, 283, 10);
		add(lbl_error_codigo_postal);
		
		lbl_error_provincia = new JLabel("");
		lbl_error_provincia.setForeground(Color.RED);
		lbl_error_provincia.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lbl_error_provincia.setBounds(334, 301, 283, 10);
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
		
		lbl_error_departamento = new JLabel("");
		lbl_error_departamento.setForeground(Color.RED);
		lbl_error_departamento.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lbl_error_departamento.setBounds(438, 385, 179, 10);
		add(lbl_error_departamento);
		
		this.agregarActionListeners();
		this.agregarListenersValidacion();
		this.setCamposDefault();
	}
	
	private void agregarActionListeners() {
		
		jb_siguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// subir los datos a la BD
					int opt = Mensaje.mensajeConfirmacion("¿Desea cargar otro pasajero?");
					if (opt == 1) 
						limpiarCampos();
					else {
						ventana_contenedora.setContentPane(menu_anterior);
						ventana_contenedora.setVisible(true);
					}
					
				}
				catch(Exception exc) { //TODO
					
				}
				
			}
		});
		
		jb_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opt = Mensaje.mensajeConfirmacion("¿Desea cancelar el alta del pasajero?");
				if (opt == 1) { 
					ventana_contenedora.setContentPane(menu_anterior);
					ventana_contenedora.setVisible(true);
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
		setCamposDefault();
	}
	
	private void setCamposDefault() {
		jtf_codigo_postal.setText("3000");
		//jcb_tipo_documento.setSelectedItem();
		//jcb_pais.setSelectedItem();
		//jcb_ciudad.setSelectedItem();
		//jcb_factura.setSelectedItem();
		//jcb_provincia.setSelectedItem();
		//jcb_nacionalidad.setSelectedItem();
	}
	
	private void inicializarMapa() {
		campos_validos.put("apellido", false);
		campos_validos.put("nombres", false);
		campos_validos.put("cuit", false);
		campos_validos.put("calle", false);
		campos_validos.put("numero", false);
		campos_validos.put("ocupacion", false);
		campos_validos.put("numero documento", false);
		campos_validos.put("codigo postal", false);
		campos_validos.put("piso", false);
		campos_validos.put("departamento", false);
		campos_validos.put("telefono", false);
		campos_validos.put("email", false);
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
				else if(!data.matches("^[a-zA-ZÀ-ÿ\\u00f1\\u00d1]+(\\s*[a-zA-ZÀ-ÿ\\u00f1\\u00d1]*)*[a-zA-ZÀ-ÿ\\u00f1\\u00d1]*$")) {
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
				else if(!data.matches("^[a-zA-ZÀ-ÿ\\u00f1\\u00d1]+(\\s*[a-zA-ZÀ-ÿ\\u00f1\\u00d1]*)*[a-zA-ZÀ-ÿ\\u00f1\\u00d1]*$")) {
					lbl_error_nombres.setText("Los nombres solo pueden contener letras.");
					campos_validos.put("nombres", false);
				}
				else {
					campos_validos.put("nombres", true);
					lbl_error_nombres.setText("");
				}
				
			}
		});
		
		jtf_cuit.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {}
			
			public void focusLost(FocusEvent e) {
				String data = jtf_cuit.getText();
				
				if(!data.matches("[0-9]*")) {
					lbl_error_cuit.setText("El CUIT solo puede contener números.");
					campos_validos.put("cuit", false);
				}
				else {
					campos_validos.put("cuit", true);
					lbl_error_cuit.setText("");
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
				else if(!data.matches("^[a-zA-ZÀ-ÿ\\u00f1\\u00d1]+(\\s*[a-zA-ZÀ-ÿ\\u00f1\\u00d1]*)*[a-zA-ZÀ-ÿ\\u00f1\\u00d1]*$")) {
					lbl_error_calle.setText("La calle solo puede contener letras.");
					campos_validos.put("calle", false);
				}
				else {
					campos_validos.put("calle", true);
					lbl_error_nombres.setText("");
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
		
		
	}
}
