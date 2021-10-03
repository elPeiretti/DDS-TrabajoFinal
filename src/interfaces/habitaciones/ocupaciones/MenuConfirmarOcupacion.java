package interfaces.habitaciones.ocupaciones;

import javax.swing.*;
import interfaces.misc.*;
import java.awt.Color;

public class MenuConfirmarOcupacion extends JPanel {

	private JFrame ventana_contenedora;
	private JPanel encabezado;
	private JLabel lbl_habitacion;
	private JLabel lbl_nombre_responsable;
	private JLabel lbl_acompaniantes;
	private JLabel lbl_habitacion_datos;
	private JLabel lbl_nombre_responsable_datos;
	private JButton jb_continuar;
	
	public MenuConfirmarOcupacion(JFrame ventana_contenedora) {
		setBackground(Color.WHITE);
		this.ventana_contenedora = ventana_contenedora;
		setLayout(null);
		
		encabezado = new Encabezado();
		encabezado.setBounds(0, 0, 640, 110);
		add(encabezado);
		
		ResultPane resultPane = new ResultPane();
		resultPane.setBounds(10, 196, 620, 180);
		add(resultPane);
		
		lbl_habitacion = new JLabel("Habitaci\u00F3n a ocupar:");
		lbl_habitacion.setBounds(42, 121, 150, 14);
		add(lbl_habitacion);
		
		lbl_nombre_responsable = new JLabel("Nombre del Responsable:");
		lbl_nombre_responsable.setBounds(42, 146, 150, 14);
		add(lbl_nombre_responsable);
		
		lbl_acompaniantes = new JLabel("Acompa\u00F1antes:");
		lbl_acompaniantes.setBounds(42, 171, 150, 14);
		add(lbl_acompaniantes);
		
		lbl_habitacion_datos = new JLabel("<Nro_Hab> - <Tipo_Hab>");
		lbl_habitacion_datos.setBounds(202, 121, 250, 14);
		add(lbl_habitacion_datos);
		
		lbl_nombre_responsable_datos = new JLabel("<Nombre Pasajero Seleccionado>");
		lbl_nombre_responsable_datos.setBounds(202, 146, 250, 14);
		add(lbl_nombre_responsable_datos);
		
		jb_continuar = new JButton("Continuar");
		jb_continuar.setBounds(541, 400, 89, 30);
		add(jb_continuar);
	}
}
