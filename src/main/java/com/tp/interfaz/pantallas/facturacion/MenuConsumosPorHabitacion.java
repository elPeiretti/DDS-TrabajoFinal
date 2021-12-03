package com.tp.interfaz.pantallas.facturacion;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;

import com.tp.interfaz.dto.FacturaDTO;
import com.tp.interfaz.dto.HabitacionDTO;
import com.tp.interfaz.dto.ItemFacturaDTO;
import com.tp.interfaz.dto.PasajeroDTO;
import com.tp.interfaz.dto.ResponsablePagoTerceroDTO;
import com.tp.interfaz.dto.ServicioDTO;
import com.tp.interfaz.pantallas.MenuPrincipal;
import com.tp.interfaz.pantallas.SeteableTab;
import com.tp.interfaz.pantallas.VentanaPrincipal;
import com.tp.interfaz.pantallas.misc.Encabezado;
import com.tp.interfaz.pantallas.misc.Mensaje;
import com.tp.interfaz.pantallas.misc.TabOrder;
import com.tp.interfaz.pantallas.misc.spinner.*;
import com.tp.logica.dominio.TipoFactura;
import com.tp.logica.excepciones.FacturaSinItemsException;
import com.tp.logica.gestores.GestorFacturas;
import com.tp.logica.gestores.GestorHabitaciones;
import com.tp.logica.gestores.GestorServicios;

public class MenuConsumosPorHabitacion extends JPanel implements SeteableTab{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2588722793200749158L;
	public static String titulo = "Facturar";
	public static int x_bound = 660;
	public static int y_bound = 500;

	private JFrame ventana_contenedora;
	private Encabezado encabezado;
	private JButton jb_siguiente;
	private JButton jb_cancelar;
	private ResultPaneServicios rp_servicios;
	private JLabel lbl_nom_resp;
	private JLabel lbl_subtotal_tag;
	private JLabel lbl_subtotal;
	private Double subtotal;
	private JLabel lbl_iva;
	private JLabel lbl_total;
	private Double total;
	private Double iva;
	private JLabel lbl_iva_tag;
	private JLabel lbl_total_tag;
	private ResponsablePagoTerceroDTO responsable;
	private PasajeroDTO responsable_pasajero;
	private HabitacionDTO habitacion;
	private MenuFacturar menu_facturar_previo;
	
	
	public MenuConsumosPorHabitacion(JFrame ventana_contenedora, Encabezado encabezado, HabitacionDTO hab, MenuFacturar contexto){
	
		setBackground(Color.WHITE);
		this.ventana_contenedora = ventana_contenedora;
		this.encabezado = encabezado;
		this.habitacion = hab;
		this.menu_facturar_previo = contexto;
		setLayout(null);
		
		JLabel lbl_nom_resp_tag = new JLabel("Nombre Responsable:");
		lbl_nom_resp_tag.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_nom_resp_tag.setBounds(10, 121, 130, 14);
		add(lbl_nom_resp_tag);
		
		encabezado.setSize(640, 110);
		encabezado.setLocation(0, 0);
		add(encabezado);
		
		jb_siguiente = new JButton("Siguiente");
		jb_siguiente.setBounds(450, 420, 100, 30);
		add(jb_siguiente);
		
		jb_cancelar = new JButton("Cancelar");
		jb_cancelar.setBounds(81, 420, 100, 30);
		add(jb_cancelar);
		
		rp_servicios = new ResultPaneServicios(this::llenarTabla);
		rp_servicios.setBounds(10, 156, 620, 184);
		add(rp_servicios);
		
		lbl_nom_resp = new JLabel("");
		lbl_nom_resp.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_nom_resp.setBounds(150, 121, 506, 14);
		add(lbl_nom_resp);
		
		lbl_subtotal_tag = new JLabel("");
		lbl_subtotal_tag.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_subtotal_tag.setBounds(419, 351, 104, 14);
		add(lbl_subtotal_tag);
		
		lbl_subtotal = new JLabel("");
		lbl_subtotal.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_subtotal.setBounds(533, 351, 98, 14);
		add(lbl_subtotal);
		
		lbl_iva = new JLabel("");
		lbl_iva.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_iva.setBounds(532, 371, 98, 14);
		add(lbl_iva);
		
		lbl_total = new JLabel("");
		lbl_total.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_total.setBounds(533, 395, 98, 14);
		add(lbl_total);
		
		lbl_iva_tag = new JLabel("");
		lbl_iva_tag.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_iva_tag.setBounds(419, 371, 104, 14);
		add(lbl_iva_tag);
		
		lbl_total_tag = new JLabel("Monto Total:");
		lbl_total_tag.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_total_tag.setBounds(419, 395, 104, 14);
		add(lbl_total_tag);
		
		agregarActionListeners();
		agregarTabOrder();
	}
	
