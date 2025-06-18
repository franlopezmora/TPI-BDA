package com.tpi.reportes.dtos;

import java.time.LocalDateTime;

public class IncidenteVehiculoDTO {
    private Integer nroIncidente;
    private String tipoIncidente;
    private LocalDateTime fechaHora;
    private String patente;
    private String nombreEmpleado;
    private String nombreInteresado;
    private String comentarioPrueba;

    public IncidenteVehiculoDTO(){}
    public IncidenteVehiculoDTO(Integer nroIncidente, String tipoIncidente, LocalDateTime fechaHora, String patente, String nombreEmpleado, String nombreInteresado, String comentarioPrueba){
        this.nroIncidente = nroIncidente;
        this.tipoIncidente = tipoIncidente;
        this.fechaHora = fechaHora;
        this.patente = patente;
        this.nombreEmpleado = nombreEmpleado;
        this.nombreInteresado = nombreInteresado;
        this.comentarioPrueba = comentarioPrueba;
    }

    public Integer getNroIncidente() {
        return nroIncidente;
    }

    public void setNroIncidente(Integer nroIncidente) {
        this.nroIncidente = nroIncidente;
    }

    public String getTipoIncidente() {
        return tipoIncidente;
    }

    public void setTipoIncidente(String tipoIncidente) {
        this.tipoIncidente = tipoIncidente;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getNombreInteresado() {
        return nombreInteresado;
    }

    public void setNombreInteresado(String nombreInteresado) {
        this.nombreInteresado = nombreInteresado;
    }

    public String getComentarioPrueba() {
        return comentarioPrueba;
    }

    public void setComentarioPrueba(String comentarioPrueba) {
        this.comentarioPrueba = comentarioPrueba;
    }

    @Override
    public String toString() {
        return "IncidenteVehiculoDTO{" +
                "nroIncidente=" + nroIncidente +
                ", tipoIncidente='" + tipoIncidente + '\'' +
                ", fechaHora=" + fechaHora +
                ", patente='" + patente + '\'' +
                ", nombreEmpleado='" + nombreEmpleado + '\'' +
                ", nombreInteresado='" + nombreInteresado + '\'' +
                ", comentarioPrueba='" + comentarioPrueba + '\'' +
                '}';
    }
}
