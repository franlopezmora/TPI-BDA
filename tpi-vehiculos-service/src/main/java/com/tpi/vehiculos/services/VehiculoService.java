package com.tpi.vehiculos.services;

import com.tpi.vehiculos.dtos.VehiculoDTO;
import com.tpi.vehiculos.entities.Modelo;
import com.tpi.vehiculos.entities.Vehiculo;
import com.tpi.vehiculos.repositories.ModeloRepository;
import com.tpi.vehiculos.repositories.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculoService {

    private final VehiculoRepository vehiculoRepository;

    public VehiculoService(VehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    public List<Vehiculo> listar() {
        return vehiculoRepository.findAll();
    }

    public Optional<Vehiculo> obtenerPorId(Long id) {
        return vehiculoRepository.findById(id);
    }

    public Vehiculo crear(Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    public Optional<Vehiculo> actualizar(Long id, Vehiculo vehiculo) {
        return vehiculoRepository.findById(id)
                .map(v -> {
                    v.setPatente(vehiculo.getPatente());
                    v.setAnio(vehiculo.getAnio());
                    v.setModelo(vehiculo.getModelo());
                    return vehiculoRepository.save(v);
                });
    }

    public boolean eliminar(Long id) {
        if (vehiculoRepository.existsById(id)) {
            vehiculoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Vehiculo> buscarPorPatente(String patente) {
        return vehiculoRepository.findByPatente(patente);
    }

    public List<Vehiculo> buscarPorAnio(Integer anio) {
        return vehiculoRepository.findByAnio(anio);
    }

    public List<Vehiculo> buscarPorModeloId(Long idModelo) {
        return vehiculoRepository.findByModeloId(idModelo);
    }

    @Autowired
    private ModeloRepository modeloRepository;

    public VehiculoDTO toDTO(Vehiculo vehiculo) {
        return new VehiculoDTO(
                vehiculo.getId().longValue(),
                vehiculo.getPatente(),
                vehiculo.getAnio(),
                vehiculo.getModelo() != null ? vehiculo.getModelo().getId().longValue() : null
        );
    }

    public Vehiculo fromDTO(VehiculoDTO dto) {
        Modelo modelo = modeloRepository.findById(dto.getIdModelo())
                .orElseThrow(() -> new IllegalArgumentException("Modelo no encontrado"));

        Vehiculo v = new Vehiculo();
        v.setId(dto.getId() != null ? dto.getId().intValue() : null);
        v.setPatente(dto.getPatente());
        v.setAnio(dto.getAnio());
        v.setModelo(modelo);
        return v;
    }


}
