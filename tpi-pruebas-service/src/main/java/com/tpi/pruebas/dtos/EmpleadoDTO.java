package com.tpi.pruebas.dtos;

public class EmpleadoDTO {
    private Long legajo;
    private String nombre;
    private String apellido;
    private String telefonoContacto;
    public EmpleadoDTO(){

    }
    public EmpleadoDTO(Long legajo, String nombre, String apellido, String telefonoContacto){
        this.legajo = legajo;
        this.nombre = nombre;
        this.apellido =apellido;
        this.telefonoContacto = telefonoContacto;
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
    public String getNombreCompleto(){
        return nombre + " " + apellido;
    }
}
