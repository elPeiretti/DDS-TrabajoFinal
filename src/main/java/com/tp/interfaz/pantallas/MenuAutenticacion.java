package com.tp.interfaz.pantallas;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.util.List;
import javax.persistence.NoResultException;
import javax.swing.*;
import java.awt.event.*;

import com.tp.interfaz.dto.ConserjeDTO;
import com.tp.interfaz.pantallas.misc.*;
import com.tp.logica.gestores.GestorLogin;

import java.awt.*;

public class MenuAutenticacion extends JPanel implements SeteableTab{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8739154481499777098L;
	public static String titulo = "Autenticación";
	public static int x_bound = 660;
	public static int y_bound = 500;

	private JFrame ventana_contenedora;
	private JTextField jtf_codigo_conserje;
	private JPasswordField jpf_contrasena;
	private JButton jb_iniciar_sesion;
	private JLabel lbl_contrasena;
	private JButton jb_salir;
	private BufferedImage logo;
	private JLabel lbl_logo;
	private JLabel lbl_error_codigo;
	private JLabel lbl_error_contrasena;
	private Encabezado encabezado;
	
	public MenuAutenticacion(JFrame ventana_contenedora, Encabezado encabezado) {
		setBackground(Color.WHITE);
		this.ventana_contenedora = ventana_contenedora;
		this.encabezado = encabezado;
		setLayout(null);
		
		jtf_codigo_conserje = new JTextField();
		jtf_codigo_conserje.setBounds(259, 223, 130, 25);
		jtf_codigo_conserje.setDocument(new JTextFieldLimit(10));
		add(jtf_codigo_conserje);
		
		jpf_contrasena = new JPasswordField();
		jpf_contrasena.setBounds(259, 294, 130, 25);
		jpf_contrasena.setDocument(new JTextFieldLimit(20));
		add(jpf_contrasena);
		
		jb_iniciar_sesion = new JButton("Iniciar Sesi\u00F3n");
		jb_iniciar_sesion.setBackground(new Color(135, 206, 235));
		jb_iniciar_sesion.setBounds(259, 344, 130, 33);
		add(jb_iniciar_sesion);
		
		jb_salir = new JButton("Salir");
		jb_salir.setBackground(new Color(255, 228, 225));
		jb_salir.setBounds(259, 388, 130, 33);
		add(jb_salir);
		
		lbl_contrasena = new JLabel("<html>Contrase\u00F1a <font color='red'>(*)</font>:</html>");
		lbl_contrasena.setBounds(259, 269, 130, 14);
		add(lbl_contrasena);
		
		JLabel lbl_codigo_conserje = new JLabel("<html>C\u00F3digo de Conserje <font color='red'>(*)</font>:</html>");
		lbl_codigo_conserje.setBounds(259, 198, 160, 14);
		add(lbl_codigo_conserje);
		
		lbl_logo = new JLabel();
		lbl_logo.setSize(150, 150);
		lbl_logo.setLocation(248, 37);
		add(lbl_logo);
		
		try {
			logo = ImageIO.read(new File("./src/main/resources/logo.png"));
			Image dlogo = logo.getScaledInstance(lbl_logo.getWidth(),lbl_logo.getHeight(),Image.SCALE_SMOOTH);
			lbl_logo.setIcon(new ImageIcon(dlogo));
			
			lbl_error_codigo = new JLabel("");
			lbl_error_codigo.setForeground(Color.RED);
			lbl_error_codigo.setFont(new Font("Tahoma", Font.PLAIN, 9));
			lbl_error_codigo.setBounds(259, 252, 180, 10);
			add(lbl_error_codigo);
			
			lbl_error_contrasena = new JLabel("");
			lbl_error_contrasena.setForeground(Color.RED);
			lbl_error_contrasena.setFont(new Font("Tahoma", Font.PLAIN, 9));
			lbl_error_contrasena.setBounds(259, 323, 180, 10);
			add(lbl_error_contrasena);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		EnterActionAssigner.setEnterAction(List.of(jb_iniciar_sesion, jb_salir));
		this.agregarActionListeners();
		this.agregarTabOrder();
	}

	private void agregarTabOrder() {
		this.setFocusTraversalPolicy(new TabOrder(List.of(
				jtf_codigo_conserje, jpf_contrasena,
				jb_iniciar_sesion, jb_salir
				)));
		this.setFocusTraversalPolicyProvider(true);
	}
	
	private void agregarActionListeners() {
		
		jb_iniciar_sesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String cod = jtf_codigo_conserje.getText();
				boolean ok = true;
				if (cod.isBlank()){
					lbl_error_codigo.setText("Este campo no puede estar vacío.");
					ok = false;
				}
				else{
					lbl_error_codigo.setText("");
				}

				String p = new String(jpf_contrasena.getPassword());
				if (p.isBlank()){
					lbl_error_contrasena.setText("Este campo no puede estar vacío.");
					ok = false;
				}
				else{
					lbl_error_contrasena.setText("");
				}		

				if (!ok) return;

				try {
					// busqueda del nombre del conserje por su codigo
					ConserjeDTO conserje = GestorLogin.getUser(cod,p);
					encabezado.lbl_conserje.setText("Conserje: "+conserje.getNombre()+" "+conserje.getApellido());
					((VentanaPrincipal)ventana_contenedora).cambiarPanel(new MenuPrincipal(ventana_contenedora,encabezado),660,500,"Menú Principal");
				}
				catch(NoResultException exc){
					lbl_error_codigo.setText("El usuario o contraseña son incorrectos.");
					lbl_error_contrasena.setText("El usuario o contraseña son incorrectos.");
				}
			}
		});
		
		jb_salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
	}

	@Override
	public void setDefaultTab() {
		jtf_codigo_conserje.requestFocus();		
	}
	
}
