package com.tp.logica.dominio;

import javax.persistence.*;

@Entity
@Table(name="tpdds.conserje")
public class Conserje {
    @Id
    @Column(name = "id_conserje")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idConserje;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "usuario")
    private String usuario;
    
    @Column(name = "contrasenia")
    private String contrasenia;

    public Conserje(){}

    public Conserje(String nombre, String apellido, String usuario, String contrasenia){
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    
}
