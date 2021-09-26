package test;
import interfaces.*;
import javax.swing.*;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame ventana = new JFrame();
		MenuPrincipal ma = new MenuPrincipal(ventana);
		ventana.setBounds(200,200,640,480);
		ventana.setVisible(true);
		
		ventana.add(ma);
	}

}
