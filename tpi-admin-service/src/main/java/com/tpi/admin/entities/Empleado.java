package com.tpi.admin.entities;

import jakarta.persistence.*;


@Entity
@Table(name= "Empleados")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LEGAJO")
    private Long legajo;

    @Column(name = "NOMBRE", nullable = false, length = 30)
    private String nombre;

    @Column(name = "APELLIDO", nullable = false, length = 50)
    private String apellido;

    @Column(name = "TELEFONO_CONTACTO", nullable = false)
    private String telefonoContacto;

    public Empleado() {
    }

    public Empleado(Long legajo, String nombre, String apellido, String telefonoContacto) {
        this.legajo = legajo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefonoContacto = telefonoContacto;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "legajo=" + legajo +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", telefonoContacto='" + telefonoContacto + '\'' +
                '}';
    }

    public Long getLegajo() {
        return legajo;
    }

    public void setLegajo(Long legajo) {
        this.legajo = legajo;
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

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }
}
