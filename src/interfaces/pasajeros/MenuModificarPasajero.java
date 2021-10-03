package interfaces.pasajeros;

import java.util.Date;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;

import dominio.Ciudad;
import dominio.Direccion;
import dominio.Pais;
import dominio.Pasajero;
import dominio.PosicionIVA;
import dominio.Provincia;
import dominio.TipoDocumento;
import interfaces.misc.Encabezado;

public class MenuModificarPasajero extends JPanel {

	private JFrame ventana_contenedora;
	private JTextField tf_apellido_pasajero;
	private JTextField tf_nombres_pasajero;
	private JTextField tf_cuit;
	private JTextField tf_numero_documento;
	private JTextField tf_calle;
	private JTextField tf_numero_direccion;
	private JTextField tf_ocupacion;
	private JTextField tf_codigo_postal;
	private JTextField tf_piso_direccion;
	private JTextField tf_departamento_direccion;
	private JTextField tf_telefono;
	private JPanel p_encabezado;
	private JDateChooser fecha_nacimiento;
	private JComboBox<Pais> cb_paises;
	private JComboBox<Ciudad> cb_ciudades;
	private JComboBox<Provincia> cb_provincias;
	private JComboBox<TipoDocumento> cb_tipos_documento;
	private JComboBox<PosicionIVA> cb_factura;
	private JComboBox cb_nacionalidad; //
	private JTextField tf_email;
	
	public MenuModificarPasajero(JFrame ventana_contenedora) {
		this.ventana_contenedora = ventana_contenedora;
		setLayout(null);
		
		tf_apellido_pasajero = new JTextField();
		tf_apellido_pasajero.setBounds(111, 105, 122, 20);
		add(tf_apellido_pasajero);
		tf_apellido_pasajero.setColumns(10);
		
		JLabel lbl_apellido = new JLabel("Apellido (*)");
		lbl_apellido.setBounds(23, 108, 68, 14);
		add(lbl_apellido);
		
		JLabel lbl_nombres = new JLabel("Nombres (*)");
		lbl_nombres.setBounds(23, 139, 68, 14);
		add(lbl_nombres);
		
		tf_nombres_pasajero = new JTextField();
		tf_nombres_pasajero.setColumns(10);
		tf_nombres_pasajero.setBounds(111, 136, 122, 20);
		add(tf_nombres_pasajero);
		
		tf_cuit = new JTextField();
		tf_cuit.setColumns(10);
		tf_cuit.setBounds(111, 167, 122, 20);
		add(tf_cuit);
		
		JLabel lbl_cuit = new JLabel("CUIT");
		lbl_cuit.setBounds(23, 170, 68, 14);
		add(lbl_cuit);
		
		cb_paises = new JComboBox<Pais>();
		cb_paises.setBounds(111, 245, 122, 24);
		add(cb_paises);
		
		JLabel lbl_pais = new JLabel("Pais (*)");
		lbl_pais.setBounds(23, 250, 68, 14);
		add(lbl_pais);
		
		JLabel lbl_ciudad = new JLabel("Ciudad (*)");
		lbl_ciudad.setBounds(23, 285, 68, 14);
		add(lbl_ciudad);
		
		cb_ciudades = new JComboBox<Ciudad>();
		cb_ciudades.setBounds(111, 280, 122, 24);
		add(cb_ciudades);
		
		cb_provincias = new JComboBox<Provincia>();
		cb_provincias.setBounds(495, 245, 122, 24);
		add(cb_provincias);
		
		JLabel lbl_provincia = new JLabel("Provincia (*)");
		lbl_provincia.setBounds(346, 250, 68, 14);
		add(lbl_provincia);
		
		JLabel lbl_tipo_documento = new JLabel("Tipo de Documento (*)");
		lbl_tipo_documento.setBounds(346, 108, 139, 14);
		add(lbl_tipo_documento);
		
		cb_tipos_documento = new JComboBox<TipoDocumento>();
		cb_tipos_documento.setBounds(495, 101, 122, 24);
		add(cb_tipos_documento);
		
		tf_numero_documento = new JTextField();
		tf_numero_documento.setColumns(10);
		tf_numero_documento.setBounds(495, 139, 122, 20);
		add(tf_numero_documento);
		
		JLabel lbl_numero_documento = new JLabel("Numero de Documento (*)");
		lbl_numero_documento.setBounds(346, 142, 139, 14);
		add(lbl_numero_documento);
		
		JLabel lbl_factura = new JLabel("Factura (*)");
		lbl_factura.setBounds(346, 173, 68, 14);
		add(lbl_factura);
		
		cb_factura = new JComboBox<PosicionIVA>();
		cb_factura.setBounds(495, 170, 122, 24);
		add(cb_factura);
		
		JLabel lbl_calle = new JLabel("Calle (*)");
		lbl_calle.setBounds(23, 336, 84, 14);
		add(lbl_calle);
		
		tf_calle = new JTextField();
		tf_calle.setColumns(10);
		tf_calle.setBounds(111, 333, 122, 20);
		add(tf_calle);
		
		JLabel lbl_nacimiento = new JLabel("Nacimiento (*)");
		lbl_nacimiento.setBounds(23, 205, 84, 14);
		add(lbl_nacimiento);
		
		JLabel lbl_numero = new JLabel("Numero (*)");
		lbl_numero.setBounds(23, 381, 84, 14);
		add(lbl_numero);
		
		tf_numero_direccion = new JTextField();
		tf_numero_direccion.setColumns(10);
		tf_numero_direccion.setBounds(111, 378, 122, 20);
		add(tf_numero_direccion);
		
		JLabel lbl_ocupacion = new JLabel("Ocupacion (*)");
		lbl_ocupacion.setBounds(23, 412, 84, 14);
		add(lbl_ocupacion);
		
		tf_ocupacion = new JTextField();
		tf_ocupacion.setColumns(10);
		tf_ocupacion.setBounds(111, 409, 122, 20);
		add(tf_ocupacion);
		
		JButton btn_cancelar = new JButton("Cancelar");
		btn_cancelar.setBounds(400, 447, 91, 23);
		add(btn_cancelar);
		
		JButton btn_siguiente = new JButton("Siguiente");
		btn_siguiente.setBounds(526, 447, 91, 23);
		add(btn_siguiente);
		
		JLabel lbl_codigo_postal = new JLabel("Codigo Postal (*)");
		lbl_codigo_postal.setBounds(346, 285, 84, 14);
		add(lbl_codigo_postal);
		
		tf_codigo_postal = new JTextField();
		tf_codigo_postal.setColumns(10);
		tf_codigo_postal.setBounds(495, 282, 122, 20);
		add(tf_codigo_postal);
		
		JLabel lbl_piso = new JLabel("Piso");
		lbl_piso.setBounds(346, 336, 30, 14);
		add(lbl_piso);
		
		tf_piso_direccion = new JTextField();
		tf_piso_direccion.setColumns(10);
		tf_piso_direccion.setBounds(386, 333, 44, 20);
		add(tf_piso_direccion);
		
		JLabel lbl_departamento = new JLabel("Departamento");
		lbl_departamento.setBounds(457, 336, 71, 14);
		add(lbl_departamento);
		
		tf_departamento_direccion = new JTextField();
		tf_departamento_direccion.setColumns(10);
		tf_departamento_direccion.setBounds(538, 333, 79, 20);
		add(tf_departamento_direccion);
		
		JLabel lbl_telefono = new JLabel("Telefono (*)");
		lbl_telefono.setBounds(346, 378, 139, 14);
		add(lbl_telefono);
		
		tf_telefono = new JTextField();
		tf_telefono.setColumns(10);
		tf_telefono.setBounds(457, 375, 160, 20);
		add(tf_telefono);
		
		cb_nacionalidad = new JComboBox();
		cb_nacionalidad.setBounds(457, 412, 160, 24);
		add(cb_nacionalidad);
		
		JLabel lbl_nacionalidad = new JLabel("Nacionalidad (*)");
		lbl_nacionalidad.setBounds(346, 417, 84, 14);
		add(lbl_nacionalidad);
		
		p_encabezado = new Encabezado();
		p_encabezado.setBounds(0, 0, 640, 80);
		add(p_encabezado);
		
		fecha_nacimiento = new JDateChooser("yyyy/MM/dd", "####/##/##", '_');
		fecha_nacimiento.setBounds(111,205,122,20);
		add(fecha_nacimiento);
		
		tf_email = new JTextField();
		tf_email.setColumns(10);
		tf_email.setBounds(457, 410, 160, 20);
		add(tf_email);
		
		JLabel lbl_email = new JLabel("E-mail");
		lbl_email.setBounds(346, 413, 139, 14);
		add(lbl_email);
	}
	
