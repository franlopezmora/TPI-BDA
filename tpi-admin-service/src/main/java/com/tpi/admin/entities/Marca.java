package com.tpi.admin.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Marcas")
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "marca")
    private List<Modelo> modelos = new ArrayList<>();

    public Marca() {
        // Constructor vacío para JPA
    }

    public Marca(Integer id, String nombre, List<Modelo> modelos) {
        this.id = id;
        this.nombre = nombre;
        this.modelos = modelos;
    }

    @Override
    public String toString() {
        return "Marca{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", modelos=" + modelos +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Modelo> getModelos() {
        return modelos;
    }

    public void setModelos(List<Modelo> modelos) {
        this.modelos = modelos;
    }
}
