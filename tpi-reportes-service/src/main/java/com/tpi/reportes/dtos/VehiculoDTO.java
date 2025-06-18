package com.tpi.reportes.dtos;

public class VehiculoDTO {
    private Long id;
    private String patente;
    private Integer anio;
    private Long idModelo;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPatente() { return patente; }
    public void setPatente(String patente) { this.patente = patente; }

    public Integer getAnio() { return anio; }
    public void setAnio(Integer anio) { this.anio = anio; }

    public Long getIdModelo() { return idModelo; }
    public void setIdModelo(Long idModelo) { this.idModelo = idModelo; }
}
