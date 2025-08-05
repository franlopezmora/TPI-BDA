// InteresadoDTO.java
package com.tpi.admin.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public class InteresadoDTO {
    private Long id;

    @NotBlank @Size(max = 3)
    private String tipoDocumento;

    @NotBlank @Size(max = 10)
    private String documento;

    @NotBlank @Size(max = 50)
    private String nombre;

    @NotBlank @Size(max = 50)
    private String apellido;

    private Boolean restringido = false;
    private Integer nroLicencia;
    private LocalDate fechaVencimientoLicencia;

    // getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTipoDocumento() { return tipoDocumento; }
    public void setTipoDocumento(String tipoDocumento) { this.tipoDocumento = tipoDocumento; }
    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public Boolean getRestringido() { return restringido; }
    public void setRestringido(Boolean restringido) { this.restringido = restringido; }
    public Integer getNroLicencia() { return nroLicencia; }
    public void setNroLicencia(Integer nroLicencia) { this.nroLicencia = nroLicencia; }
    public LocalDate getFechaVencimientoLicencia() { return fechaVencimientoLicencia; }
    public void setFechaVencimientoLicencia(LocalDate fechaVencimientoLicencia) { this.fechaVencimientoLicencia = fechaVencimientoLicencia; }
}
