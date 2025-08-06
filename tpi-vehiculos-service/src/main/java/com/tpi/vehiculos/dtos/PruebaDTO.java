package com.tpi.vehiculos.dtos;

import java.time.LocalDateTime;

public class PruebaDTO {
    private Long id;
    private LocalDateTime fechaHoraInicio;
    private Long idVehiculo;

    public PruebaDTO() {}

    public PruebaDTO(Long id, LocalDateTime fechaHoraInicio, Long idVehiculo) {
        this.id = id;
        this.fechaHoraInicio = fechaHoraInicio;
        this.idVehiculo = idVehiculo;
    }

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

    public Long getIdVehiculo() {
        return idVehiculo;
    }
    public void setIdVehiculo(Long idVehiculo) {
        this.idVehiculo = idVehiculo;
    }
}
