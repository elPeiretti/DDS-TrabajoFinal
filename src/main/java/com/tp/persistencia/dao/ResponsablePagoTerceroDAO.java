package com.tp.persistencia.dao;

import com.tp.logica.dominio.ResponsablePagoTercero;

public interface ResponsablePagoTerceroDAO {
    public ResponsablePagoTercero getByCuit(String cuit);

    public ResponsablePagoTercero getResponsableTerceroById(Integer idResponsable);
}
