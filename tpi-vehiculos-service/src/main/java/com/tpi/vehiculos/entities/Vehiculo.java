package com.tpi.vehiculos.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Vehiculos")
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "patente", nullable = false)
    private String patente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "id_modelo",
            nullable = false,
            foreignKey = @ForeignKey(name = "Vehiculos_Modelos_FK")
    )
    @JsonIgnoreProperties({"vehiculos", "hibernateLazyInitializer", "handler"})
    private Modelo modelo;

    @Column(name = "anio", nullable = false)
    private Integer anio ;  // valor por defecto en la BD

    @OneToMany(mappedBy = "vehiculo")
    @JsonIgnore
    private List<Posicion> posiciones = new ArrayList<>();

    public Vehiculo () {
    }

    public Vehiculo (Integer id, String patente, Modelo modelo, Integer anio, List<Posicion> posiciones) {
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
