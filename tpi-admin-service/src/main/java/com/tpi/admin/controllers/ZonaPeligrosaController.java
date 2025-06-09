package com.tpi.admin.controllers;

import com.tpi.admin.entities.ZonaPeligrosa;
import com.tpi.admin.repositories.ZonaPeligrosaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zonas-peligrosas")
public class ZonaPeligrosaController {

    @Autowired
    private ZonaPeligrosaRepository zonaPeligrosaRepository;

    @GetMapping
    public List<ZonaPeligrosa> getAll() {
        return zonaPeligrosaRepository.findAll();
    }

    @PostMapping
    public ZonaPeligrosa create(@RequestBody ZonaPeligrosa zona) {
        return zonaPeligrosaRepository.save(zona);
    }
}