	private void agregarTabOrder() {
		this.setFocusTraversalPolicy(new TabOrder(List.of(
				rp_servicios.getPrevBtn(),
				rp_servicios.getNextBtn(),
				jb_siguiente,
				jb_cancelar
				)));
		this.setFocusTraversalPolicyProvider(true);
	}

	public MenuConsumosPorHabitacion(JFrame ventana_contenedora, Encabezado encabezado, PasajeroDTO responsable_pasajero, HabitacionDTO hab, MenuFacturar contexto){
		this(ventana_contenedora,encabezado,hab,contexto);
		this.responsable_pasajero = responsable_pasajero;
		inicializarCampos();
		llenarTabla();
	}
	public MenuConsumosPorHabitacion(JFrame ventana_contenedora, Encabezado encabezado, ResponsablePagoTerceroDTO responsable, HabitacionDTO hab, MenuFacturar contexto){
		this(ventana_contenedora,encabezado,hab,contexto);
		this.responsable = responsable;
		inicializarCampos();
		llenarTabla();
	}

	private void inicializarCampos(){
		if(responsable == null){
			lbl_nom_resp.setText(responsable_pasajero.getApellido()+", "+responsable_pasajero.getNombres());
		}
		else{
			lbl_nom_resp.setText(responsable.getRazonSocial());
		}

		if (responsable != null || (responsable_pasajero!=null && responsable_pasajero.getPosicionIVA().getPosicion().equals("R.I."))){
			lbl_iva_tag.setText("IVA(21%):");
			lbl_subtotal_tag.setText("Subtotal:");
		}

		rp_servicios.agregarColumnas(List.of("Consumos","Precio Unitario","Unidades Consumidas","Unidades a Facturar"), List.of(0,1,2,3));
		rp_servicios.setCantPaginas((long) Math.ceil(GestorServicios.getCantServiciosNoFacturadosByHabitacion(habitacion)/(double)rp_servicios.getCantidadFilas()));
		rp_servicios.setRowObjects(GestorServicios.getServiciosNoFacturadosByHabitacion(habitacion));
		for (ServicioDTO s : rp_servicios.getRowObjects()){
			rp_servicios.getMapCantidadSeteada().put(s.getIdServicio(),s.getCantidad()-s.getCantidadPagada());
		}
		actualizarMontos();
	}

	private void llenarTabla(){

		rp_servicios.limpiarTabla();

		List<ServicioDTO> servicios = rp_servicios.getRowObjects();
		int inic = (rp_servicios.getPaginaActual()-1)* rp_servicios.getCantidadFilas();

		for(int i = inic; i<servicios.size() && i<inic+rp_servicios.getCantidadFilas(); i++){
			rp_servicios.agregarFila(servicios.get(i));
		}
		
		//si o si debe hacerse despues de agregar las filas :(
		DefaultTableCellRenderer render_centrado = new DefaultTableCellRenderer();
		render_centrado.setHorizontalAlignment(SwingConstants.CENTER);
		rp_servicios.getTable().getColumnModel().getColumn(1).setCellRenderer(render_centrado);
		rp_servicios.getTable().getColumnModel().getColumn(2).setCellRenderer(render_centrado);
		rp_servicios.getTable().getColumnModel().getColumn(3).setCellRenderer(new SpinnerCellRenderer());
		rp_servicios.getTable().getColumnModel().getColumn(3).setCellEditor(new SpinnerCellEditor());
		rp_servicios.getTable().setRowHeight(30);

	}

	@Override
	public void setDefaultTab() {
		rp_servicios.getPrevBtn().requestFocus();
	}

