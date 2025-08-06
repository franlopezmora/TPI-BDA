package com.tpi.pruebas.dtos;

import java.time.LocalDateTime;

public class IncidenteDTO {
    private Long id;
    private String mensaje;
    private Long idTipoIncidente;
    private String tipoDescripcion;
    private String telefono;
    private LocalDateTime fechaHora;
    private Long legajoEmpleado;
    private String nombreEmpleado;
    private String apellidoEmpleado;
    private Long idPrueba;
    private PruebaDTO prueba;
    private Long idInteresado;
    private String nombreInteresado;
    private String apellidoInteresado;

    // Getters y setters


    public Long getIdTipoIncidente() {
        return idTipoIncidente;
    }

    public void setIdTipoIncidente(Long idTipoIncidente) {
        this.idTipoIncidente = idTipoIncidente;
    }

    public String getTipoDescripcion() {
        return tipoDescripcion;
    }

    public void setTipoDescripcion(String tipoDescripcion) {
        this.tipoDescripcion = tipoDescripcion;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public PruebaDTO getPrueba() {
        return prueba;
    }

    public void setPrueba(PruebaDTO prueba) {
        this.prueba = prueba;
    }

    public Long getIdInteresado() {
        return idInteresado;
    }

    public void setIdInteresado(Long idInteresado) {
        this.idInteresado = idInteresado;
    }

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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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

    // ————— Métodos “puente” para compatibilidad con los tests —————

    /** Para que dto.setTipo(...) compile y funcione igual que setTipoDescripcion */
    public void setTipo(String tipo) {
        this.tipoDescripcion = tipo;
    }

    /** Para que dto.getTipo() compile y devuelva lo mismo que getTipoDescripcion */
    public String getTipo() {
        return this.tipoDescripcion;
    }

    /** Para que dto.setFecha(...) compile y funcione igual que setFechaHora */
    public void setFecha(LocalDateTime fecha) {
        this.fechaHora = fecha;
    }

    /** Para que dto.getFecha() compile y devuelva lo mismo que getFechaHora */
    public LocalDateTime getFecha() {
        return this.fechaHora;
    }
}