package com.tp.logica.gestores;

import java.util.ArrayList;
import java.util.List;

import com.tp.interfaz.dto.CiudadDTO;
import com.tp.interfaz.dto.PaisDTO;
import com.tp.interfaz.dto.ProvinciaDTO;
import com.tp.logica.dominio.Ciudad;
import com.tp.logica.dominio.Pais;
import com.tp.logica.dominio.Provincia;
import com.tp.persistencia.dao.CiudadDAO;
import com.tp.persistencia.dao.CiudadSqlDAO;
import com.tp.persistencia.dao.PaisDAO;
import com.tp.persistencia.dao.PaisSqlDAO;
import com.tp.persistencia.dao.ProvinciaDAO;
import com.tp.persistencia.dao.ProvinciaSqlDAO;

public class GestorGeografico {

	public static List<PaisDTO> getAllPais() {

		PaisDAO paisDAO = new PaisSqlDAO();
		
		List<Pais> paises = paisDAO.getAll();
		
		List<PaisDTO> resultado = new ArrayList<PaisDTO>();
		
		for (Pais p : paises) {
			resultado.add(new PaisDTO(p.getIdPais(),p.getNombre()));
		}
		
		return resultado;
	}

	public static List<ProvinciaDTO> getAllProvinciaByPais(Integer idPais) {
		
		ProvinciaDAO provinciaDAO = new ProvinciaSqlDAO();
		
		List<Provincia> provincias = provinciaDAO.getByPais(idPais);
		
		List<ProvinciaDTO> resultado = new ArrayList<ProvinciaDTO>();
		
		for (Provincia p : provincias) {
			resultado.add(new ProvinciaDTO(p.getIdProvincia(),p.getNombre()));
		}
		
		return resultado;
		
	}

	public static List<CiudadDTO> getAllCiudadByProvincia(Integer idProvincia) {
		
		CiudadDAO ciudadDAO = new CiudadSqlDAO();
		
		List<Ciudad> ciudades = ciudadDAO.getByProvincia(idProvincia);
		
		List<CiudadDTO> resultado = new ArrayList<CiudadDTO>();
		
		for (Ciudad p : ciudades) {
			resultado.add(new CiudadDTO(p.getIdCiudad(),p.getNombre()));
		}
		
		return resultado;
	}

}
