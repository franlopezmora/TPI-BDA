package com.tpi.pruebas.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "Pruebas")
public class Prueba {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FECHA_HORA_INICIO", nullable = false)
    private LocalDateTime fechaHoraInicio;

    @Column(name = "FECHA_HORA_FIN")
    private LocalDateTime fechaHoraFin;

    @Column(name = "COMENTARIOS")
    private String comentario;

    @ManyToOne
    @JoinColumn(
            name = "ID_EMPLEADO",
            foreignKey = @ForeignKey(name = "Pruebas_Empleados_FK")
    )
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(
            name = "ID_INTERESADO",
            foreignKey = @ForeignKey(name = "Pruebas_Interesados_FK")
    )
    private Interesado interesado;

    @ManyToOne
    @JoinColumn(
            name = "ID_VEHICULO",
            foreignKey = @ForeignKey(name = "Pruebas_Vehiculos_FK")
    )
    private Vehiculo vehiculo;

    @OneToMany(mappedBy = "prueba")
    @JsonIgnore
    private List<Notificacion> notificaciones = new ArrayList<>();

    public Prueba() {
    }

    public Prueba(Long id, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin, String comentario, Empleado empleado, Interesado interesado, Vehiculo vehiculo, List<Notificacion> notificaciones) {
        this.id = id;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.comentario = comentario;
        this.empleado = empleado;
        this.interesado = interesado;
        this.vehiculo = vehiculo;
        this.notificaciones = notificaciones;
    }

    @Override
    public String toString() {
        return "Prueba{" +
                "id=" + id +
                ", fechaHoraInicio=" + fechaHoraInicio +
                ", fechaHoraFin=" + fechaHoraFin +
                ", comentario='" + comentario + '\'' +
                ", empleado=" + empleado +
                ", interesado=" + interesado +
                ", vehiculo=" + vehiculo +
                ", notificaciones=" + notificaciones +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public LocalDateTime getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Interesado getInteresado() {
        return interesado;
    }

    public void setInteresado(Interesado interesado) {
        this.interesado = interesado;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public List<Notificacion> getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(List<Notificacion> notificaciones) {
        this.notificaciones = notificaciones;
    }
}
