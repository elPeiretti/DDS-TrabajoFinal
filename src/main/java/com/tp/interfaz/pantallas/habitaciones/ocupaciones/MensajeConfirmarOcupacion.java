package com.tp.interfaz.pantallas.habitaciones.ocupaciones;

import javax.swing.*;

import com.tp.interfaz.dto.BusqPasajeroDTO;
import com.tp.interfaz.dto.OcupacionDTO;
import com.tp.interfaz.dto.PasajeroDTO;
import com.tp.interfaz.pantallas.MenuPrincipal;
import com.tp.interfaz.pantallas.SeteableTab;
import com.tp.interfaz.pantallas.VentanaPrincipal;
import com.tp.interfaz.pantallas.habitaciones.MenuEstadoHabitaciones;
import com.tp.interfaz.pantallas.misc.*;
import com.tp.logica.excepciones.NuevaOcupacionException;
import com.tp.logica.gestores.GestorHabitaciones;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class MensajeConfirmarOcupacion extends JPanel implements SeteableTab {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2288976139522990459L;
	public static String titulo = "Confirmar Ocupacion";
	public static int x_bound = 660;
	public static int y_bound = 500;
	private Map<Integer,BusqPasajeroDTO.columnaOrden> indice_columnas;
	private JFrame ventana_contenedora;
	private Encabezado encabezado;
	private OcupacionDTO nuevaOcupacion;
	private JLabel lbl_habitacion;
	private JLabel lbl_nombre_responsable;
	private JLabel lbl_acompaniantes;
	private JLabel lbl_habitacion_datos;
	private JLabel lbl_nombre_responsable_datos;
	private JButton jb_continuar;
	private ResultPane<PasajeroDTO> rp_acompaniantes;
	private MenuBuscarAcompaniantes estadoAnterior;
	
	public MensajeConfirmarOcupacion(JFrame ventana_contenedora, Encabezado encabezado, OcupacionDTO nuevaOcupacion, MenuBuscarAcompaniantes estadoAnterior) {
		setBackground(Color.WHITE);
		this.ventana_contenedora = ventana_contenedora;
		this.nuevaOcupacion = nuevaOcupacion;
		this.encabezado = encabezado;
		this.estadoAnterior = estadoAnterior;
		setLayout(null);
		
		encabezado.setBounds(0, 0, 640, 110);
		add(encabezado);
		
		rp_acompaniantes = new ResultPane<PasajeroDTO>();
		rp_acompaniantes.setBounds(10, 196, 620, 180);
		add(rp_acompaniantes);
		
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
		jb_continuar.setBounds(541, 400, 100, 30);
		add(jb_continuar);
		
		rp_acompaniantes.getNextBtn().setVisible(false);
		rp_acompaniantes.getPrevBtn().setVisible(false);
		rp_acompaniantes.getPageNumbers().setVisible(false);
		
		this.inicializarCampos();
		this.llenarTabla();
		this.agregarActionListeners();
	}
	

	private void agregarActionListeners() {
		jb_continuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int opcion = Mensaje.mensajeConfirmacionOcupacion();
				
				if(opcion == 0) {
					try {
						GestorHabitaciones.ocuparHabitacion(nuevaOcupacion);
					}catch(NuevaOcupacionException x) {
						Mensaje.mensajeError(new String[]{"Hubo un error al registrar la ocupación"});
						return;
					}
					((VentanaPrincipal)ventana_contenedora).cambiarPanel(new MenuPrincipal(ventana_contenedora,encabezado),
							MenuPrincipal.x_bound,MenuPrincipal.y_bound,MenuPrincipal.titulo);
				} else if(opcion == 1) {
					try {
						GestorHabitaciones.ocuparHabitacion(nuevaOcupacion);
					}catch(NuevaOcupacionException x) {
						Mensaje.mensajeError(new String[]{"Hubo un error al registrar la ocupación"});
						return;
					}
					((VentanaPrincipal)ventana_contenedora).cambiarPanel(new MenuEstadoHabitaciones(ventana_contenedora,encabezado),
							MenuEstadoHabitaciones.x_bound,MenuEstadoHabitaciones.y_bound,MenuEstadoHabitaciones.titulo);
				} else if(opcion == 2) {
					estadoAnterior.add(encabezado);
					((VentanaPrincipal)ventana_contenedora).cambiarPanel(estadoAnterior,
							MenuBuscarAcompaniantes.x_bound,MenuBuscarAcompaniantes.y_bound,MenuBuscarAcompaniantes.titulo);
				}
				
				
			}
		});
	}


	private void llenarTabla() {
		rp_acompaniantes.getContenido().setRowCount(0);
		rp_acompaniantes.getRowObjects().clear();
		
		//rp_acompaniantes.setCantPaginas((long) Math.ceil(GestorPasajeros.getCountPasajerosBy(criterios_actuales, nuevaOcupacion.getResponsable())/8.0));
		//List<PasajeroDTO> lp = GestorPasajeros.getPasajerosBy(criterios_actuales, (rp_pasajeros_busqueda.getPaginaActual()-1)*8, 8, nuevaOcupacion.getResponsable());
		
		for(PasajeroDTO p : nuevaOcupacion.getAcompaniantes()) {
			Vector<Object> v = p.asVector();
			rp_acompaniantes.getContenido().addRow(v);
			rp_acompaniantes.getRowObjects().add(p);
		}
	}
	
	private void inicializarCampos() {
		lbl_habitacion_datos.setText(nuevaOcupacion.getHabitacion().getNumero() + " - " + nuevaOcupacion.getHabitacion().getTipoHabitacion());
		lbl_nombre_responsable_datos.setText(nuevaOcupacion.getResponsable().getApellido() + ", " + nuevaOcupacion.getResponsable().getNombres());
		
		rp_acompaniantes.agregarColumnas(List.of("Apellido","Nombres","Tipo Documento","Número de Documento"),null);
		indice_columnas = new HashMap<Integer,BusqPasajeroDTO.columnaOrden>();
		indice_columnas.put(0, BusqPasajeroDTO.columnaOrden.APELLIDO);//la clave debe coincidir con el orden en rp_pasajeros
		indice_columnas.put(1, BusqPasajeroDTO.columnaOrden.NOMBRES);
		indice_columnas.put(2, BusqPasajeroDTO.columnaOrden.TIPODOC);
		indice_columnas.put(3, BusqPasajeroDTO.columnaOrden.NRODOC);
			
	}



	@Override
	public void setDefaultTab() {
		jb_continuar.requestFocus();
	}
}
