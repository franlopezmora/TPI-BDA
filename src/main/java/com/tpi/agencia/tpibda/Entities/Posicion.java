package com.tpi.agencia.tpibda.Entities;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "POSICIONES")
public class Posicion {
    public Posicion (){};
    @Id
    private Long id;
    @ManyToOne
    @Column(name = "ID_VEHICULO")
    private Vehiculo vehiculo;
    @Column(name = "FECHA_HORA", nullable = false)
    private LocalDateTime fechaHora;
    @Column(nullable = false)
    private Double latitude;
    @Column(nullable = false)
    private Double longitud;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Posicion(Long id, LocalDateTime fechaHora, Vehiculo vehiculo, Double longitud, Double latitude) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.vehiculo = vehiculo;
        this.longitud = longitud;
        this.latitude = latitude;
    }
}
