package com.tpi.pruebas.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Pruebas")
public class Prueba {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")

    private Long Id;
    @Column(name = "FECHA_HORA_INICIO", nullable = false)
    private LocalDateTime fechaHoraInicio;
    @Column(name = "FECHA_HORA_FIN", nullable = false)
    private LocalDateTime fechaHoraFin;
    @Column(name="COMENTARIOS")
    private String comentarios;
    @Column(name="ID_EMPLEADO", nullable = false)
    private Long idEmpleado;
    @Column(name = "ID_INTERESADO", nullable = false)
    private Long idInteresado;
    @Column(name = "ID_VEHICULO", nullable = false)
    private Long idVehiculo;

    public Prueba(Long id, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin, String comentarios, Long idEmpleado, Long idInteresado, Long idVehiculo) {
        Id = id;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.comentarios = comentarios;
        this.idEmpleado = idEmpleado;
        this.idInteresado = idInteresado;
        this.idVehiculo = idVehiculo;
    }
    public Prueba (){}
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Long getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Long getIdInteresado() {
        return idInteresado;
    }

    public void setIdInteresado(Long idInteresado) {
        this.idInteresado = idInteresado;
    }

    public Long getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Long idVehiculo) {
        this.idVehiculo = idVehiculo;
    }
}
