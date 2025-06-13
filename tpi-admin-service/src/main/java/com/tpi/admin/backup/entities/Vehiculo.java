package com.tpi.admin.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
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
@Table(name = "Vehiculos")
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "PATENTE", nullable = false)
    private String patente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "ID_MODELO",
            nullable = false,
            foreignKey = @jakarta.persistence.ForeignKey(name = "Vehiculos_Modelos_FK")
    )
    private Modelo modelo;

    @Column(name = "ANIO", nullable = false)
    private Integer anio ;  // valor por defecto en la BD

    @OneToMany(mappedBy = "vehiculo")
    @JsonIgnore
    private List<Posicion> posiciones = new ArrayList<>();

    public Vehiculo() {
    }

    public Vehiculo(Integer id, String patente, Modelo modelo, Integer anio, List<Posicion> posiciones) {
        this.id = id;
        this.patente = patente;
        this.modelo = modelo;
        this.anio = anio;
        this.posiciones = posiciones;
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "id=" + id +
                ", patente='" + patente + '\'' +
                ", modelo=" + modelo +
                ", anio=" + anio +
                ", posiciones=" + posiciones +
                '}';
    }

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

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public List<Posicion> getPosiciones() {
        return posiciones;
    }

    public void setPosiciones(List<Posicion> posiciones) {
        this.posiciones = posiciones;
    }
}
