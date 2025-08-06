package com.tpi.admin.entities;

import com.tpi.admin.utils.BooleanIntegerConverter;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDate;

@Entity
@Table(name = "Interesados")
@SQLDelete(sql  = "UPDATE interesados SET activo = false WHERE id = ?")
@Where(clause = "activo = true")
public class Interesado {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TIPO_DOCUMENTO", nullable = false, length = 3)
    private String tipoDocumento;

    @Column(name = "DOCUMENTO", nullable = false, length = 10)
    private String documento;

    @Column(name = "NOMBRE", nullable = false, length = 50)
    private String nombre;

    @Column(name = "APELLIDO", nullable = false, length = 50)
    private String apellido;

    @Column(name = "RESTRINGIDO")
    @Convert(converter = BooleanIntegerConverter.class)
    private Boolean restringido = false;

    @Column(name = "NRO_LICENCIA")
    private Integer nroLicencia;

    @Column(name = "FECHA_VENCIMIENTO_LICENCIA")
    private LocalDate fechaVencimientoLicencia;

    @Column(nullable = false)
    private Boolean activo = true;

    public Interesado() {
    }

    @Override
    public String toString() {
        return "Interesado{" +
                "id=" + id +
                ", tipoDocumento='" + tipoDocumento + '\'' +
                ", documento='" + documento + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", restringido=" + restringido +
                ", nroLicencia=" + nroLicencia +
                ", fechaVencimientoLicencia=" + fechaVencimientoLicencia +
                ", activo=" + activo +
                '}';
    }

    public Interesado(Long id, String tipoDocumento, String documento, String nombre, String apellido, Boolean restringido, Integer nroLicencia, LocalDate fechaVencimientoLicencia, Boolean activo) {
        this.id = id;
        this.tipoDocumento = tipoDocumento;
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.restringido = restringido;
        this.nroLicencia = nroLicencia;
        this.fechaVencimientoLicencia = fechaVencimientoLicencia;
        this.activo = activo;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
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

    public Boolean getRestringido() {
        return restringido;
    }

    public void setRestringido(Boolean restringido) {
        this.restringido = restringido;
    }

    public Integer getNroLicencia() {
        return nroLicencia;
    }

    public void setNroLicencia(Integer nroLicencia) {
        this.nroLicencia = nroLicencia;
    }

    public LocalDate getFechaVencimientoLicencia() {
        return fechaVencimientoLicencia;
    }

    public void setFechaVencimientoLicencia(LocalDate fechaVencimientoLicencia) {
        this.fechaVencimientoLicencia = fechaVencimientoLicencia;
    }
}
