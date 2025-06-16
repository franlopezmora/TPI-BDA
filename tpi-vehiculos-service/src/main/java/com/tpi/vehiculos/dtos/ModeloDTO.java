package com.tpi.vehiculos.dtos;

public class ModeloDTO {
    private Long id;
    private String descripcion;
    private Long idMarca;

    public ModeloDTO() {}

    public ModeloDTO(Long id, String descripcion, Long idMarca) {
        this.id = id;
        this.descripcion = descripcion;
        this.idMarca = idMarca;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Long getIdMarca() { return idMarca; }
    public void setIdMarca(Long idMarca) { this.idMarca = idMarca; }
}
