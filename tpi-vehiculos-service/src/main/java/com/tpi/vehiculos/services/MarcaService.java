package com.tpi.vehiculos.services;

import com.tpi.vehiculos.dtos.MarcaDTO;
import com.tpi.vehiculos.entities.Marca;
import com.tpi.vehiculos.repositories.MarcaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarcaService {

    private final MarcaRepository marcaRepository;

    public MarcaService(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    public List<Marca> listar() {
        return marcaRepository.findAll();
    }

    public Optional<Marca> obtenerPorId(Long id) {
        return marcaRepository.findById(id);
    }

    public Marca crear(Marca marca) {
        return marcaRepository.save(marca);
    }

    public Optional<Marca> actualizar(Long id, Marca marca) {
        return marcaRepository.findById(id)
                .map(m -> {
                    m.setNombre(marca.getNombre());
                    return marcaRepository.save(m);
                });
    }

    public boolean eliminar(Long id) {
        if (marcaRepository.existsById(id)) {
            marcaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Marca> buscarPorNombre(String nombre) {
        return marcaRepository.findByNombre(nombre);
    }

    public List<Marca> buscarPorNombreParcial(String nombre) {
        return marcaRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public List<MarcaDTO> listarDTO() {
        return marcaRepository.findAll().stream().map(this::toDTO).toList();
    }

    public MarcaDTO toDTO(Marca marca) {
        return new MarcaDTO(
                marca.getId().longValue(),
                marca.getNombre()
        );
    }

    public Marca fromDTO(MarcaDTO dto) {
        Marca marca = new Marca();
        if (dto.getId() != null) {
            marca.setId(dto.getId().intValue());
        }
        marca.setNombre(dto.getNombre());
        return marca;
    }

}