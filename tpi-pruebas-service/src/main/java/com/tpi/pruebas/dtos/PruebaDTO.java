package com.tpi.pruebas.dtos;
import com.tpi.pruebas.clients.VehiculoClient;
import com.tpi.pruebas.dtos.EmpleadoDTO;
import com.tpi.pruebas.dtos.InteresadoDTO;
import com.tpi.pruebas.dtos.VehiculoDTO;

import java.time.LocalDateTime;

public class PruebaDTO {
    private Long id;
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;
    private String comentario;


    private EmpleadoDTO emplado;
    private InteresadoDTO interesado;
    private VehiculoDTO vehiculo;
    public PruebaDTO(){}

    public PruebaDTO(Long id, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin, String comentario, EmpleadoDTO emplado, InteresadoDTO interesado, VehiculoDTO vehiculo) {
        this.id = id;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.comentario = comentario;
        this.emplado = emplado;
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

    public EmpleadoDTO getEmplado() {
        return emplado;
    }

    public void setEmpleado(EmpleadoDTO emplado) {
        this.emplado = emplado;
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