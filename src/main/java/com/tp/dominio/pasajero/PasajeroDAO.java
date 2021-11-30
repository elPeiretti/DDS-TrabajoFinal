package com.tp.dominio.pasajero;

import java.util.List;

import com.tp.dto.BusqPasajeroDTO;
import com.tp.dto.PasajeroDTO;

public interface PasajeroDAO {

	public List<Pasajero> getPasajerosByCriteria(BusqPasajeroDTO criterios, Integer li, Integer cant);
	public List<Pasajero> getPasajerosByCriteria(BusqPasajeroDTO criterios);
	public List<Pasajero> getPasajerosByCriteria(BusqPasajeroDTO criterios, Integer li, Integer cant, PasajeroDTO responsable);
	public List<Pasajero> getPasajerosAdultosByCriteria(BusqPasajeroDTO criterios, Integer li, Integer cant);
	
	public Long getCountPasajerosByCriteria(BusqPasajeroDTO criterios);
	public Long getCountPasajerosByCriteria(BusqPasajeroDTO criterios, PasajeroDTO responsable);
	public Long getCountPasajerosAdultosByCriteria(BusqPasajeroDTO criterios);
	
	public void insertarPasajero(Pasajero pasajero);
	
	public Pasajero getPasajeroById(Integer idPasajero);
	public List<Pasajero> getPasajerosById(List<Integer> idPasajeros);
    public List<Pasajero> getPasajerosQueNoEstenOcupandoByCriteria(BusqPasajeroDTO criterios_actuales, int li,int cant);
	public Long getCountPasajerosQueNoEstenOcupandoByCriteria(BusqPasajeroDTO criterios_actuales);
	
}
