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
import com.tp.excepciones.CamposInvalidosException;
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
		
		return convertToPasajeroDTO(pasajeros);
		
	}
	
	public static List<PasajeroDTO> convertToPasajeroDTO(List<Pasajero> pasajeros) {
	public static List<PasajeroDTO> getPasajerosBy(BusqPasajeroDTO criterios, Integer li, Integer cant, PasajeroDTO responsable) {
		
		PasajeroDAO pasajeroDAO = new PasajeroSqlDAO();
		
		List<Pasajero> pasajeros = pasajeroDAO.getPasajerosByCriteria(criterios, li, cant, responsable);
		
		return convertToPasajeroDTO(pasajeros);
		
	}
		
	private static List<PasajeroDTO> convertToPasajeroDTO(List<Pasajero> pasajeros) {
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
	
	public static Long getCountPasajerosBy(BusqPasajeroDTO criterios, PasajeroDTO responsable) {
		
		PasajeroDAO pasajeroDAO = new PasajeroSqlDAO();
		return pasajeroDAO.getCountPasajerosByCriteria(criterios, responsable);
		
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

    public static void darAltaPasajero(PasajeroDTO p, boolean validarDocumento, boolean cuit_obligatorio) throws DocumentoExistenteException, CamposInvalidosException {
		boolean documentoExistente = false;
		
		validarCampos(p, cuit_obligatorio);
		
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
		return pDao.getCountPasajerosByCriteria(dto)>0;
	}

	public static void validarCampos(PasajeroDTO pasajero, boolean cuit_obligatorio) throws CamposInvalidosException{

		ArrayList<String> errores = new ArrayList<String>();
		errores.add("Campos inválidos en el pasajero.");

		if (pasajero.getApellido().isBlank())
			errores.add("El campo apellido no puede estar vacío.");
		else if(!pasajero.getApellido().matches("^[a-zA-ZÀ-ÿ\u00f1\u00d1]+(\s*[a-zA-ZÀ-ÿ\u00f1\u00d1]*)*[a-zA-ZÀ-ÿ\u00f1\u00d1]+$"))
			errores.add("El campo apellido solo puede contener letras");

		if (pasajero.getNombres().isBlank())
			errores.add("El campo nombres no puede estar vacío.");
		else if(!pasajero.getNombres().matches("^[a-zA-ZÀ-ÿ\u00f1\u00d1]+(\s*[a-zA-ZÀ-ÿ\u00f1\u00d1]*)*[a-zA-ZÀ-ÿ\u00f1\u00d1]+$")) 
			errores.add("El campo nombres solo puede contener letras");

		if(cuit_obligatorio && pasajero.getCuit() == null ) 
			errores.add("El campo CUIT no puede estar vacío.");
		else if(pasajero.getCuit() != null && !pasajero.getCuit().matches("[0-9][0-9]-[0-9]{8}-[0-9]")) 
			errores.add("El CUIT posee un formato inválido.");

		if (pasajero.getFechaDeNacimiento() == null)
			errores.add("El campo fecha de nacimiento no puede estar vacío.");

		if (pasajero.getDireccionDTO().getCalle().isBlank())
			errores.add("El campo calle no puede estar vacío.");
		
		if (pasajero.getDireccionDTO().getNroCalle() == null)
			errores.add("El campo número no puede estar vacío.");

		if (pasajero.getOcupacion().isBlank())
			errores.add("El campo ocupación no puede estar vacío.");

		if (pasajero.getNroDocumento().isBlank())
			errores.add("El campo número de documento no puede estar vacío.");

		if (pasajero.getDireccionDTO().getCodigoPostal().isBlank())
			errores.add("El campo código postal no puede estar vacío.");
		else if(!pasajero.getDireccionDTO().getCodigoPostal().matches("[A-Z]*[0-9]+[A-Z]*")) 
			errores.add("El campo código postal posee un formato inválido.");
		
		if (pasajero.getTelefono().isBlank())
			errores.add("El campo teléfono no puede estar vacío.");
		else if(!pasajero.getTelefono().matches("(\\+)?([0-9]){7,15}")) 
			errores.add("El campo teléfono posee un formato inválido.");

		if(!pasajero.getEmail().matches("(^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?!-)(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6})?"))
			errores.add("El campo email posee un formato inválido.");
		
		if (errores.size()>1) throw new CamposInvalidosException(errores);
	}

	public static List<PasajeroDTO> getPasajerosAdultosBy(BusqPasajeroDTO criterios, Integer li, Integer cant) {
		PasajeroDAO pasajeroDAO = new PasajeroSqlDAO();
		
		List<Pasajero> pasajeros = pasajeroDAO.getPasajerosAdultosByCriteria(criterios, li, cant);
		
		return convertToPasajeroDTO(pasajeros);
	}

	public static double getCountPasajerosAdultosBy(BusqPasajeroDTO criterios) {
		
		PasajeroDAO pasajeroDAO = new PasajeroSqlDAO();
		
		return pasajeroDAO.getCountPasajerosAdultosByCriteria(criterios);
	}
	
	
	
}
