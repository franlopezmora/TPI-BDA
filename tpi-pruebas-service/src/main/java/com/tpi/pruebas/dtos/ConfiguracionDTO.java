package com.tpi.pruebas.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ConfiguracionDTO {
    @JsonProperty("ubicacion_agencia")
    private CoordenadaDTO ubicacionAgencia;

    @JsonProperty("radio_maximo_metros")
    private Integer radioMaximoMetros;

    @JsonProperty("zonas_peligrosas")
    private List<ZonaPeligrosaDTO> zonasPeligrosas;


    // Getters y setters

    public ConfiguracionDTO() {}

    public CoordenadaDTO getUbicacionAgencia() {
        return ubicacionAgencia;
    }

    public void setUbicacionAgencia(CoordenadaDTO ubicacionAgencia) {
        this.ubicacionAgencia = ubicacionAgencia;
    }

    public Integer getRadioMaximoMetros() {
        return radioMaximoMetros;
    }

    public void setRadioMaximoMetros(Integer radioMaximoMetros) {
        this.radioMaximoMetros = radioMaximoMetros;
    }

    public List<ZonaPeligrosaDTO> getZonasPeligrosas() {
        return zonasPeligrosas;
    }

    public void setZonasPeligrosas(List<ZonaPeligrosaDTO> zonasPeligrosas) {
        this.zonasPeligrosas = zonasPeligrosas;
    }
}
