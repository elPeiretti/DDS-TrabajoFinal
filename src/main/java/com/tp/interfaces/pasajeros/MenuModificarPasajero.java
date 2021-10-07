package com.tp.interfaces.pasajeros;

import java.util.Date;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;

import com.tp.dominio.Ciudad;
import com.tp.dominio.Direccion;
import com.tp.dominio.Pais;
import com.tp.dominio.Pasajero;
import com.tp.dominio.PosicionIVA;
import com.tp.dominio.Provincia;
import com.tp.dominio.TipoDocumento;
import com.tp.interfaces.misc.Encabezado;

public class MenuModificarPasajero extends JPanel {
	
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
	
	public MenuModificarPasajero(JFrame ventana_contenedora, JPanel encabezado) {
		this.ventana_contenedora = ventana_contenedora;
		setLayout(null);
		
		jtf_apellido = new JTextField();
		jtf_apellido.setBounds(111, 105, 122, 20);
		add(jtf_apellido);
		jtf_apellido.setColumns(10);
		
		JLabel lbl_apellido = new JLabel("Apellido (*)");
		lbl_apellido.setBounds(23, 108, 68, 14);
		add(lbl_apellido);
		
		JLabel lbl_nombres = new JLabel("Nombres (*)");
		lbl_nombres.setBounds(23, 139, 68, 14);
		add(lbl_nombres);
		
		jtf_nombres = new JTextField();
		jtf_nombres.setColumns(10);
		jtf_nombres.setBounds(111, 136, 122, 20);
		add(jtf_nombres);
		
		jtf_cuit = new JTextField();
		jtf_cuit.setColumns(10);
		jtf_cuit.setBounds(111, 167, 122, 20);
		add(jtf_cuit);
		
		JLabel lbl_cuit = new JLabel("CUIT");
		lbl_cuit.setBounds(23, 170, 68, 14);
		add(lbl_cuit);
		
		jcb_pais = new JComboBox<Pais>();
		jcb_pais.setBounds(111, 245, 122, 24);
		add(jcb_pais);
		
		JLabel lbl_pais = new JLabel("Pais (*)");
		lbl_pais.setBounds(23, 250, 68, 14);
		add(lbl_pais);
		
		JLabel lbl_ciudad = new JLabel("Ciudad (*)");
		lbl_ciudad.setBounds(23, 285, 68, 14);
		add(lbl_ciudad);
		
		jcb_ciudad = new JComboBox<Ciudad>();
		jcb_ciudad.setBounds(111, 280, 122, 24);
		add(jcb_ciudad);
		
		jcb_provincia = new JComboBox<Provincia>();
		jcb_provincia.setBounds(495, 245, 122, 24);
		add(jcb_provincia);
		
		JLabel lbl_provincia = new JLabel("Provincia (*)");
		lbl_provincia.setBounds(346, 250, 68, 14);
		add(lbl_provincia);
		
		JLabel lbl_tipo_documento = new JLabel("Tipo de Documento (*)");
		lbl_tipo_documento.setBounds(346, 108, 139, 14);
		add(lbl_tipo_documento);
		
		jcb_tipo_documento = new JComboBox<TipoDocumento>();
		jcb_tipo_documento.setBounds(495, 101, 122, 24);
		add(jcb_tipo_documento);
		
		jtf_numero_documento = new JTextField();
		jtf_numero_documento.setColumns(10);
		jtf_numero_documento.setBounds(495, 139, 122, 20);
		add(jtf_numero_documento);
		
		JLabel lbl_numero_documento = new JLabel("Numero de Documento (*)");
		lbl_numero_documento.setBounds(346, 142, 139, 14);
		add(lbl_numero_documento);
		
		JLabel lbl_factura = new JLabel("Factura (*)");
		lbl_factura.setBounds(346, 173, 68, 14);
		add(lbl_factura);
		
		jcb_factura = new JComboBox<PosicionIVA>();
		jcb_factura.setBounds(495, 170, 122, 24);
		add(jcb_factura);
		
		JLabel lbl_calle = new JLabel("Calle (*)");
		lbl_calle.setBounds(23, 336, 84, 14);
		add(lbl_calle);
		
		jtf_calle = new JTextField();
		jtf_calle.setColumns(10);
		jtf_calle.setBounds(111, 333, 122, 20);
		add(jtf_calle);
		
		JLabel lbl_nacimiento = new JLabel("Nacimiento (*)");
		lbl_nacimiento.setBounds(23, 205, 84, 14);
		add(lbl_nacimiento);
		
		JLabel lbl_numero = new JLabel("Numero (*)");
		lbl_numero.setBounds(23, 381, 84, 14);
		add(lbl_numero);
		
		jtf_numero = new JTextField();
		jtf_numero.setColumns(10);
		jtf_numero.setBounds(111, 378, 122, 20);
		add(jtf_numero);
		
		JLabel lbl_ocupacion = new JLabel("Ocupacion (*)");
		lbl_ocupacion.setBounds(23, 412, 84, 14);
		add(lbl_ocupacion);
		
		jtf_ocupacion = new JTextField();
		jtf_ocupacion.setColumns(10);
		jtf_ocupacion.setBounds(111, 409, 122, 20);
		add(jtf_ocupacion);
		
		JButton btn_cancelar = new JButton("Cancelar");
		btn_cancelar.setBounds(400, 447, 91, 23);
		add(btn_cancelar);
		
		JButton btn_siguiente = new JButton("Siguiente");
		btn_siguiente.setBounds(526, 447, 91, 23);
		add(btn_siguiente);
		
		JLabel lbl_codigo_postal = new JLabel("Codigo Postal (*)");
		lbl_codigo_postal.setBounds(346, 285, 84, 14);
		add(lbl_codigo_postal);
		
		jtf_codigo_postal = new JTextField();
		jtf_codigo_postal.setColumns(10);
		jtf_codigo_postal.setBounds(495, 282, 122, 20);
		add(jtf_codigo_postal);
		
		JLabel lbl_piso = new JLabel("Piso");
		lbl_piso.setBounds(346, 336, 30, 14);
		add(lbl_piso);
		
		jtf_piso = new JTextField();
		jtf_piso.setColumns(10);
		jtf_piso.setBounds(386, 333, 44, 20);
		add(jtf_piso);
		
		JLabel lbl_departamento = new JLabel("Departamento");
		lbl_departamento.setBounds(457, 336, 71, 14);
		add(lbl_departamento);
		
		jtf_departamento = new JTextField();
		jtf_departamento.setColumns(10);
		jtf_departamento.setBounds(538, 333, 79, 20);
		add(jtf_departamento);
		
		JLabel lbl_telefono = new JLabel("Telefono (*)");
		lbl_telefono.setBounds(346, 378, 139, 14);
		add(lbl_telefono);
		
		jtf_telefono = new JTextField();
		jtf_telefono.setColumns(10);
		jtf_telefono.setBounds(457, 375, 160, 20);
		add(jtf_telefono);
		
		jcb_nacionalidad = new JComboBox();
		jcb_nacionalidad.setBounds(457, 412, 160, 24);
		add(jcb_nacionalidad);
		
		JLabel lbl_nacionalidad = new JLabel("Nacionalidad (*)");
		lbl_nacionalidad.setBounds(346, 417, 84, 14);
		add(lbl_nacionalidad);
		
		this.encabezado = encabezado;
		encabezado.setBounds(0, 0, 640, 80);
		add(encabezado);
		
		dc_nacimiento = new JDateChooser("yyyy/MM/dd", "####/##/##", '_');
		dc_nacimiento.setBounds(111,205,122,20);
		add(dc_nacimiento);
		
		jtf_email = new JTextField();
		jtf_email.setColumns(10);
		jtf_email.setBounds(457, 410, 160, 20);
		add(jtf_email);
		
		JLabel lbl_email = new JLabel("E-mail");
		lbl_email.setBounds(346, 413, 139, 14);
		add(lbl_email);
	}
	
