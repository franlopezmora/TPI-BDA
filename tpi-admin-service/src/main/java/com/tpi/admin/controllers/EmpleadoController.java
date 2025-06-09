package com.tpi.admin.controllers;

import com.tpi.admin.entities.Empleado;
import com.tpi.admin.repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @GetMapping
    public List<Empleado> getAll() {
        return empleadoRepository.findAll();
    }

    @PostMapping
    public Empleado create(@RequestBody Empleado empleado) {
        return empleadoRepository.save(empleado);
    }
}
