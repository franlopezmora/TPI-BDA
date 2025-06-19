package com.tpi.pruebas.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "incidentes")
public class Incidente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_incidente", nullable = false)
    private TipoIncidente tipoIncidente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_prueba", nullable = false)
    private Prueba prueba;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoIncidente getTipoIncidente() {
        return tipoIncidente;
    }

    public void setTipoIncidente(TipoIncidente tipoIncidente) {
        this.tipoIncidente = tipoIncidente;
    }

    public Prueba getPrueba() {
        return prueba;
    }

    public void setPrueba(Prueba prueba) {
        this.prueba = prueba;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
}
