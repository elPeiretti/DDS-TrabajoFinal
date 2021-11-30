package com.tp.dominio.factura;

public interface ResponsablePagoTerceroDAO {
    public ResponsablePagoTercero getByCuit(String cuit);

    public ResponsablePagoTercero getResponsableTerceroById(Integer idResponsable);
}
