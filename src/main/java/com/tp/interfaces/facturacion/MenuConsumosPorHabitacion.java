package com.tp.interfaces.facturacion;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import com.tp.dto.HabitacionDTO;
import com.tp.dto.PasajeroDTO;
import com.tp.dto.ResponsablePagoTerceroDTO;
import com.tp.dto.ServicioDTO;
import com.tp.interfaces.misc.Encabezado;
import com.tp.interfaces.misc.spinner.*;
import com.tp.interfaces.SeteableTab;

public class MenuConsumosPorHabitacion extends JPanel implements SeteableTab{

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
	private JLabel lbl_iva;
	private JLabel lbl_total;
	private JLabel lbl_iva_tag;
	private JLabel lbl_total_tag;
	private ResponsablePagoTerceroDTO responsable;
	private PasajeroDTO responsable_pasajero;
	private HabitacionDTO habitacion;
	
	
	public MenuConsumosPorHabitacion(JFrame ventana_contenedora, Encabezado encabezado, HabitacionDTO hab){
	
		setBackground(Color.WHITE);
		this.ventana_contenedora = ventana_contenedora;
		this.encabezado = encabezado;
		this.habitacion = hab;
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
		
		rp_servicios = new ResultPaneServicios();
		rp_servicios.setBounds(10, 156, 620, 184);
		add(rp_servicios);
		
		lbl_nom_resp = new JLabel("");
		lbl_nom_resp.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_nom_resp.setBounds(124, 121, 506, 14);
		add(lbl_nom_resp);
		
		lbl_subtotal_tag = new JLabel("Subtotal:");
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
		
		lbl_iva_tag = new JLabel("IVA(21%)");
		lbl_iva_tag.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_iva_tag.setBounds(419, 371, 104, 14);
		add(lbl_iva_tag);
		
		lbl_total_tag = new JLabel("Monto Total:");
		lbl_total_tag.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_total_tag.setBounds(419, 395, 104, 14);
		add(lbl_total_tag);
		
	}

	public MenuConsumosPorHabitacion(JFrame ventana_contenedora, Encabezado encabezado, PasajeroDTO responsable_pasajero, HabitacionDTO hab){
		this(ventana_contenedora,encabezado,hab);
		this.responsable_pasajero = responsable_pasajero;
		inicializarCampos();
		rp_servicios.agregarColumnas(List.of("Consumos","Precio Unitario","Unidades Consumidas","Unidades a Facturar"), List.of(0,1,2,3));
		llenarTabla();
	}
	public MenuConsumosPorHabitacion(JFrame ventana_contenedora, Encabezado encabezado, ResponsablePagoTerceroDTO responsable, HabitacionDTO hab){
		this(ventana_contenedora,encabezado,hab);
		this.responsable = responsable;
		inicializarCampos();
		rp_servicios.agregarColumnas(List.of("Consumos","Precio Unitario","Unidades Consumidas","Unidades a Facturar"), List.of(0,1,2,3));
		llenarTabla();
	}

	private void inicializarCampos(){
		if(responsable == null){
			lbl_nom_resp.setText(responsable_pasajero.getApellido()+", "+responsable_pasajero.getNombres());
		}
		else{
			lbl_nom_resp.setText(responsable.getRazonSocial());
		}
	}

	private void llenarTabla(){
		//List<ServicioDTO> servicios = GestorHabitaciones.getServiciosNoFacturadosByHabitacion(habitacion);

		rp_servicios.agregarFila("SANDIA",30.02,300);
		rp_servicios.agregarFila("MILANESA",120.0,6);
		rp_servicios.agregarFila("COCOS",10.50,20);

		/*for(ServicioDTO s: servicios){
			rp_servicios.agregarFila(s);
		}*/
		
		//si o si debe hacerse despues de agregar las filas :(
		rp_servicios.getTable().getColumnModel().getColumn(3).setCellRenderer(new SpinnerCellRenderer());
		rp_servicios.getTable().getColumnModel().getColumn(3).setCellEditor(new SpinnerCellEditor());
		rp_servicios.getTable().setRowHeight(30);

	}

	@Override
	public void setDefaultTab() {
		jb_siguiente.requestFocus();
	}
}
