package com.tpi.reportes.dtos;

import java.time.LocalDateTime;

public class PruebaDTO {
    private Long id;
    private String comentario;
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;
    private VehiculoDTO vehiculo;
    private EmpleadoDTO empleado;
    private InteresadoDTO interesado;

    public PruebaDTO(){}

    public PruebaDTO(Long id, String comentario, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin, VehiculoDTO vehiculo, EmpleadoDTO empleado, InteresadoDTO interesado) {
        this.id = id;
        this.comentario = comentario;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.vehiculo = vehiculo;
        this.empleado = empleado;
        this.interesado = interesado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
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

    public VehiculoDTO getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(VehiculoDTO vehiculo) {
        this.vehiculo = vehiculo;
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
}
public class VehiculoDTO{
    private Integer id;
    private String patente;
    public VehiculoDTO(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }
}
public class EmpleadoDTO{
    private Long legajo;
    private String nombre;
    private String apellido;

    public EmpleadoDTO(){}

    public Long getLegajo() {
        return legajo;
    }

    public void setLegajo(Long legajo) {
        this.legajo = legajo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getNombreCompleto(){
        return  nombre + " " + apellido;
    }
}
public class InteresadoDTO{
    private String nombre;
    private String apellido;

    public InteresadoDTO(){}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getNombreCompleto(){
        return  nombre + " " + apellido;
    }
}