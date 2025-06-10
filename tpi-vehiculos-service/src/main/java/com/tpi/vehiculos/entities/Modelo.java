package com.tpi.vehiculos.entities;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Modelos")
public class Modelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "id_marca",
            nullable = false,
            foreignKey = @ForeignKey(name = "Modelos_Marcas_FK")
    )
    @JsonIgnoreProperties({"modelos", "hibernateLazyInitializer", "handler"})
    private Marca marca;



    @OneToMany(mappedBy = "modelo", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Vehiculo> vehiculos = new ArrayList<>();

    public Modelo () {}

    public Modelo (Integer id, String descripcion, Marca marca, List<Vehiculo> vehiculos) {
        this.id = id;
        this.descripcion = descripcion;
        this.marca = marca;
        this.vehiculos = vehiculos;
    }

    @Override
    public String toString() {
        return "Modelo{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", marca=" + marca +
                ", vehiculos=" + vehiculos +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }
}
