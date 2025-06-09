package com.tpi.vehiculos.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Marcas")

public class Marca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOMBRE")
    private String nombre;
}