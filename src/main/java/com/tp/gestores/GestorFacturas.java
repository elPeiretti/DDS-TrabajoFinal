package com.tp.gestores;

import com.tp.dominio.factura.ResponsablePagoTercero;
import com.tp.dominio.factura.ResponsablePagoTerceroDAO;
import com.tp.dominio.factura.ResponsablePagoTerceroSqlDAO;
import com.tp.dto.ResponsablePagoTerceroDTO;

public class GestorFacturas {

    public static ResponsablePagoTerceroDTO getResponsableTerceroByCuit(String cuit) {
        ResponsablePagoTerceroDAO responsableDAO = new ResponsablePagoTerceroSqlDAO();
        ResponsablePagoTercero responsable = responsableDAO.getByCuit(cuit);
        if(responsable == null) return null;
        return convertToResponsableDTO(responsable);
    }
    private static ResponsablePagoTerceroDTO convertToResponsableDTO(ResponsablePagoTercero resp) {
        ResponsablePagoTerceroDTO respDTO = new ResponsablePagoTerceroDTO();
        respDTO.setCuit(resp.getCuit());
        
        respDTO.setIdResponsable(resp.getIdResponsable());
        respDTO.setRazonSocial(resp.getRazonSocial());
        respDTO.setTelefono(resp.getTelefono());
        return respDTO;
    }
}