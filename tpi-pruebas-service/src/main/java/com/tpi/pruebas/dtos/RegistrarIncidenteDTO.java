package com.tpi.pruebas.dtos;

public class RegistrarIncidenteDTO {
    private Long idPrueba;
    private String nombreTipoIncidente;

    public Long getIdPrueba() {
        return idPrueba;
    }

    public void setIdPrueba(Long idPrueba) {
        this.idPrueba = idPrueba;
    }

    public String getNombreTipoIncidente() {
        return nombreTipoIncidente;
    }

    public void setNombreTipoIncidente(String nombreTipoIncidente) {
        this.nombreTipoIncidente = nombreTipoIncidente;
    }
}
