package com.tpi.admin.backup.entities;



import com.tpi.admin.entities.Marca;
import com.tpi.admin.entities.Vehiculo;
import jakarta.persistence.*;
import com.tpi.admin.entities.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Modelos")
public class Modelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "ID_MARCA",
            nullable = false,
            foreignKey = @ForeignKey(name = "Modelos_Marcas_FK")
    )
    private com.tpi.admin.entities.Marca marca;


    @OneToMany(mappedBy = "modelo")
    private List<com.tpi.admin.entities.Vehiculo> vehiculos = new ArrayList<>();

    public Modelo() {}

    public Modelo(Integer id, String descripcion, Marca marca, List<com.tpi.admin.entities.Vehiculo> vehiculos) {
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
