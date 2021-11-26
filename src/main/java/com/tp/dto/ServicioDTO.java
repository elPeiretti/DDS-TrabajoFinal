package com.tp.dto;

import java.util.Vector;

public class ServicioDTO {

    private String descripcion;
    private Double precioUnitario;
    private Integer cantidad;

    public Vector<Object> asVector() {
        Vector<Object> data = new Vector<Object>();
        data.add(descripcion);
        data.add(String.format("%.2f", precioUnitario));
        data.add(cantidad.toString());
        return data;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    
    
    
}
