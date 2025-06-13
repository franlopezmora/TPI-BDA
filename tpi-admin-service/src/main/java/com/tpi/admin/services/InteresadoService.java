package com.tpi.admin.services;

import com.tpi.admin.entities.Interesado;
import com.tpi.admin.repositories.InteresadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InteresadoService {

    @Autowired
    private InteresadoRepository interesadoRepository;

    public List<Interesado> listarInteresados() {
        return interesadoRepository.findAll();
    }

    public Interesado crearInteresado(Interesado interesado) {
        return interesadoRepository.save(interesado);
    }

    public Optional<Interesado> obtenerInteresadoPorId(Long id) {
        return interesadoRepository.findById(id);
    }

    public Interesado actualizarInteresado(Long id, Interesado actualizado) {
        Interesado existente = interesadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Interesado no encontrado con ID: " + id));

        existente.setTipoDocumento(actualizado.getTipoDocumento());
        existente.setDocumento(actualizado.getDocumento());
        existente.setNombre(actualizado.getNombre());
        existente.setApellido(actualizado.getApellido());
        existente.setRestringido(actualizado.getRestringido());
        existente.setNroLicencia(actualizado.getNroLicencia());
        existente.setFechaVencimientoLicencia(actualizado.getFechaVencimientoLicencia());

        return interesadoRepository.save(existente);
    }

    public void eliminarInteresado(Long id) {
        interesadoRepository.deleteById(id);
    }
}
