package com.tpi.admin.controllers;

import com.tpi.admin.entities.Interesado;
import com.tpi.admin.repositories.InteresadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interesados")
public class InteresadoController {

    @Autowired
    private InteresadoRepository interesadoRepository;

    @GetMapping
    public List<Interesado> getAll() {
        return interesadoRepository.findAll();
    }

    @PostMapping
    public Interesado create(@RequestBody Interesado interesado) {
        return interesadoRepository.save(interesado);
    }
}
