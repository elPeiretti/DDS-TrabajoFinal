package com.tp.dominio.habitacion;

import java.time.LocalDate;
import java.util.List;

public interface HabitacionDAO {

	List<Habitacion> getAllHabitaciones();

    Habitacion getHabitacionByNumero(String numero);
    public void insertarHabitacion(Habitacion hab);
    public Habitacion getHabitacionByIdWithCostoVigenteEn(Integer id,LocalDate fechaIngreso);

}
