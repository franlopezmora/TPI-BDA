package com.tpi.pruebas.dtos;

import java.time.LocalDateTime;

public class IncidenteDTO {
    private Long id;
    private String mensaje;
    private String tipo; // "Peligro" o "Radio"
    private String telefono;
    private LocalDateTime fecha;
    private Long legajoEmpleado;
    private Long idPrueba;
    private PruebaDTO prueba;
    private String nombreEmpleado;
    private String apellidoEmpleado;
    private String nombreInteresado;
    private String apellidoInteresado;

    // Getters y setters


    public Long getLegajoEmpleado() {
        return legajoEmpleado;
    }

    public void setLegajoEmpleado(Long legajoEmpleado) {
        this.legajoEmpleado = legajoEmpleado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Long getIdPrueba() {
        return idPrueba;
    }

    public void setIdPrueba(Long idPrueba) {
        this.idPrueba = idPrueba;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getApellidoEmpleado() {
        return apellidoEmpleado;
    }

    public void setApellidoEmpleado(String apellidoEmpleado) {
        this.apellidoEmpleado = apellidoEmpleado;
    }

    public String getNombreInteresado() {
        return nombreInteresado;
    }

    public void setNombreInteresado(String nombreInteresado) {
        this.nombreInteresado = nombreInteresado;
    }

    public String getApellidoInteresado() {
        return apellidoInteresado;
    }

    public void setApellidoInteresado(String apellidoInteresado) {
        this.apellidoInteresado = apellidoInteresado;
    }
}