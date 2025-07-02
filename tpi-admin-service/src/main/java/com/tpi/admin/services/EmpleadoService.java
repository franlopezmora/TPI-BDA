package com.tpi.admin.services;

import com.tpi.admin.entities.Empleado;
import com.tpi.admin.repositories.EmpleadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    public List<Empleado> listarEmpleados() {
        return empleadoRepository.findAll();
    }

    public Empleado guardarEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    public Optional<Empleado> obtenerEmpleadoPorId(Long legajo) {
        return empleadoRepository.findById(legajo);
    }

    public Empleado actualizarEmpleado(Long legajo, Empleado datosActualizados) {
        return empleadoRepository.findById(legajo).map(e -> {
            e.setNombre(datosActualizados.getNombre());
            e.setApellido(datosActualizados.getApellido());
            e.setTelefonoContacto(datosActualizados.getTelefonoContacto());
            return empleadoRepository.save(e);
        }).orElseThrow(() -> new RuntimeException("Empleado no encontrado con legajo: " + legajo));
    }

    public void eliminarEmpleado(Long legajo) {
        empleadoRepository.deleteById(legajo);
    }
}