	/*
	 * Implementado en metodo aparte para mantener los constructores iguales
	 */
	void cargarPasajero(Pasajero psjr) {
		Direccion dir = psjr.getDireccion();
		
		tf_apellido_pasajero.setText(psjr.getApellido());
		tf_nombres_pasajero.setText(psjr.getNombre());
		tf_cuit.setText(psjr.getCuit());
		tf_numero_documento.setText(psjr.getNroDocumento());
		tf_calle.setText(dir.getCalle());
		tf_numero_direccion.setText(dir.getNroCalle().toString());
		tf_ocupacion.setText(psjr.getOcupacion());
		tf_codigo_postal.setText(dir.getCodigoPostal().toString()); // ver comentarios en Direccion
		tf_piso_direccion.setText(dir.getPiso().toString());
		tf_departamento_direccion.setText(dir.getNroDepartamento().toString()); // ver comentarios en Direccion
		tf_telefono.setText(psjr.getTelefono());
		fecha_nacimiento.setDate(Date.from(psjr.getNacimiento()));
		cb_paises.setSelectedItem(dir.getPais());
		cb_provincias.setSelectedItem(dir.getProvincia());
		cb_ciudades.setSelectedItem(dir.getCiudad());
		cb_factura.setSelectedItem(psjr.getPosicionIVA());
		cb_nacionalidad.setSelectedItem(psjr.getNacionalidad()); // ver comentarios en Pasajero
	}

}
