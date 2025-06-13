package com.tpi.vehiculos.dtos;

import java.time.LocalDateTime;

public class PosicionDTO {
    private Long id;
    private LocalDateTime fechaHora;
    private Double latitud;
    private Double longitud;
    private Long idVehiculo;

    // Constructor con campos
    public PosicionDTO(Long id, LocalDateTime fechaHora, Double latitud, Double longitud, Long idVehiculo) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.latitud = latitud;
        this.longitud = longitud;
        this.idVehiculo = idVehiculo;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    public Double getLatitud() { return latitud; }
    public void setLatitud(Double latitud) { this.latitud = latitud; }

    public Double getLongitud() { return longitud; }
    public void setLongitud(Double longitud) { this.longitud = longitud; }

    public Long getIdVehiculo() { return idVehiculo; }
    public void setIdVehiculo(Long idVehiculo) { this.idVehiculo = idVehiculo; }
}
