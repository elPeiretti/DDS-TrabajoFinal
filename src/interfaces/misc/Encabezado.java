package interfaces.misc;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Color;

public class Encabezado extends JPanel {

	private BufferedImage logo;
	private JLabel lbl_logo;
	private JLabel lbl_conserje;
	
	public Encabezado() {
		setBackground(Color.WHITE);
		setLayout(null);
		
		lbl_conserje = new JLabel("Conserje:");
		lbl_conserje.setBounds(486, 24, 130, 14);
		add(lbl_conserje);
		lbl_logo = new JLabel();
		lbl_logo.setSize(100, 100);
		lbl_logo.setLocation(10, 11);
		add(lbl_logo);
		
		try {
			logo = ImageIO.read(new File("./src/logo.png"));
			Image dlogo = logo.getScaledInstance(lbl_logo.getWidth(),lbl_logo.getHeight(),Image.SCALE_SMOOTH);
			lbl_logo.setIcon(new ImageIcon(dlogo));
			
			JLabel lbl_fecha = new JLabel("01/01/1000");
			lbl_fecha.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl_fecha.setBounds(520, 49, 99, 14);
			add(lbl_fecha);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
