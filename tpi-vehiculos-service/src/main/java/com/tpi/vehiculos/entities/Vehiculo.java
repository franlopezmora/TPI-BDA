package com.tpi.vehiculos.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Vehiculos")

public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "PATENTE", unique = true)
    private String patente;

    @Column(name = "ANIO")
    private int anio;

    @ManyToOne
    @JoinColumn(name = "ID_MODELO", nullable = false)
    private Modelo modelo;

}