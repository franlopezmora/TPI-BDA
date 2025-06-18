package com.tpi.reportes.dtos;

import java.time.LocalDateTime;

public class PosicionDTO {
    private Long id;
    private Integer vehiculoId;
    private LocalDateTime fechaHora;
    private Double latitud;
    private Double longitud;

    /** distancia euclídea */
    public double distanceTo(PosicionDTO o) {
        double dx = this.latitud  - o.latitud;
        double dy = this.longitud - o.longitud;
        return Math.sqrt(dx*dx + dy*dy);
    }
    // getters y setters
    // getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Integer getVehiculoId() { return vehiculoId; }
    public void setVehiculoId(Integer vehiculoId) { this.vehiculoId = vehiculoId; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
    public Double getLatitud() { return latitud; }
    public void setLatitud(Double latitud) { this.latitud = latitud; }
    public Double getLongitud() { return longitud; }
    public void setLongitud(Double longitud) { this.longitud = longitud; }
}
