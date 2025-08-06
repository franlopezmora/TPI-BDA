package com.tpi.vehiculos.dtos;

public class VehiculoDTO {
    private Long id;
    private String patente;
    private Integer anio;
    private Long idModelo;
    private Boolean activo;

    public VehiculoDTO() {}

    public VehiculoDTO(Long id, String patente, Integer anio, Long idModelo, Boolean activo) {
        this.id = id;
        this.patente = patente;
        this.anio = anio;
        this.idModelo = idModelo;
        this.activo   = activo;
    }

    public VehiculoDTO(Long id, String patente, Integer anio, Long idModelo) {
        this(id, patente, anio, idModelo, true);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPatente() { return patente; }
    public void setPatente(String patente) { this.patente = patente; }

    public Integer getAnio() { return anio; }
    public void setAnio(Integer anio) { this.anio = anio; }

    public Long getIdModelo() { return idModelo; }
    public void setIdModelo(Long idModelo) { this.idModelo = idModelo; }

    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
}