package com.tp.interfaz.dto;

public class ResponsablePagoTerceroDTO {
    private Integer idResponsable;
    private String razonSocial;
    private String cuit;
    private String telefono;

    public Integer getIdResponsable() {
        return idResponsable;
    }
    public void setIdResponsable(Integer idResponsable) {
        this.idResponsable = idResponsable;
    }
    public String getRazonSocial() {
        return razonSocial;
    }
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }
    public String getCuit() {
        return cuit;
    }
    public void setCuit(String cuit) {
        this.cuit = cuit;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
