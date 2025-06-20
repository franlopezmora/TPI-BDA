package com.tpi.pruebas.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ZonaPeligrosaDTO {

    @JsonProperty("id_zona")
    private String idZona;

    @JsonProperty("nombre_zona")
    private String nombreZona;

    private CoordenadaDTO coordenadas;

    @JsonProperty("radio_metros")
    private Integer radioMetros;

    public ZonaPeligrosaDTO() {}

    public String getIdZona() {
        return idZona;
    }

    public void setIdZona(String idZona) {
        this.idZona = idZona;
    }

    public String getNombreZona() {
        return nombreZona;
    }

    public void setNombreZona(String nombreZona) {
        this.nombreZona = nombreZona;
    }

    public CoordenadaDTO getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(CoordenadaDTO coordenadas) {
        this.coordenadas = coordenadas;
    }

    public Integer getRadioMetros() {
        return radioMetros;
    }

    public void setRadioMetros(Integer radioMetros) {
        this.radioMetros = radioMetros;
    }
}
