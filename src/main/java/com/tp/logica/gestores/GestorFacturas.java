package com.tp.logica.gestores;

import java.util.ArrayList;
import java.util.List;

import com.tp.interfaz.dto.FacturaDTO;
import com.tp.interfaz.dto.ItemFacturaDTO;
import com.tp.interfaz.dto.ResponsablePagoTerceroDTO;
import com.tp.logica.dominio.Factura;
import com.tp.logica.dominio.ItemFactura;
import com.tp.logica.dominio.ResponsablePagoTercero;
import com.tp.logica.dominio.Servicio;
import com.tp.logica.excepciones.FacturaSinItemsException;
import com.tp.persistencia.dao.FacturaDAO;
import com.tp.persistencia.dao.FacturaSqlDAO;
import com.tp.persistencia.dao.ResponsablePagoTerceroDAO;
import com.tp.persistencia.dao.ResponsablePagoTerceroSqlDAO;

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

    private static ResponsablePagoTercero getResponsableTerceroById(Integer idResponsable) {
        ResponsablePagoTerceroDAO rpDao = new ResponsablePagoTerceroSqlDAO();
        return rpDao.getResponsableTerceroById(idResponsable);
    }

    public static void generarFactura(FacturaDTO facturaDto) throws FacturaSinItemsException{
        Factura factura = new Factura(facturaDto);
        FacturaDAO fDao = new FacturaSqlDAO();

        if(facturaDto.getResponsablePasajero() != null)
            factura.setResponsablePasajero( GestorPasajeros.getPasajeroById(facturaDto.getResponsablePasajero().getIdPasajero()) );
        else{
            factura.setResponsableTercero( GestorFacturas.getResponsableTerceroById(facturaDto.getResponsableTercero().getIdResponsable()) );
        }

        List<ItemFactura> listaItems = new ArrayList<ItemFactura>();
        for(ItemFacturaDTO itemDTO : facturaDto.getListaItems()){
            Servicio s = GestorServicios.getServicioById(itemDTO.getServicio().getIdServicio());
            ItemFactura item = new ItemFactura(itemDTO);
            item.setServicio(s);
            s.aumentarCantidadPagada(item.getCantidad());
            listaItems.add(item);
        }

        if(listaItems.size() == 0)
            throw new FacturaSinItemsException();

        factura.setItems(listaItems);
        fDao.insertarFactura(factura);
    }
    
}