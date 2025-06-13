package com.tpi.reportes.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Incidentes")
public class Incidente {

    @EmbeddedId
    private IncidenteId incidenteId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "ID_TIPO_INCIDENTE",
            nullable = false,
            foreignKey = @ForeignKey(name = "Incidente_TipoIncidente_FK")
    )
    private TipoIncidente tipoIncidente;

    @Column(name = "FECHA_HORA", nullable = false)
    private LocalDateTime fechaHora;

    public Incidente() { }

    public Incidente(IncidenteId incidenteId,
                     TipoIncidente tipoIncidente,
                     LocalDateTime fechaHora) {
        this.incidenteId = incidenteId;
        this.tipoIncidente = tipoIncidente;
        this.fechaHora = fechaHora;
    }

    @Override
    public String toString() {
        return "Incidente{" +
                "incidenteId=" + incidenteId +
                ", tipoIncidente=" + tipoIncidente +
                ", fechaHora=" + fechaHora +
                '}';
    }

    public IncidenteId getIncidenteId() {
        return incidenteId;
    }

    public void setIncidenteId(IncidenteId incidenteId) {
        this.incidenteId = incidenteId;
    }

    public TipoIncidente getTipoIncidente() {
        return tipoIncidente;
    }

    public void setTipoIncidente(TipoIncidente tipoIncidente) {
        this.tipoIncidente = tipoIncidente;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
}
