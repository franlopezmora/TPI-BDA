package com.tpi.pruebas.dtos;

import java.time.LocalDateTime;

public class PruebaDTO {
    private Long id;
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;
    private String comentario;
    private Long idEmpleado;
    private Long idInteresado;
    private Long idVehiculo;

    // Constructor vacío
    public PruebaDTO() {}

    // Constructor completo
    public PruebaDTO(Long id, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin, String comentario, Long idEmpleado, Long idInteresado, Long idVehiculo) {
        this.id = id;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.comentario = comentario;
        this.idEmpleado = idEmpleado;
        this.idInteresado = idInteresado;
        this.idVehiculo = idVehiculo;
    }

    // Getters y Setters...

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
