package interfaces.habitaciones;

import javax.swing.*;
import interfaces.misc.*;

public class MenuEstadoHabitaciones extends JPanel {

	private JFrame ventana_contenedora;
	private JPanel encabezado;
	private JTable jtable_habitaciones;
	private JLabel lbl_fecha_desde;
	private JLabel lbl_fecha_hasta;
	private JButton jb_cancelar;
	private JButton btn_siguiente;
	private JTextField dp_desde;
	private JTextField dp_hasta;
	
	public MenuEstadoHabitaciones(JFrame ventana_contenedora) {
		this.ventana_contenedora = ventana_contenedora;
		setLayout(null);
		
		encabezado = new Encabezado();
		encabezado.setBounds(0, 0, 640, 110);
		add(encabezado);
		
		lbl_fecha_desde = new JLabel("Desde Fecha:");
		lbl_fecha_desde.setBounds(142, 144, 110, 14);
		add(lbl_fecha_desde);
		
		lbl_fecha_hasta = new JLabel("Hasta Fecha:");
		lbl_fecha_hasta.setBounds(346, 144, 110, 14);
		add(lbl_fecha_hasta);
		
		jb_cancelar = new JButton("Cancelar");
		jb_cancelar.setBounds(427, 419, 89, 30);
		add(jb_cancelar);
		
		btn_siguiente = new JButton("Siguiente");
		btn_siguiente.setBounds(526, 419, 89, 30);
		add(btn_siguiente);
		
		jtable_habitaciones = new JTable();
		jtable_habitaciones.setBounds(10, 195, 620, 200);
		add(jtable_habitaciones);
		
		dp_desde = new JTextField();
		dp_desde.setText("DATE PICKER");
		dp_desde.setBounds(231, 141, 86, 20);
		add(dp_desde);
		dp_desde.setColumns(10);
		
		dp_hasta = new JTextField();
		dp_hasta.setText("DATE PICKER");
		dp_hasta.setColumns(10);
		dp_hasta.setBounds(430, 141, 86, 20);
		add(dp_hasta);
	}
}
