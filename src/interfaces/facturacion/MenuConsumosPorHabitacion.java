package interfaces.facturacion;

import java.awt.Color;

import javax.swing.*;

import interfaces.misc.Encabezado;
import interfaces.misc.ResultPane;

public class MenuConsumosPorHabitacion extends JPanel {

	private JFrame ventana_contenedora;
	private JPanel encabezado;
	private JButton jb_siguiente;
	private JButton jb_cancelar;
	private ResultPane rp_facturas;
	private JLabel lbl_nom_resp;
	
	public MenuConsumosPorHabitacion(JFrame ventana_contenedora) {
	
		setBackground(Color.WHITE);
		this.ventana_contenedora = ventana_contenedora;
		setLayout(null);
		
		JLabel lbl_nom_resp_tag = new JLabel("Nombre Responsable:");
		lbl_nom_resp_tag.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_nom_resp_tag.setBounds(10, 121, 110, 14);
		add(lbl_nom_resp_tag);
		
		encabezado = new Encabezado();
		encabezado.setSize(640, 110);
		encabezado.setLocation(0, 0);
		add(encabezado);
		
		jb_siguiente = new JButton("Siguiente");
		jb_siguiente.setBounds(450, 420, 100, 30);
		add(jb_siguiente);
		
		jb_cancelar = new JButton("Cancelar");
		jb_cancelar.setBounds(81, 420, 100, 30);
		add(jb_cancelar);
		
		rp_facturas = new ResultPane();
		rp_facturas.setBounds(10, 156, 620, 254);
		add(rp_facturas);
		
		lbl_nom_resp = new JLabel("<cargar nombre>");
		lbl_nom_resp.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_nom_resp.setBounds(124, 121, 506, 14);
		add(lbl_nom_resp);
	}
}
