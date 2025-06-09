package com.tpi.vehiculos.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Modelos")

public class Modelo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "ID_MARCA", nullable = false)
    private Marca marca;
}