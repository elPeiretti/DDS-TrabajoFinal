package interfaces;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import interfaces.facturacion.*;
import interfaces.habitaciones.*;
import interfaces.habitaciones.ocupaciones.*;
import interfaces.habitaciones.reservas.*;
import interfaces.ingresos.*;
import interfaces.pagos.*;
import interfaces.pasajeros.*;

public class VentanaPrincipal extends JFrame {
	
	public final static String M_PPAL = "Menú Principal";
	public final static String M_AUTH = "Iniciar Sesión";
	public final static String CONSUMOS_HABITACION = "Consumos por Habitacion";
	public final static String FACTURAR = "Menú Facturar";
	public final static String EST_HABITACIONES = "Estado Habitaciones";
	public final static String BUSCAR_ACOMP = "Búsqueda de Acompañantes";
	public final static String BUSCAR_RESP = "Selección del Responsable";
	public final static String CONFIRMAR_OCUPACION = "Confirmación de Ocupación";
	public final static String CONFIRMAR_RESERVA = "Confirmación de Reserva";
	public final static String LISTAR_INGRESOS = "Listar Ingresos";
	public final static String BUSCAR_FACTURA = "Búsqueda de Facturas";
	public final static String REG_PAGO = "Registrar Pago";
	public final static String ALTA_PASAJERO = "Alta de Pasajero";
	public final static String BUSCAR_PASAJERO = "Búsqueda de Pasajeros";
	public final static String MODIF_PASAJERO = "Modificar Pasajero";
	
	private MenuAutenticacion m_auth;
	private MenuPrincipal m_ppal;
	private MenuConsumosPorHabitacion m_consumos_hab;
	private MenuFacturar m_fact;
	private MenuEstadoHabitaciones m_estado_hab;
	private MenuBuscarAcompaniantes m_buscar_acomp;
	private MenuBuscarResponsable m_buscar_resp;
	private MenuConfirmarOcupacion m_confirmar_ocup;
	private MenuConfirmarReserva m_confirmar_reserva;
	private MenuListarIngresos m_listar_ingr;
	private MenuBusquedaFactura m_buscar_factura;
	private MenuRegistrarPago m_reg_pago;
	private MenuAltaPasajero m_alta_pasaj;
	private MenuBusquedaPasajero m_buscar_pasajero;
	private MenuModificarPasajero m_modif_pasajero;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new CardLayout(0, 0));
		setContentPane(contentPane);
		
		//inicializar menues
		m_auth = new MenuAutenticacion(this);
		m_ppal = new MenuPrincipal(this);
		m_consumos_hab = new MenuConsumosPorHabitacion(this);
		m_fact = new MenuFacturar(this);
		m_estado_hab = new MenuEstadoHabitaciones(this);
		m_buscar_acomp = new MenuBuscarAcompaniantes(this);
		m_buscar_resp = new MenuBuscarResponsable(this);
		m_confirmar_ocup = new MenuConfirmarOcupacion(this);
		m_confirmar_reserva = new MenuConfirmarReserva(this);
		m_listar_ingr = new MenuListarIngresos(this);
		m_buscar_factura = new MenuBusquedaFactura(this);
		m_reg_pago = new MenuRegistrarPago(this);
		m_alta_pasaj = new MenuAltaPasajero(this);
		m_buscar_pasajero = new MenuBusquedaPasajero(this);
		m_modif_pasajero = new MenuModificarPasajero(this);
		
		//inicializar contentPane principal
		this.agregarPaneles();
		
		//Primer menu que se muestra es el de inicio de sesion
		this.cambiarPanel(M_AUTH);
	}
	
	public void agregarPaneles() {
		contentPane.add(m_buscar_acomp);
		contentPane.add(m_auth);
		contentPane.add(m_alta_pasaj);
		contentPane.add(m_buscar_factura);
		contentPane.add(m_buscar_pasajero);
		contentPane.add(m_buscar_resp);
		contentPane.add(m_confirmar_ocup);
		contentPane.add(m_confirmar_reserva);
		contentPane.add(m_consumos_hab);
		contentPane.add(m_estado_hab);
		contentPane.add(m_fact);
		contentPane.add(m_listar_ingr);
		contentPane.add(m_modif_pasajero);
		contentPane.add(m_reg_pago);
		contentPane.add(m_ppal);
	}
	
	public void cambiarPanel(String panel) {
		CardLayout c = (CardLayout) contentPane.getLayout();

		c.show(contentPane,panel);
		this.setTitle(panel);
	}

}
