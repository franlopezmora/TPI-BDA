package com.tpi.admin.services;

import com.tpi.admin.dtos.InteresadoDTO;
import com.tpi.admin.entities.Interesado;
import com.tpi.admin.repositories.InteresadoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InteresadoService {

    private final InteresadoRepository repo;

    public InteresadoService(InteresadoRepository repo) {
        this.repo = repo;
    }

    public List<InteresadoDTO> listarInteresados() {
        return repo.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public InteresadoDTO crearInteresado(InteresadoDTO dto) {
        Interesado entidad = toEntity(dto);
        Interesado saved = repo.save(entidad);
        return toDto(saved);
    }

    public Optional<InteresadoDTO> obtenerInteresadoPorId(Long id) {
        return repo.findById(id).map(this::toDto);
    }

    public InteresadoDTO actualizarInteresado(Long id, InteresadoDTO dto) {
        return repo.findById(id)
                .map(existing -> {
                    existing.setTipoDocumento(dto.getTipoDocumento());
                    existing.setDocumento(dto.getDocumento());
                    existing.setNombre(dto.getNombre());
                    existing.setApellido(dto.getApellido());
                    existing.setRestringido(dto.getRestringido());
                    existing.setNroLicencia(dto.getNroLicencia());
                    existing.setFechaVencimientoLicencia(dto.getFechaVencimientoLicencia());
                    return toDto(repo.save(existing));
                })
                .orElseThrow(() -> new EntityNotFoundException("Interesado no encontrado"));
    }

    public void eliminarInteresado(Long id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Interesado no encontrado");
        }
        repo.deleteById(id);
    }

    public void restringirInteresado(Long id) {
        Interesado i = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Interesado no encontrado"));
        i.setRestringido(true);
        repo.save(i);
    }

    public List<InteresadoDTO> listarRestringidos() {
        // aquí sí llamamos al método específico
        return repo.findByRestringidoTrue().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private Interesado toEntity(InteresadoDTO dto) {
        Interesado e = new Interesado();
        e.setId(dto.getId());
        e.setTipoDocumento(dto.getTipoDocumento());
        e.setDocumento(dto.getDocumento());
        e.setNombre(dto.getNombre());
        e.setApellido(dto.getApellido());
        e.setRestringido(dto.getRestringido());
        e.setNroLicencia(dto.getNroLicencia());
        e.setFechaVencimientoLicencia(dto.getFechaVencimientoLicencia());
        return e;
    }

    private InteresadoDTO toDto(Interesado e) {
        InteresadoDTO dto = new InteresadoDTO();
        dto.setId(e.getId());
        dto.setTipoDocumento(e.getTipoDocumento());
        dto.setDocumento(e.getDocumento());
        dto.setNombre(e.getNombre());
        dto.setApellido(e.getApellido());
        dto.setRestringido(e.getRestringido());
        dto.setNroLicencia(e.getNroLicencia());
        dto.setFechaVencimientoLicencia(e.getFechaVencimientoLicencia());
        return dto;
    }
}
