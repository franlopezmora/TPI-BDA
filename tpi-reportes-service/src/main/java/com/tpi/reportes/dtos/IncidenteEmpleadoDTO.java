package com.tpi.reportes.dtos;

import java.time.LocalDateTime;

public class IncidenteEmpleadoDTO {
    private Long idPrueba;
    private Integer nroIncidente;
    private String tipoIncidente;
    private LocalDateTime fechaHora;
    private Integer idVehiculo;
    private String nombreInteresado;

    private IncidenteEmpleadoDTO(){}

    public IncidenteEmpleadoDTO(Long idPrueba, Integer nroIncidente, String tipoIncidente, LocalDateTime fechaHora, Integer idVehiculo, String nombreInteresado) {
        this.idPrueba = idPrueba;
        this.nroIncidente = nroIncidente;
        this.tipoIncidente = tipoIncidente;
        this.fechaHora = fechaHora;
        this.idVehiculo = idVehiculo;
        this.nombreInteresado = nombreInteresado;
    }

    public Long getIdPrueba() {
        return idPrueba;
    }

    public void setIdPrueba(Long idPrueba) {
        this.idPrueba = idPrueba;
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

    public String getNombreInteresado() {
        return nombreInteresado;
    }

    public void setNombreInteresado(String nombreInteresado) {
        this.nombreInteresado = nombreInteresado;
    }

    public Integer getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Integer idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    @Override
    public String toString() {
        return "IncidenteEmpleadoDTO{" +
                "idPrueba=" + idPrueba +
                ", nroIncidente=" + nroIncidente +
                ", tipoIncidente='" + tipoIncidente + '\'' +
                ", fechaHora=" + fechaHora +
                ", idVehiculo=" + idVehiculo +
                ", nombreInteresado='" + nombreInteresado + '\'' +
                '}';
    }
}
