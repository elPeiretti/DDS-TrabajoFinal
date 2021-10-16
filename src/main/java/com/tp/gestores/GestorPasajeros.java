package com.tp.gestores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tp.dominio.direccion.Direccion;
import com.tp.dominio.direccion.DireccionSqlDAO;
import com.tp.dominio.geo.Ciudad;
import com.tp.dominio.geo.CiudadSqlDAO;
import com.tp.dominio.geo.Pais;
import com.tp.dominio.geo.PaisSqlDAO;
import com.tp.dominio.pasajero.*;
import com.tp.dto.BusqPasajeroDTO;
import com.tp.dto.DireccionDTO;
import com.tp.dto.PasajeroDTO;
import com.tp.dto.PosicionIVADTO;
import com.tp.dto.TipoDocumentoDTO;
import com.tp.excepciones.DocumentoExistenteException;

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
	
	public static List<PasajeroDTO> getPasajerosBy(BusqPasajeroDTO criterios, Integer li, Integer cant) {
		
		PasajeroDAO pasajeroDAO = new PasajeroSqlDAO();
		
		List<Pasajero> pasajeros = pasajeroDAO.getPasajerosByCriteria(criterios, li, cant);
		
		List<PasajeroDTO> resultado = new ArrayList<PasajeroDTO>();
		
		for(Pasajero p : pasajeros) {
			TipoDocumento auxTipoDocumento = p.getTipoDocumento();
			Direccion auxDir = p.getDireccion();
			DireccionDTO auxDirDto = new DireccionDTO(auxDir.getIdDireccion(), auxDir.getCodigoPostal(), auxDir.getCalle(), auxDir.getNroCalle(), auxDir.getPiso(), auxDir.getNroDepartamento(), auxDir.getCiudad().getIdCiudad(), auxDir.getProvincia().getIdProvincia(), auxDir.getCiudad().getPais().getIdPais());
			PasajeroDTO auxPas = new PasajeroDTO(p.getIdPasajero(), p.getNombres(), p.getApellido(), p.getCuit(), p.getNroDocumento(), p.getNacimiento(), p.getNacionalidad().getIdPais(), p.getEmail(), p.getTelefono(), p.getOcupacion(), new TipoDocumentoDTO(auxTipoDocumento.getIdTipoDocumento(), auxTipoDocumento.getTipo()),p.getPosicionIVA().getIdPosicionIVA(), auxDirDto);
			resultado.add(auxPas);
		}
		
		return resultado;
	}
	
	public static Long getCountPasajerosBy(BusqPasajeroDTO criterios) {
		
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

    public static void darAltaPasajero(PasajeroDTO p, boolean validarDocumento) throws DocumentoExistenteException {
		boolean documentoExistente = false;
		
		if(validarDocumento)
			documentoExistente = GestorPasajeros.existeDocumento(p);
		
		if(documentoExistente){
			throw new DocumentoExistenteException();
		}

		Pasajero pasajero = new Pasajero(p);
		TipoDocumento td = new TipoDocumentoSqlDAO().getById(p.getTipoDocumentoDTO().getIdTipoDocumento());
		Direccion direc = new Direccion(p.getDireccionDTO());
		Ciudad ciudad = new CiudadSqlDAO().getById(p.getDireccionDTO().getIdCiudad());
		direc.setCiudad(ciudad);
		Pais nacionalidad = new PaisSqlDAO().getById(p.getNacionalidad());
		PosicionIVA pIva = new PosicionIVASqlDAO().getById(p.getIdPosicionIVA());

		if (ciudad.getPais().equals(nacionalidad)) {
			nacionalidad = ciudad.getPais();
		}
		pasajero.setPosicionIVA(pIva);
		pasajero.setNacionalidad(nacionalidad);
		pasajero.setTipoDocumento(td);
		pasajero.setDireccion(direc);
		
		new PasajeroSqlDAO().insertarPasajero(pasajero);
	}

	private static boolean existeDocumento(PasajeroDTO p) {
		PasajeroDAO pDao = new PasajeroSqlDAO();
		BusqPasajeroDTO dto = new BusqPasajeroDTO();
		dto.setTipoDocumentoDTO(p.getTipoDocumentoDTO());
		dto.setNroDocumento(p.getNroDocumento());
		return !pDao.getPasajerosByCriteria(dto).isEmpty();
	}
	
}
