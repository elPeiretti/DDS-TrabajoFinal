package com.tp.interfaz.dto;


public class ItemFacturaDTO {
    
    private Integer cantidad;
    private Integer idItemFactura;
    private ServicioDTO servicio;
    
    public ItemFacturaDTO(){}
    
    public ItemFacturaDTO(Integer cantidadAFacturar, ServicioDTO servicio) {
        this.cantidad = cantidadAFacturar;
        this.servicio = servicio;
    }

    public Integer getCantidad() {
        return cantidad;
    }
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    public Integer getIdItemFactura() {
        return idItemFactura;
    }
    public void setIdItemFactura(Integer idItemFactura) {
        this.idItemFactura = idItemFactura;
    }
    public ServicioDTO getServicio() {
        return servicio;
    }
    public void setServicio(ServicioDTO servicio) {
        this.servicio = servicio;
    }

    
}
