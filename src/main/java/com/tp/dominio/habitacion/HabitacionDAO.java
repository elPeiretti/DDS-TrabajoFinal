package com.tp.dominio.habitacion;

import java.time.LocalDate;
import java.util.List;

import com.tp.excepciones.NuevaHabitacionException;

public interface HabitacionDAO {

	List<Habitacion> getAllHabitaciones();

    Habitacion getHabitacionByNumero(String numero);
    public void insertarHabitacion(Habitacion hab) throws NuevaHabitacionException;
    public Habitacion getHabitacionByIdWithCostoVigenteEn(Integer id,LocalDate fechaIngreso);

}
