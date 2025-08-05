package com.tpi.pruebas.dtos;
import com.tpi.pruebas.clients.VehiculoClient;
import com.tpi.pruebas.dtos.EmpleadoDTO;
import com.tpi.pruebas.dtos.InteresadoDTO;
import com.tpi.pruebas.dtos.VehiculoDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class PruebaDTO {
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime fechaHoraInicio;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime fechaHoraFin;
    private String comentario;


    private EmpleadoDTO empleado;
    private InteresadoDTO interesado;
    private VehiculoDTO vehiculo;
    public PruebaDTO(){}

    public PruebaDTO(Long id, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin, String comentario, EmpleadoDTO empleado, InteresadoDTO interesado, VehiculoDTO vehiculo) {
        this.id = id;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.comentario = comentario;
        this.empleado = empleado;
        this.interesado = interesado;
        this.vehiculo = vehiculo;
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

    public EmpleadoDTO getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadoDTO empleado) {
        this.empleado = empleado;
    }

    public InteresadoDTO getInteresado() {
        return interesado;
    }

    public void setInteresado(InteresadoDTO interesado) {
        this.interesado = interesado;
    }

    public VehiculoDTO getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(VehiculoDTO vehiculo) {
        this.vehiculo = vehiculo;
    }
}