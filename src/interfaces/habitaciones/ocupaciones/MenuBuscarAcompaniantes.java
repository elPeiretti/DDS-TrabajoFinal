package interfaces.habitaciones.ocupaciones;

import javax.swing.*;
import interfaces.misc.*;
import java.awt.Color;

public class MenuBuscarAcompaniantes extends JPanel {

	private JFrame ventana_contenedora;
	private JPanel encabezado;
	private JTextField jtf_numero_documento;
	private JTextField jtf_nombres;
	private JTextField jtf_apellido;
	private JComboBox jcb_tipo_documento;
	private JLabel lbl_numero_documento;
	private JLabel lbl_apellido;
	private JLabel lbl_tipo_documento;
	private JPanel rp_pasajeros_busqueda;
	private ResultPane rp_pasajeros_agregados;
	private JButton jb_buscar;
	private JButton jb_siguiente;
	private JButton jb_cancelar;
	
	// setear ventana a 640x700
	public MenuBuscarAcompaniantes(JFrame ventana_contenedora) {
		setBackground(Color.WHITE);
		this.ventana_contenedora = ventana_contenedora;
		setLayout(null);
		
		encabezado = new Encabezado();
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
		
		jcb_tipo_documento = new JComboBox();
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
		
		rp_pasajeros_busqueda = new ResultPane();
		rp_pasajeros_busqueda.setBounds(10, 244, 620, 180);
		add(rp_pasajeros_busqueda);
		
		rp_pasajeros_agregados = new ResultPane();
		rp_pasajeros_agregados.setBounds(10, 435, 620, 180);
		add(rp_pasajeros_agregados);
		
		jb_siguiente = new JButton("Siguiente");
		jb_siguiente.setBounds(452, 636, 100, 30);
		add(jb_siguiente);
		
		jb_cancelar = new JButton("Cancelar");
		jb_cancelar.setBounds(83, 640, 100, 30);
		add(jb_cancelar);
	}	
}
