package com.tp.interfaz.dto;

import java.time.LocalDate;
import java.util.List;

import com.tp.logica.dominio.TipoFactura;

public class FacturaDTO {
    private TipoFactura tipo;
    private Double importeTotal;
    private LocalDate fechaEmision;
    private Integer idFactura;
    private Double totalIVA;
    private List<ItemFacturaDTO> listaItems;
    private ResponsablePagoTerceroDTO responsableTercero;
    private PasajeroDTO responsablePasajero;
    
    public FacturaDTO(){}

    public FacturaDTO(TipoFactura tipo, Double total, LocalDate fechaEmision, Double iva, List<ItemFacturaDTO> listaItems, PasajeroDTO pasajero, ResponsablePagoTerceroDTO responsableTercero) {
        this.tipo = tipo;
        this.importeTotal = total;
        this.fechaEmision = fechaEmision;
        this.totalIVA = iva;
        this.listaItems = listaItems;
        this.responsablePasajero = pasajero;
        this.responsableTercero = responsableTercero;
    }

    public TipoFactura getTipo() {
        return tipo;
    }
    public void setTipo(TipoFactura tipo) {
        this.tipo = tipo;
    }
    public Double getImporteTotal() {
        return importeTotal;
    }
    public void setImporteTotal(Double importeTotal) {
        this.importeTotal = importeTotal;
    }
    public LocalDate getFechaEmision() {
        return fechaEmision;
    }
    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }
    public Integer getIdFactura() {
        return idFactura;
    }
    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }
    public Double getTotalIVA() {
        return totalIVA;
    }
    public void setTotalIVA(Double totalIVA) {
        this.totalIVA = totalIVA;
    }
    public List<ItemFacturaDTO> getListaItems() {
        return listaItems;
    }
    public void setListaItems(List<ItemFacturaDTO> listaItems) {
        this.listaItems = listaItems;
    }
    public ResponsablePagoTerceroDTO getResponsableTercero() {
        return responsableTercero;
    }
    public void setResponsableTercero(ResponsablePagoTerceroDTO responsableTercero) {
        this.responsableTercero = responsableTercero;
    }
    public PasajeroDTO getResponsablePasajero() {
        return responsablePasajero;
    }
    public void setResponsablePasajero(PasajeroDTO responsablePasajero) {
        this.responsablePasajero = responsablePasajero;
    }
    
    

}
