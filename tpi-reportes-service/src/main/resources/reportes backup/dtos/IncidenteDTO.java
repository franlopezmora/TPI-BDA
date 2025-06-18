package com.tpi.reportes.dtos;

import java.time.LocalDateTime;

public class IncidenteDTO {
    private Long idPrueba;
    private Integer nroIncidente;
    private String tipoIncidente;
    private LocalDateTime fechaHora;

    public IncidenteDTO(){}

    public IncidenteDTO(Long idPrueba, Integer nroIncidente, String tipoIncidente, LocalDateTime fechaHora) {
        this.idPrueba = idPrueba;
        this.nroIncidente = nroIncidente;
        this.tipoIncidente = tipoIncidente;
        this.fechaHora = fechaHora;
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

    @Override
    public String toString() {
        return "IncidenteDTO{" +
                "idPrueba=" + idPrueba +
                ", nroIncidente=" + nroIncidente +
                ", tipoIncidente='" + tipoIncidente + '\'' +
                ", fechaHora=" + fechaHora +
                '}';
    }
}
