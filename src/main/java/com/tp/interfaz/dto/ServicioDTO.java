package com.tp.interfaz.dto;

import java.util.Vector;

public class ServicioDTO {

    private Integer idServicio;
    private String descripcion;
    private Double precioUnitario;
    private Integer cantidad;
    private Integer cantidadPagada;

    public ServicioDTO(){}

    public ServicioDTO(Integer idServicio, String descripcion, Double precioUnitario, Integer cantidad, Integer cantidadPagada) {
        this.cantidad = cantidad;
        this.idServicio = idServicio;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.cantidadPagada = cantidadPagada;
    }

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

    public Integer getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }

    public Integer getCantidadPagada() {
        return cantidadPagada;
    }

    public void setCantidadPagada(Integer cantidadPagada) {
        this.cantidadPagada = cantidadPagada;
    }
    
    
    
}