	/*
	 * Implementado en metodo aparte para mantener los constructores iguales
	 */
	void cargarPasajero(Pasajero psjr) {
		Direccion dir = psjr.getDireccion();
		
		jtf_apellido.setText(psjr.getApellido());
		jtf_nombres.setText(psjr.getNombre());
		jtf_cuit.setText(psjr.getCuit());
		jtf_numero_documento.setText(psjr.getNroDocumento());
		jtf_calle.setText(dir.getCalle());
		jtf_numero.setText(dir.getNroCalle().toString());
		jtf_ocupacion.setText(psjr.getOcupacion());
		jtf_codigo_postal.setText(dir.getCodigoPostal().toString()); // ver comentarios en Direccion
		jtf_piso.setText(dir.getPiso().toString());
		jtf_departamento.setText(dir.getNroDepartamento().toString()); // ver comentarios en Direccion
		jtf_telefono.setText(psjr.getTelefono());
		dc_nacimiento.setDate(Date.from(psjr.getNacimiento()));
		jcb_pais.setSelectedItem(dir.getPais());
		jcb_provincia.setSelectedItem(dir.getProvincia());
		jcb_ciudad.setSelectedItem(dir.getCiudad());
		jcb_factura.setSelectedItem(psjr.getPosicionIVA());
		jcb_nacionalidad.setSelectedItem(psjr.getNacionalidad()); // ver comentarios en Pasajero
	}

}
