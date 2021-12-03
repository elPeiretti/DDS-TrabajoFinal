package com.tp.interfaz.pantallas.misc;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Function;

import javax.swing.JOptionPane;

import com.tp.interfaz.dto.ReservaDTO;

public class Mensaje {
	
	public static int mensajeConfirmacion(String msg) {
		String[] opt = {"Cancelar", "Confirmar"};
		return JOptionPane.showOptionDialog(null, msg, "Confirmar acción", JOptionPane.DEFAULT_OPTION, -1, null, opt, opt[1]);
	}
	
	public static int mensajeConfirmacion(String msg, String titulo, String[] opt) {
		return JOptionPane.showOptionDialog(null, msg, titulo, JOptionPane.DEFAULT_OPTION, -1, null, opt, opt[1]);
	}

	public static int mensajeInformacion(String msg) {
		String[] opt = {"Continuar"};
		return JOptionPane.showOptionDialog(null, msg, "Mensaje Informativo", JOptionPane.DEFAULT_OPTION, -1, null, opt, opt[0]);
	}

	public static int warningDocumentoExistente(){
		String[] opt = {"Corregir", "Aceptar Igualmente"};
		return JOptionPane.showOptionDialog(null, "¡CUIDADO! El tipo y número de documento ya existen en el sistema", "Alerta: Documento ya existente", JOptionPane.DEFAULT_OPTION, -1, null, opt, opt[1]);
	}
	
	public static int mensajeError(String[] msgs){
		String[] opt = {"Confirmar"};
		String msg = "Se produjeron los siguientes errores durante la ejecución:\n";
		Integer i = 1;
		for (String m : msgs){
			msg+=i.toString()+". "+m+"\n";
			i++;
		}
		return JOptionPane.showOptionDialog(null, msg, "Error", JOptionPane.DEFAULT_OPTION, -1, null, opt, opt[0]);
	}

    public static int mensajeOcuparIgual(List<ReservaDTO> reservas) {
		
		String msg = "<html><center>La habitación "+reservas.get(0).getNumeroHabitacion()+" posee las siguientes reservas registradas:<br><br>";
		Function<LocalDate,String> f = (fecha) -> (DateTimeFormatter.ofPattern("dd/MM/yyyy").format(fecha));

		for (ReservaDTO r : reservas){
			msg += r.getApellidoResponsable()+", "+r.getNombreResponsable()+"<br>";
			msg += "Desde: "+ f.apply(r.getInicioReserva()) + " - Hasta: "+ f.apply(r.getFinReserva())+ "<br><br>";
		}
		msg+= "Seleccione la operación a realizar:</center></html>";

		String[] opt = {"Volver", "Ocupar Igual"};

		return JOptionPane.showOptionDialog(null, msg, "¡Reservas existentes!", JOptionPane.DEFAULT_OPTION, -1, null, opt, opt[1]);
    }

	public static int mensajeConfirmacionOcupacion() {
		String opt[] = {"Salir", "Cargar otra habitación","Seguir cargando"};
		return JOptionPane.showOptionDialog(null, "<html><center>¿Desea continuar operando?</center></html>", "Continuar Operando", JOptionPane.DEFAULT_OPTION, -1, null, opt, opt[2]);
	}

	
}
