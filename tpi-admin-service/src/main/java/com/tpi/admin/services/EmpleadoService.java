package com.tpi.admin.services;

import com.tpi.admin.dtos.EmpleadoDTO;
import com.tpi.admin.entities.Empleado;
import com.tpi.admin.repositories.EmpleadoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    public List<EmpleadoDTO> listarEmpleados() {
        return empleadoRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public EmpleadoDTO guardarEmpleado(EmpleadoDTO dto) {
        Empleado entidad = toEntity(dto);
        Empleado saved = empleadoRepository.save(entidad);
        return toDto(saved);
    }

    public Optional<EmpleadoDTO> obtenerEmpleadoPorId(Long legajo) {
        return empleadoRepository.findById(legajo)
                .map(this::toDto);
    }

    public EmpleadoDTO actualizarEmpleado(Long legajo, EmpleadoDTO dto) {
        return empleadoRepository.findById(legajo)
                .map(existing -> {
                    // solo los campos mutables
                    existing.setNombre(dto.getNombre());
                    existing.setApellido(dto.getApellido());
                    existing.setTelefonoContacto(dto.getTelefonoContacto());
                    return toDto(empleadoRepository.save(existing));
                })
                .orElseThrow(() -> new EntityNotFoundException(
                        "Empleado no encontrado con legajo: " + legajo
                ));
    }

    public void eliminarEmpleado(Long legajo) {
        empleadoRepository.deleteById(legajo);
    }

    public EmpleadoDTO toDto(Empleado e) {
        EmpleadoDTO dto = new EmpleadoDTO();
        dto.setLegajo(e.getLegajo());
        dto.setNombre(e.getNombre());
        dto.setApellido(e.getApellido());
        dto.setTelefonoContacto(e.getTelefonoContacto());
        return dto;
    }

    private Empleado toEntity(EmpleadoDTO dto) {
        Empleado e = new Empleado();
        e.setLegajo(dto.getLegajo());
        e.setNombre(dto.getNombre());
        e.setApellido(dto.getApellido());
        e.setTelefonoContacto(dto.getTelefonoContacto());
        return e;
    }
}
