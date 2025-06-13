package com.tpi.vehiculos.dtos;

public class ZonaPeligrosaDTO {
    private Long id;
    private String nombre;
    private Double latNoroeste;
    private Double lonNoroeste;
    private Double latSureste;
    private Double lonSureste;

    public ZonaPeligrosaDTO() {}

    public ZonaPeligrosaDTO(Long id, String nombre, Double latNoroeste, Double lonNoroeste, Double latSureste, Double lonSureste) {
        this.id = id;
        this.nombre = nombre;
        this.latNoroeste = latNoroeste;
        this.lonNoroeste = lonNoroeste;
        this.latSureste = latSureste;
        this.lonSureste = lonSureste;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Double getLatNoroeste() { return latNoroeste; }
    public void setLatNoroeste(Double latNoroeste) { this.latNoroeste = latNoroeste; }

    public Double getLonNoroeste() { return lonNoroeste; }
    public void setLonNoroeste(Double lonNoroeste) { this.lonNoroeste = lonNoroeste; }

    public Double getLatSureste() { return latSureste; }
    public void setLatSureste(Double latSureste) { this.latSureste = latSureste; }

    public Double getLonSureste() { return lonSureste; }
    public void setLonSureste(Double lonSureste) { this.lonSureste = lonSureste; }
}
