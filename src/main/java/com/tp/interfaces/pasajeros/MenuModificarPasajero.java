package com.tp.interfaces.pasajeros;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.stream.Collectors;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;
import com.tp.dominio.direccion.Direccion;
import com.tp.dominio.geo.Ciudad;
import com.tp.dominio.geo.Pais;
import com.tp.dominio.geo.Provincia;
import com.tp.dominio.pasajero.Pasajero;
import com.tp.dominio.pasajero.PosicionIVA;
import com.tp.dominio.pasajero.TipoDocumento;
import com.tp.dto.PasajeroDTO;
import com.tp.interfaces.VentanaPrincipal;
import com.tp.interfaces.misc.Encabezado;
import com.tp.interfaces.misc.Mensaje;

public class MenuModificarPasajero extends MenuAltaPasajero {
	
	
	private JButton jb_borrar;
	private PasajeroDTO pasajero;
	
	public MenuModificarPasajero(JFrame ventana_contenedora, Encabezado encabezado, MenuBusquedaPasajero estado_anterior, PasajeroDTO pasajero) {
		super(ventana_contenedora,encabezado,estado_anterior);
		
		this.pasajero = pasajero;
		
		for(ActionListener a: jb_siguiente.getActionListeners()) {
			jb_siguiente.removeActionListener(a);
		}
		for(ActionListener a: jb_cancelar.getActionListeners()) {
			jb_cancelar.removeActionListener(a);
		}
		
		jb_borrar = new JButton("Borrar");
		jb_borrar.setBounds(23, jb_siguiente.getBounds().y, 91, 30);
		add(jb_borrar);
		
		this.agregarActionListeners();
		
		this.inicializarCampos();
	}
	
	private void inicializarCampos() {
		
		
	}

	private void agregarActionListeners() {
		
		jb_siguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (campos_validos.values().stream().filter(t->!t).collect(Collectors.toList()).size() != 0) {
					indicarCamposIncompletos();
					return;
				}
				
				try {
					// llamar al gestor TODO
					int opt = Mensaje.mensajeInformacion("La operación ha culminado con éxito.");
					((VentanaPrincipal)ventana_contenedora).cambiarPanel(new MenuBusquedaPasajero(ventana_contenedora,encabezado),660,500,"Gestionar Pasajero");
				}
				catch(Exception exc) { //TODO
					
				}
				
			}
		});
		
		jb_borrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int opt = Mensaje.mensajeConfirmacion("Los datos del pasajero "+"nombre apellido"+", "+"tipodocumento nro doc"
														+ "serán eliminados del sistema.");
				if (opt == 1) {
					
					try {
						// llamar al gestor TODO
						((VentanaPrincipal)ventana_contenedora).cambiarPanel(new MenuBusquedaPasajero(ventana_contenedora,encabezado),660,500,"Gestionar Pasajero");
					}
					catch(Exception exc) { //TODO
						
					}
				}
						
			}
		});
		
		jb_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opt = Mensaje.mensajeConfirmacion("¿Desea cancelar la modificación del pasajero?");
				if (opt == 1) { 
					((VentanaPrincipal)ventana_contenedora).cambiarPanel(menu_anterior,660,500,"Gestionar Pasajero");
				}
			}
		});
	}
	
	/*
	 * Implementado en metodo aparte para mantener los constructores iguales
	 */
	void cargarPasajero(Pasajero psjr) {
		Direccion dir = psjr.getDireccion();
		
		jtf_apellido.setText(psjr.getApellido());
		jtf_nombres.setText(psjr.getNombres());
		jftf_cuit.setText(psjr.getCuit());
		jtf_numero_documento.setText(psjr.getNroDocumento());
		jtf_calle.setText(dir.getCalle());
		jtf_numero.setText(dir.getNroCalle().toString());
		jtf_ocupacion.setText(psjr.getOcupacion());
		jtf_codigo_postal.setText(dir.getCodigoPostal().toString()); // ver comentarios en Direccion
		jtf_piso.setText(dir.getPiso().toString());
		jtf_departamento.setText(dir.getNroDepartamento().toString()); // ver comentarios en Direccion
		jtf_telefono.setText(psjr.getTelefono());
		dc_nacimiento.setDate(Date.from(psjr.getNacimiento()));
		jcb_pais.setSelectedItem(dir.getPais());
		jcb_provincia.setSelectedItem(dir.getProvincia());
		jcb_ciudad.setSelectedItem(dir.getCiudad());
		jcb_factura.setSelectedItem(psjr.getPosicionIVA());
		jcb_nacionalidad.setSelectedItem(psjr.getNacionalidad()); // ver comentarios en Pasajero
	}

}
