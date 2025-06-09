package com.tpi.vehiculos.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "Zonas_Peligrosas")
public class ZonaPeligrosa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @Column(name = "LAT_NOROESTE", nullable = false)
    private Double latNoroeste;

    @Column(name = "LON_NOROESTE", nullable = false)
    private Double lonNoroeste;

    @Column(name = "LAT_SURESTE", nullable = false)
    private Double latSureste;

    @Column(name = "LON_SURESTE", nullable = false)
    private Double lonSureste;

    public ZonaPeligrosa () {
    }

    public ZonaPeligrosa (Integer id, String nombre, Double latNoroeste, Double lonNoroeste, Double latSureste, Double lonSureste) {
        this.id = id;
        this.nombre = nombre;
        this.latNoroeste = latNoroeste;
        this.lonNoroeste = lonNoroeste;
        this.latSureste = latSureste;
        this.lonSureste = lonSureste;
    }

    @Override
    public String toString() {
        return "ZonaPeligrosa{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", latNoroeste=" + latNoroeste +
                ", lonNoroeste=" + lonNoroeste +
                ", latSureste=" + latSureste +
                ", lonSureste=" + lonSureste +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getLatNoroeste() {
        return latNoroeste;
    }

    public void setLatNoroeste(Double latNoroeste) {
        this.latNoroeste = latNoroeste;
    }

    public Double getLonNoroeste() {
        return lonNoroeste;
    }

    public void setLonNoroeste(Double lonNoroeste) {
        this.lonNoroeste = lonNoroeste;
    }

    public Double getLatSureste() {
        return latSureste;
    }

    public void setLatSureste(Double latSureste) {
        this.latSureste = latSureste;
    }

    public Double getLonSureste() {
        return lonSureste;
    }

    public void setLonSureste(Double lonSureste) {
        this.lonSureste = lonSureste;
    }


}
