package interfaces.pasajeros;

import javax.swing.*;

import interfaces.misc.*;
import java.awt.Color;

public class MenuBusquedaPasajero extends JPanel {

	private JFrame ventana_contenedora;
	private JTextField jtf_apellido;
	private JTextField jtf_nombres;
	private JTextField jtf_numero_documento;
	private JComboBox jcb_tipo_documento;
	private JLabel lbl_tipo_documento;
	private JLabel lbl_numero_documento;
	private JLabel lbl_nombres;
	private JLabel lbl_apellido;
	private JPanel encabezado;
	private JButton jb_buscar;
	private JButton jb_siguiente;
	private JButton jb_cancelar;
	private JPanel rp_pasajeros;
	
	
	public MenuBusquedaPasajero(JFrame ventana_contenedora) {
		setBackground(Color.WHITE);
		this.ventana_contenedora = ventana_contenedora;
		setLayout(null);
		
		lbl_apellido = new JLabel("Apellido:");
		lbl_apellido.setBounds(81, 119, 64, 14);
		add(lbl_apellido);
		
		lbl_nombres = new JLabel("Nombres:");
		lbl_nombres.setBounds(329, 119, 61, 14);
		add(lbl_nombres);
		
		lbl_tipo_documento = new JLabel("<html>Tipo de<br>Documento:</html>");
		lbl_tipo_documento.setBounds(81, 152, 130, 30);
		add(lbl_tipo_documento);
		
		lbl_numero_documento = new JLabel("<html>N\u00FAmero de<br>Documento:</html>");
		lbl_numero_documento.setBounds(329, 152, 100, 30);
		add(lbl_numero_documento);
		
		jtf_apellido = new JTextField();
		jtf_apellido.setBounds(155, 111, 140, 30);
		add(jtf_apellido);
		jtf_apellido.setDocument(new JTextFieldLimit(30));
		jtf_apellido.setColumns(30);
		
		jtf_nombres = new JTextField();
		jtf_nombres.setBounds(410, 111, 140, 30);
		add(jtf_nombres);
		jtf_nombres.setColumns(50);
		jtf_nombres.setDocument(new JTextFieldLimit(10));
		
		jtf_numero_documento = new JTextField();
		jtf_numero_documento.setBounds(410, 152, 140, 30);
		add(jtf_numero_documento);
		jtf_numero_documento.setColumns(15);
		jtf_numero_documento.setDocument(new JTextFieldLimit(15));
		
		jcb_tipo_documento = new JComboBox();
		jcb_tipo_documento.setBounds(180, 152, 115, 30);
		add(jcb_tipo_documento);
		
		encabezado = new Encabezado();
		encabezado.setSize(640, 110);
		encabezado.setLocation(0, 0);
		add(encabezado);
		
		jb_buscar = new JButton("Buscar");
		jb_buscar.setBounds(450, 193, 100, 30);
		add(jb_buscar);
		
		jb_siguiente = new JButton("Siguiente");
		jb_siguiente.setBounds(450, 420, 100, 30);
		add(jb_siguiente);
		
		jb_cancelar = new JButton("Cancelar");
		jb_cancelar.setBounds(81, 420, 100, 30);
		add(jb_cancelar);
		
		rp_pasajeros = new ResultPane();
		rp_pasajeros.setBounds(10, 230, 620, 180);
		add(rp_pasajeros);
	}
}
