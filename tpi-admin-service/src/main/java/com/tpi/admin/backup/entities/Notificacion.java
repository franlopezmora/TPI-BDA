package com.tpi.admin.entities;



import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Notificaciones")
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "MENSAJE", nullable = false)
    private String mensaje;

    @Column(name = "TIPO", nullable = false)
    private String tipo;

    @Column(name = "TELEFONO", nullable = false)
    private String telefono;

    @Column(name = "FECHA", nullable = false)
    private LocalDateTime fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "ID_PRUEBA",
            nullable = false,
            foreignKey = @ForeignKey(name = "Notificaciones_Pruebas_FK")
    )
    private Prueba prueba;


    @Column(name = "TIPO_INCIDENTE")
    private String tipoIncidente;

    public Notificacion() {
        // Constructor vacío para JPA
    }

    public Notificacion(Integer id, String mensaje, String tipo, String telefono, LocalDateTime fecha, Prueba prueba, String tipoIncidente) {
        this.id = id;
        this.mensaje = mensaje;
        this.tipo = tipo;
        this.telefono = telefono;
        this.fecha = fecha;
        this.prueba = prueba;
        this.tipoIncidente = tipoIncidente;
    }

    // --- Getters y Setters ---


    @Override
    public String toString() {
        return "Notificacion{" +
                "id=" + id +
                ", mensaje='" + mensaje + '\'' +
                ", tipo='" + tipo + '\'' +
                ", telefono='" + telefono + '\'' +
                ", fecha=" + fecha +
                ", prueba=" + prueba +
                ", tipoIncidente='" + tipoIncidente + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Prueba getPrueba() {
        return prueba;
    }

    public void setPrueba(Prueba prueba) {
        this.prueba = prueba;
    }

    public String getTipoIncidente() {
        return tipoIncidente;
    }

    public void setTipoIncidente(String tipoIncidente) {
        this.tipoIncidente = tipoIncidente;
    }
}
