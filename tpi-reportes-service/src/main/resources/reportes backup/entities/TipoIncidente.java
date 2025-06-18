package com.tpi.reportes.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Tipos_Incidente")
public class TipoIncidente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOMBRE_INCIDENTE", nullable = false, length = 255)
    private String nombre;

    @OneToMany(mappedBy = "tipoIncidente", fetch = FetchType.LAZY)
    private List<Incidente> incidentes = new ArrayList<>();

    public TipoIncidente() { }

    public TipoIncidente(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "TipoIncidente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Incidente> getIncidentes() {
        return incidentes;
    }

    public void setIncidentes(List<Incidente> incidentes) {
        this.incidentes = incidentes;
    }
}
