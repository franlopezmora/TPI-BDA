package com.tpi.reportes.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class IncidenteId implements Serializable {

    @Column(name = "ID_PRUEBA")
    private Long idPrueba;

    @Column(name = "NRO_INCIDENTE")
    private Integer nroIncidente;

    public IncidenteId() { }

    public IncidenteId(Long idPrueba, Integer nroIncidente) {
        this.idPrueba = idPrueba;
        this.nroIncidente = nroIncidente;
    }

    @Override
    public String toString() {
        return "IncidenteId{" +
                "idPrueba=" + idPrueba +
                ", nroIncidente=" + nroIncidente +
                '}';
    }

    public Long getIdPrueba() {
        return idPrueba;
    }

    public void setIdPrueba(Long idPrueba) {
        this.idPrueba = idPrueba;
    }

    public Integer getNroIncidente() {
        return nroIncidente;
    }

    public void setNroIncidente(Integer nroIncidente) {
        this.nroIncidente = nroIncidente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IncidenteId)) return false;
        IncidenteId that = (IncidenteId) o;
        return Objects.equals(idPrueba, that.idPrueba) &&
                Objects.equals(nroIncidente, that.nroIncidente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPrueba, nroIncidente);
    }
}
