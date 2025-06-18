package com.tpi.reportes.dtos;

public class VehiculoDTO {
    private Integer id;
    private String patente;

    public VehiculoDTO() {
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
}