	public void actualizarMontos(){
		subtotal = 0d;
		rp_servicios.getRowObjects().stream()
									.map(s -> s.getPrecioUnitario()*rp_servicios.getMapCantidadSeteada().get(s.getIdServicio()))
									.forEach(val -> subtotal+=val);
		total = subtotal*1.21;
		iva = subtotal*0.21;

		lbl_total.setText(String.format("%.2f", total));
		if (responsable != null || (responsable_pasajero!=null && responsable_pasajero.getPosicionIVA().getPosicion().equals("R.I."))){
			lbl_subtotal.setText(String.format("%.2f", subtotal));
			lbl_iva.setText(String.format("%.2f", iva));
		}
	}

	public void agregarActionListeners(){
		rp_servicios.getTable().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt){
				int col = rp_servicios.getTable().getSelectedColumn();
				int row = rp_servicios.getTable().getSelectedRow();
				if(col!=3 || row == -1) return;

				int i = (rp_servicios.getPaginaActual()-1)* rp_servicios.getCantidadFilas();
				Integer value = (Integer) rp_servicios.getTable().getValueAt(row, 3);
				Integer max = rp_servicios.getTable().getJspinnersMaxList().get(row);
				if(value<0){
					value=0;
				}
				else if(value>max){
					value=max;
				}
				rp_servicios.getMapCantidadSeteada().put(rp_servicios.getRowObjects().get(i+row).getIdServicio(),value);

				actualizarMontos();
			}
		});

		jb_cancelar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int opt = Mensaje.mensajeConfirmacion("¿Está seguro que desea cancelar la creación de la factura?");
				if(opt == 1){
					menu_facturar_previo.add(encabezado);
					((VentanaPrincipal) ventana_contenedora).cambiarPanel(menu_facturar_previo,
															 MenuFacturar.x_bound, MenuFacturar.y_bound, MenuFacturar.titulo);
				}
			}
			
		});

		jb_siguiente.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
				FacturaDTO factura = crearFacturaDTO();
				
				try {
					GestorFacturas.generarFactura(factura);
				} catch (FacturaSinItemsException exc) {
					Mensaje.mensajeInformacion("La factura debe contener al menos un item.");
					return;
				}

				Mensaje.mensajeInformacion("Se ha creado la factura de la habitación "+habitacion.getNumero()+" exitosamente.");

				if(GestorServicios.getCantServiciosNoFacturadosByHabitacion(habitacion)==0){
					GestorHabitaciones.liberarHabitacion(habitacion);
					Mensaje.mensajeInformacion("Se ha completado la facturación de la habitación "+habitacion.getNumero()+" exitosamente.");
					((VentanaPrincipal) ventana_contenedora).cambiarPanel(new MenuPrincipal(ventana_contenedora, encabezado),
														MenuPrincipal.x_bound, MenuPrincipal.y_bound, MenuPrincipal.titulo);
				}
				else{
					menu_facturar_previo.add(encabezado);
					((VentanaPrincipal) ventana_contenedora).cambiarPanel(menu_facturar_previo,MenuFacturar.x_bound,
																		MenuFacturar.y_bound,MenuFacturar.titulo);
				}
														
			}

			private FacturaDTO crearFacturaDTO(){
				List<ItemFacturaDTO> items_factura = new ArrayList<ItemFacturaDTO>();
				List<ServicioDTO> servicios = rp_servicios.getRowObjects();
				Map<Integer,Integer> cantidades = rp_servicios.getMapCantidadSeteada();
				
				for(int i=0; i<servicios.size(); i++){
					Integer cantidadAFacturar = cantidades.get(servicios.get(i).getIdServicio());
					if (cantidadAFacturar==0)
						continue;

					items_factura.add( new ItemFacturaDTO(cantidadAFacturar,servicios.get(i)) );
				}

				TipoFactura tipo_factura = (responsable != null || (responsable_pasajero != null && responsable_pasajero.getPosicionIVA().getPosicion().equals("R.I.")))?
											 TipoFactura.A:TipoFactura.B;
				

				return new FacturaDTO(tipo_factura,total,LocalDate.now(),iva,items_factura,responsable_pasajero,responsable);
			}
		});
	}
}
