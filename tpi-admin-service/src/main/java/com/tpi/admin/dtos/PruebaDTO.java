package com.tpi.admin.dtos;

import java.time.LocalDateTime;

public class PruebaDTO {
    private Long id;
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;
    private String comentario;

    private Long empleadoLegajo;
    private Long vehiculoId;
    private Long interesadoId;

    // Getters/setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public LocalDateTime getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Long getEmpleadoLegajo() {
        return empleadoLegajo;
    }

    public void setEmpleadoLegajo(Long empleadoLegajo) {
        this.empleadoLegajo = empleadoLegajo;
    }

    public Long getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(Long vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public Long getInteresadoId() {
        return interesadoId;
    }

    public void setInteresadoId(Long interesadoId) {
        this.interesadoId = interesadoId;
    }
}
