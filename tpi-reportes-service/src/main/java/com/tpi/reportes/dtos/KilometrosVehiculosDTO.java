package com.tpi.reportes.dtos;


import java.time.LocalDateTime;

/**
 * DTO para reportar la cantidad de kilómetros recorridos por un vehículo en un período.
 */
public class KilometrosVehiculosDTO {
    private Integer vehiculoId;
    private LocalDateTime desde;
    private LocalDateTime hasta;
    private Double totalKilometros;

    public KilometrosVehiculosDTO() {
    }

    public KilometrosVehiculosDTO(Integer vehiculoId, LocalDateTime desde, LocalDateTime hasta, Double totalKilometros) {
        this.vehiculoId = vehiculoId;
        this.desde = desde;
        this.hasta = hasta;
        this.totalKilometros = totalKilometros;
    }

    public Integer getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(Integer vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public LocalDateTime getDesde() {
        return desde;
    }

    public void setDesde(LocalDateTime desde) {
        this.desde = desde;
    }

    public LocalDateTime getHasta() {
        return hasta;
    }

    public void setHasta(LocalDateTime hasta) {
        this.hasta = hasta;
    }

    public Double getTotalKilometros() {
        return totalKilometros;
    }

    public void setTotalKilometros(Double totalKilometros) {
        this.totalKilometros = totalKilometros;
    }
}
