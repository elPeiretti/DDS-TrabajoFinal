package interfaces.pasajeros;

import javax.swing.*;

import interfaces.misc.Encabezado;

public class MenuAltaPasajero extends JPanel {

	private JFrame ventana_contenedora;
	private JTextField tf_apellido_pasajero;
	private JTextField tf_nombres_pasajero;
	private JTextField tf_cuit;
	private JTextField tf_numero_documento;
	private JTextField tf_calle;
	private JTextField tf_nacimiento;
	private JTextField tf_numero_direccion;
	private JTextField tf_ocupacion;
	private JTextField tf_codigo_postal;
	private JTextField tf_piso_direccion;
	private JTextField tf_departamento_direccion;
	private JTextField tf_telefono;
	private JPanel p_encabezado;
	
	public MenuAltaPasajero(JFrame ventana_contenedora) {
		this.ventana_contenedora = ventana_contenedora;
		setLayout(null);
		
		tf_apellido_pasajero = new JTextField();
		tf_apellido_pasajero.setBounds(111, 105, 122, 20);
		add(tf_apellido_pasajero);
		tf_apellido_pasajero.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Apellido (*)");
		lblNewLabel.setBounds(23, 108, 68, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombres (*)");
		lblNewLabel_1.setBounds(23, 139, 68, 14);
		add(lblNewLabel_1);
		
		tf_nombres_pasajero = new JTextField();
		tf_nombres_pasajero.setColumns(10);
		tf_nombres_pasajero.setBounds(111, 136, 122, 20);
		add(tf_nombres_pasajero);
		
		tf_cuit = new JTextField();
		tf_cuit.setColumns(10);
		tf_cuit.setBounds(111, 167, 122, 20);
		add(tf_cuit);
		
		JLabel lblNewLabel_1_1 = new JLabel("CUIT");
		lblNewLabel_1_1.setBounds(23, 170, 68, 14);
		add(lblNewLabel_1_1);
		
		JComboBox cb_paises = new JComboBox();
		cb_paises.setBounds(111, 245, 122, 24);
		add(cb_paises);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Pais (*)");
		lblNewLabel_1_1_1.setBounds(23, 250, 68, 14);
		add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Ciudad (*)");
		lblNewLabel_1_1_1_1.setBounds(23, 285, 68, 14);
		add(lblNewLabel_1_1_1_1);
		
		JComboBox cb_ciudades = new JComboBox();
		cb_ciudades.setBounds(111, 280, 122, 24);
		add(cb_ciudades);
		
		JComboBox cb_provincias = new JComboBox();
		cb_provincias.setBounds(495, 245, 122, 24);
		add(cb_provincias);
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("Provincia (*)");
		lblNewLabel_1_1_1_2.setBounds(346, 250, 68, 14);
		add(lblNewLabel_1_1_1_2);
		
		JLabel lblNewLabel_1_1_1_2_1 = new JLabel("Tipo de Documento (*)");
		lblNewLabel_1_1_1_2_1.setBounds(346, 108, 139, 14);
		add(lblNewLabel_1_1_1_2_1);
		
		JComboBox cb_tipos_documento = new JComboBox();
		cb_tipos_documento.setBounds(495, 101, 122, 24);
		add(cb_tipos_documento);
		
		tf_numero_documento = new JTextField();
		tf_numero_documento.setColumns(10);
		tf_numero_documento.setBounds(495, 139, 122, 20);
		add(tf_numero_documento);
		
		JLabel lblNewLabel_1_2 = new JLabel("Numero de Documento (*)");
		lblNewLabel_1_2.setBounds(346, 142, 139, 14);
		add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_1_1_2_2 = new JLabel("Factura (*)");
		lblNewLabel_1_1_1_2_2.setBounds(346, 173, 68, 14);
		add(lblNewLabel_1_1_1_2_2);
		
		JComboBox cb_factura = new JComboBox();
		cb_factura.setBounds(495, 170, 122, 24);
		add(cb_factura);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Calle (*)");
		lblNewLabel_1_1_2.setBounds(23, 336, 84, 14);
		add(lblNewLabel_1_1_2);
		
		tf_calle = new JTextField();
		tf_calle.setColumns(10);
		tf_calle.setBounds(111, 333, 122, 20);
		add(tf_calle);
		
		JLabel lblNewLabel_1_1_2_1 = new JLabel("Nacimiento (*)");
		lblNewLabel_1_1_2_1.setBounds(23, 201, 84, 14);
		add(lblNewLabel_1_1_2_1);
		
		tf_nacimiento = new JTextField();
		tf_nacimiento.setColumns(10);
		tf_nacimiento.setBounds(111, 198, 122, 20);
		add(tf_nacimiento);
		
		JLabel lblNewLabel_1_1_2_2 = new JLabel("Numero (*)");
		lblNewLabel_1_1_2_2.setBounds(23, 381, 84, 14);
		add(lblNewLabel_1_1_2_2);
		
		tf_numero_direccion = new JTextField();
		tf_numero_direccion.setColumns(10);
		tf_numero_direccion.setBounds(111, 378, 122, 20);
		add(tf_numero_direccion);
		
		JLabel lblNewLabel_1_1_2_2_1 = new JLabel("Ocupacion (*)");
		lblNewLabel_1_1_2_2_1.setBounds(23, 412, 84, 14);
		add(lblNewLabel_1_1_2_2_1);
		
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
		
		JLabel lblNewLabel_1_1_2_3 = new JLabel("Codigo Postal (*)");
		lblNewLabel_1_1_2_3.setBounds(346, 285, 84, 14);
		add(lblNewLabel_1_1_2_3);
		
		tf_codigo_postal = new JTextField();
		tf_codigo_postal.setColumns(10);
		tf_codigo_postal.setBounds(495, 282, 122, 20);
		add(tf_codigo_postal);
		
		JLabel lblNewLabel_1_1_2_3_1 = new JLabel("Piso");
		lblNewLabel_1_1_2_3_1.setBounds(346, 336, 30, 14);
		add(lblNewLabel_1_1_2_3_1);
		
		tf_piso_direccion = new JTextField();
		tf_piso_direccion.setColumns(10);
		tf_piso_direccion.setBounds(386, 333, 44, 20);
		add(tf_piso_direccion);
		
		JLabel lblNewLabel_1_1_2_3_1_1 = new JLabel("Departamento");
		lblNewLabel_1_1_2_3_1_1.setBounds(457, 336, 71, 14);
		add(lblNewLabel_1_1_2_3_1_1);
		
		tf_departamento_direccion = new JTextField();
		tf_departamento_direccion.setColumns(10);
		tf_departamento_direccion.setBounds(538, 333, 79, 20);
		add(tf_departamento_direccion);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Telefono (*)");
		lblNewLabel_1_2_1.setBounds(346, 378, 139, 14);
		add(lblNewLabel_1_2_1);
		
		tf_telefono = new JTextField();
		tf_telefono.setColumns(10);
		tf_telefono.setBounds(457, 375, 160, 20);
		add(tf_telefono);
		
		JComboBox cb_nacionalidad = new JComboBox();
		cb_nacionalidad.setBounds(457, 412, 160, 24);
		add(cb_nacionalidad);
		
		JLabel lblNewLabel_1_1_1_2_3 = new JLabel("Nacionalidad (*)");
		lblNewLabel_1_1_1_2_3.setBounds(346, 417, 84, 14);
		add(lblNewLabel_1_1_1_2_3);
		
		p_encabezado = new Encabezado();
		p_encabezado.setBounds(0, 0, 640, 80);
		add(p_encabezado);
	}
}
