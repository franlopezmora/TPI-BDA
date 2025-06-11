package com.tpi.reportes.dtos;

import java.time.LocalDateTime;

public class PruebaDTO {
    private Long id;
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;
    private String comentarios;
    private Long empleadoId;
    private Long interesadoId;
    private Integer vehiculoId;

    public PruebaDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getFechaHoraInicio() { return fechaHoraInicio; }
    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public LocalDateTime getFechaHoraFin() { return fechaHoraFin; }
    public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public String getComentarios() { return comentarios; }
    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Long getEmpleadoId() { return empleadoId; }
    public void setEmpleadoId(Long empleadoId) {
        this.empleadoId = empleadoId;
    }

    public Long getInteresadoId() { return interesadoId; }
    public void setInteresadoId(Long interesadoId) {
        this.interesadoId = interesadoId;
    }

    public Integer getVehiculoId() { return vehiculoId; }
    public void setVehiculoId(Integer vehiculoId) {
        this.vehiculoId = vehiculoId;
    }
}