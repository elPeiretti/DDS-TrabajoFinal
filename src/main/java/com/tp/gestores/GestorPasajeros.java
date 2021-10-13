package com.tp.gestores;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tp.dominio.direccion.Direccion;
import com.tp.dominio.pasajero.*;
import com.tp.dto.DireccionDTO;
import com.tp.dto.PasajeroDTO;
import com.tp.dto.PosicionIVADTO;
import com.tp.dto.TipoDocumentoDTO;

public class GestorPasajeros {

	
	public static List<TipoDocumentoDTO> getAllTipoDocumento() {
		
		TipoDocumentoDAO tipoDocumentoDao = new TipoDocumentoSqlDAO();
		
		List<TipoDocumento> tiposDocumentos = tipoDocumentoDao.getAll();
		
		List<TipoDocumentoDTO> resultado = new ArrayList<TipoDocumentoDTO>();
		
		
		for(TipoDocumento t : tiposDocumentos) {
			resultado.add(new TipoDocumentoDTO(t.getIdTipoDocumento(),t.getTipo()));
			
		}
		
		return resultado; 
		
	}
	
	public static List<PasajeroDTO> getPasajerosBy(Map<String,Object>criterios, Integer li, Integer cant) {
		
		PasajeroDAO pasajeroDAO = new PasajeroSqlDAO();
		
		List<Pasajero> pasajeros = pasajeroDAO.getPasajerosByCriteria(criterios, li, cant);
		
		List<PasajeroDTO> resultado = new ArrayList<PasajeroDTO>();
		
		for(Pasajero p : pasajeros) {
			TipoDocumento auxTipoDocumento = p.getTipoDocumento();
			Direccion auxDir = p.getDireccion();
			DireccionDTO auxDirDto = new DireccionDTO(auxDir.getIdDireccion(), auxDir.getCodigoPostal(), auxDir.getCalle(), auxDir.getNroCalle(), auxDir.getPiso(), auxDir.getNroDepartamento(), auxDir.getCiudad().getIdCiudad(), auxDir.getProvincia().getIdProvincia(), auxDir.getCiudad().getPais().getIdPais());
			PasajeroDTO auxPas = new PasajeroDTO(p.getIdPasajero(), p.getNombres(), p.getApellido(), p.getCuit(), p.getNroDocumento(), p.getNacimiento(), p.getNacionalidad(), p.getEmail(), p.getTelefono(), p.getOcupacion(), new TipoDocumentoDTO(auxTipoDocumento.getIdTipoDocumento(), auxTipoDocumento.getTipo()),p.getPosicionIVA().getIdPosicionIVA(), auxDirDto);
			resultado.add(auxPas);
		}
		
		return resultado;
	}
	
	public static Long getCountPasajerosBy(Map<String,Object>criterios) {
		
		PasajeroDAO pasajeroDAO = new PasajeroSqlDAO();
		return pasajeroDAO.getCountPasajerosByCriteria(criterios);
		
	}

	public static List<PosicionIVADTO> getAllPosicionIVA() {
		PosicionIVADAO tipoDocumentoDao = new PosicionIVASqlDAO();
		
		List<PosicionIVA> posicionesIVA = tipoDocumentoDao.getAll();
		
		List<PosicionIVADTO> resultado = new ArrayList<PosicionIVADTO>();
		
		for(PosicionIVA t : posicionesIVA) {
			resultado.add(new PosicionIVADTO(t.getIdPosicionIVA(),t.getPosicion()));
			
		}
		
		return resultado; 
	}
	
}
