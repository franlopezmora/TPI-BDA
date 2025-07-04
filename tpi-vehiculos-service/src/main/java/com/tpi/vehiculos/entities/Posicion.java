package com.tpi.vehiculos.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Posiciones")
public class Posicion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "id_vehiculo",
            nullable = false,
            foreignKey = @ForeignKey(name = "Posiciones_Vehiculos_FK")
    )
    private Vehiculo vehiculo;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    @Column(name = "latitud",nullable = false)
    private Double latitud;

    @Column(name = "longitud",nullable = false)
    private Double longitud;

    // Getters y setters


    @Override
    public String toString() {
        return "Posicion{" +
                "id=" + id +
                ", vehiculo=" + vehiculo +
                ", fechaHora=" + fechaHora +
                ", latitud=" + latitud +
                ", longitud=" + longitud +
                '}';
    }

    public Posicion (Long id, Vehiculo vehiculo, LocalDateTime fechaHora, Double latitud, Double longitud) {
        this.id = id;
        this.vehiculo = vehiculo;
        this.fechaHora = fechaHora;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Posicion () {
    }

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

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }
}
