package com.tpi.agencia.tpibda.Entities;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "VEHICULO")
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String patente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_MODELO", nullable = false)
    private Modelo modelo;

    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Posicion> posiciones = new ArrayList<>();

    public Vehiculo() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public List<Posicion> getPosiciones() {
        return posiciones;
    }

    public void setPosiciones(List<Posicion> posiciones) {
        this.posiciones = posiciones;
    }

    public void addPosicion(Posicion posicion) {
        posiciones.add(posicion);
        posicion.setVehiculo(this);
    }

    public void removePosicion(Posicion posicion) {
        posiciones.remove(posicion);
        posicion.setVehiculo(null);
    }
}
