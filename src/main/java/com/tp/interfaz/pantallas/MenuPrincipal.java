package com.tp.interfaz.pantallas;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Color;
import java.awt.Image;

import javax.swing.border.LineBorder;

import com.tp.interfaz.pantallas.facturacion.MenuFacturar;
import com.tp.interfaz.pantallas.habitaciones.MenuEstadoHabitaciones;
import com.tp.interfaz.pantallas.ingresos.MenuListarIngresos;
import com.tp.interfaz.pantallas.misc.Encabezado;
import com.tp.interfaz.pantallas.misc.EnterActionAssigner;
import com.tp.interfaz.pantallas.misc.Mensaje;
import com.tp.interfaz.pantallas.misc.TabOrder;
import com.tp.interfaz.pantallas.pagos.MenuRegistrarPago;
import com.tp.interfaz.pantallas.pasajeros.MenuBusquedaPasajero;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuPrincipal extends JPanel implements SeteableTab{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3111229752677918492L;
	public static String titulo = "Menú Principal";
	public static int x_bound = 660;
	public static int y_bound = 500;

	private JFrame ventana_contenedora;
	private BufferedImage logo;
	private JLabel lbl_logo;
	private JButton jb_gestionar_pasajero;
	private JButton jb_facturar;
	private JButton jb_ingresar_pago;
	private JButton jb_ocupar_habitacion;
	private JButton jb_reservar_habitacion;
	private JButton jb_gestionar_responsable_pagos;
	private JButton jb_listar_cheques;
	private JButton jb_listar_ingresos;
	private JButton jb_ingresar_nota_credito;
	private JButton jb_cancelar_reserva;
	private JButton jb_salir;
	private Encabezado encabezado;
	
	public MenuPrincipal(JFrame ventana_contenedora, Encabezado encabezado) {
		setBackground(Color.WHITE);
		this.ventana_contenedora = ventana_contenedora;
		this.encabezado = encabezado;
		setLayout(null);
		
		
		jb_gestionar_pasajero = new JButton("Gestionar Pasajero");
		jb_gestionar_pasajero.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jb_gestionar_pasajero.setBackground(new Color(36, 130, 109));
		jb_gestionar_pasajero.setBounds(46, 59, 150, 40);
		add(jb_gestionar_pasajero);
		
		jb_reservar_habitacion = new JButton("Reservar Habitaci\u00F3n");
		jb_reservar_habitacion.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jb_reservar_habitacion.setBackground(new Color(36, 130, 109));
		jb_reservar_habitacion.setBounds(46, 110, 150, 40);
		add(jb_reservar_habitacion);
		
		jb_facturar = new JButton("Facturar");
		jb_facturar.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jb_facturar.setBackground(new Color(36, 130, 109));
		jb_facturar.setBounds(46, 212, 150, 40);
		add(jb_facturar);
		
		jb_ocupar_habitacion = new JButton("Ocupar Habitaci\u00F3n");
		jb_ocupar_habitacion.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jb_ocupar_habitacion.setBackground(new Color(36, 130, 109));
		jb_ocupar_habitacion.setBounds(46, 161, 150, 40);
		add(jb_ocupar_habitacion);
		
		jb_ingresar_pago = new JButton("Ingresar Pago");
		jb_ingresar_pago.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jb_ingresar_pago.setBackground(new Color(36, 130, 109));
		jb_ingresar_pago.setBounds(46, 260, 150, 40);
		add(jb_ingresar_pago);
		
		jb_gestionar_responsable_pagos = new JButton("<html><center>Gestionar<br> Responsable de Pagos</center></html>");
		jb_gestionar_responsable_pagos.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jb_gestionar_responsable_pagos.setBackground(new Color(36, 130, 109));
		jb_gestionar_responsable_pagos.setBounds(440, 59, 150, 40);
		add(jb_gestionar_responsable_pagos);
		
		jb_listar_cheques = new JButton("Listar Cheques");
		jb_listar_cheques.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jb_listar_cheques.setBackground(new Color(36, 130, 109));
		jb_listar_cheques.setBounds(440, 212, 150, 40);
		add(jb_listar_cheques);
		
		jb_listar_ingresos = new JButton("Listar Ingresos");
		jb_listar_ingresos.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jb_listar_ingresos.setBackground(new Color(36, 130, 109));
		jb_listar_ingresos.setBounds(440, 260, 150, 40);
		add(jb_listar_ingresos);
		
		jb_ingresar_nota_credito = new JButton("Ingresar Nota de Cr\u00E9dito");
		jb_ingresar_nota_credito.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jb_ingresar_nota_credito.setBackground(new Color(36, 130, 109));
		jb_ingresar_nota_credito.setBounds(440, 161, 150, 40);
		add(jb_ingresar_nota_credito);
		
		jb_cancelar_reserva = new JButton("Cancelar Reserva");
		jb_cancelar_reserva.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jb_cancelar_reserva.setBackground(new Color(36, 130, 109));
		jb_cancelar_reserva.setBounds(440, 110, 150, 40);
		add(jb_cancelar_reserva);
		
		jb_salir = new JButton("Salir");
		jb_salir.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jb_salir.setBackground(new Color(255, 228, 225));
		jb_salir.setBounds(245, 379, 150, 40);
		add(jb_salir);
		
		lbl_logo = new JLabel();
		lbl_logo.setSize(250, 300);
		lbl_logo.setLocation(194, 43);
		add(lbl_logo);
		
		try {
			logo = ImageIO.read(new File("./src/main/resources/logo.png"));
			Image dlogo = logo.getScaledInstance(lbl_logo.getWidth(),lbl_logo.getHeight(),Image.SCALE_SMOOTH);
			lbl_logo.setIcon(new ImageIcon(dlogo));
			
		} catch (IOException e) {
			Mensaje.mensajeError(new String[]{"Fallo en la lectura de la imagen del logo."});
			e.printStackTrace();
		}
		
		EnterActionAssigner.setEnterAction(List.of(jb_gestionar_pasajero, jb_reservar_habitacion, jb_ocupar_habitacion,
											jb_facturar, jb_ingresar_pago, jb_gestionar_responsable_pagos, jb_cancelar_reserva,
											jb_ingresar_nota_credito, jb_listar_cheques, jb_listar_ingresos, jb_salir));

		this.agregarActionListeners();
		this.agregarTabOrder();
	}
	
	private void agregarTabOrder() {
		this.setFocusTraversalPolicy(new TabOrder(List.of(
				jb_gestionar_pasajero,
				jb_reservar_habitacion,
				jb_ocupar_habitacion,
				jb_facturar,
				jb_ingresar_pago,
				jb_gestionar_responsable_pagos,
				jb_cancelar_reserva,
				jb_ingresar_nota_credito,
				jb_listar_cheques,
				jb_listar_ingresos,
				jb_salir
				)));
		this.setFocusTraversalPolicyProvider(true);
	}
	
	private void agregarActionListeners() {
		
		jb_gestionar_pasajero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((VentanaPrincipal)ventana_contenedora).cambiarPanel(new MenuBusquedaPasajero(ventana_contenedora,encabezado),
																	MenuBusquedaPasajero.x_bound,MenuBusquedaPasajero.y_bound,MenuBusquedaPasajero.titulo);
			}
		});
		jb_reservar_habitacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((VentanaPrincipal)ventana_contenedora).cambiarPanel(new MenuEstadoHabitaciones(ventana_contenedora,encabezado),
																	MenuEstadoHabitaciones.x_bound,MenuEstadoHabitaciones.y_bound,MenuEstadoHabitaciones.titulo);
			}
		});
		jb_ocupar_habitacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((VentanaPrincipal)ventana_contenedora).cambiarPanel(new MenuEstadoHabitaciones(ventana_contenedora,encabezado),
																	MenuEstadoHabitaciones.x_bound,MenuEstadoHabitaciones.y_bound,MenuEstadoHabitaciones.titulo);
			}
		});
		jb_facturar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((VentanaPrincipal)ventana_contenedora).cambiarPanel(new MenuFacturar(ventana_contenedora,encabezado),
																	MenuFacturar.x_bound,MenuFacturar.y_bound,MenuFacturar.titulo);
			}
		});
		jb_ingresar_pago.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((VentanaPrincipal)ventana_contenedora).cambiarPanel(new MenuRegistrarPago(ventana_contenedora,encabezado),
																	MenuRegistrarPago.x_bound,MenuRegistrarPago.y_bound,MenuRegistrarPago.titulo);
			}
		});
		jb_listar_ingresos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((VentanaPrincipal)ventana_contenedora).cambiarPanel(new MenuListarIngresos(ventana_contenedora,encabezado),
																	MenuListarIngresos.x_bound,MenuListarIngresos.y_bound,MenuListarIngresos.titulo);
			}
		});
		jb_salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((VentanaPrincipal)ventana_contenedora).cambiarPanel(new MenuAutenticacion(ventana_contenedora,encabezado),
																	MenuAutenticacion.x_bound,MenuAutenticacion.y_bound,MenuAutenticacion.titulo);
			}
		});
	}

	@Override
	public void setDefaultTab() {
		jb_gestionar_pasajero.requestFocus();
	}
	
}
