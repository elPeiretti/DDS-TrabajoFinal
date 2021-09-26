package interfaces;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import interfaces.misc.JTextFieldLimit;

import java.awt.Color;
import java.awt.Image;

public class MenuAutenticacion extends JPanel {
	
	private JFrame ventana_contenedora;
	private JTextField jtf_codigo_conserje;
	private JPasswordField jpf_contrasena;
	private JButton jb_iniciar_sesion;
	private JLabel lbl_contrasena;
	private JButton jb_salir;
	private BufferedImage logo;
	private JLabel lbl_logo;
	
	public MenuAutenticacion(JFrame ventana_contenedora) {
		setBackground(Color.WHITE);
		this.ventana_contenedora = ventana_contenedora;
		setLayout(null);
		
		jtf_codigo_conserje = new JTextField();
		jtf_codigo_conserje.setBounds(259, 229, 130, 25);
		jtf_codigo_conserje.setDocument(new JTextFieldLimit(10));
		add(jtf_codigo_conserje);
		
		jpf_contrasena = new JPasswordField();
		jpf_contrasena.setBounds(259, 290, 130, 25);
		jpf_contrasena.setDocument(new JTextFieldLimit(20));
		add(jpf_contrasena);
		
		jb_iniciar_sesion = new JButton("Iniciar Sesi\u00F3n");
		jb_iniciar_sesion.setBackground(new Color(135, 206, 235));
		jb_iniciar_sesion.setBounds(259, 330, 130, 33);
		add(jb_iniciar_sesion);
		
		jb_salir = new JButton("Salir");
		jb_salir.setBackground(new Color(255, 228, 225));
		jb_salir.setBounds(259, 374, 130, 33);
		add(jb_salir);
		
		lbl_contrasena = new JLabel("Contrase\u00F1a:");
		lbl_contrasena.setBounds(259, 265, 130, 14);
		add(lbl_contrasena);
		
		JLabel lbl_codigo_conserje = new JLabel("C\u00F3digo de Conserje:");
		lbl_codigo_conserje.setBounds(259, 204, 130, 14);
		add(lbl_codigo_conserje);
		
		lbl_logo = new JLabel();
		lbl_logo.setSize(150, 150);
		lbl_logo.setLocation(246, 43);
		add(lbl_logo);
		
		try {
			logo = ImageIO.read(new File("./src/logo.png"));
			Image dlogo = logo.getScaledInstance(lbl_logo.getWidth(),lbl_logo.getHeight(),Image.SCALE_SMOOTH);
			lbl_logo.setIcon(new ImageIcon(dlogo));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
