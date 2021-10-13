package com.tp.gestores;

import java.util.ArrayList;
import java.util.List;

import com.tp.dominio.geo.*;
import com.tp.dto.CiudadDTO;
import com.tp.dto.PaisDTO;
import com.tp.dto.ProvinciaDTO;

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
