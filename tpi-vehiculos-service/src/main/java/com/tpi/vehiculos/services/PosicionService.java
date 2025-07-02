package com.tpi.vehiculos.services;

import com.tpi.vehiculos.clients.PruebaClient;
import com.tpi.vehiculos.dtos.PosicionDTO;
import com.tpi.vehiculos.entities.Posicion;
import com.tpi.vehiculos.entities.Vehiculo;
import com.tpi.vehiculos.repositories.PosicionRepository;
import com.tpi.vehiculos.repositories.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PosicionService {

    private final PosicionRepository posicionRepository;
    private final PruebaClient pruebaClient;

    public PosicionService(PosicionRepository posicionRepository, PruebaClient pruebaClient) {
        this.posicionRepository = posicionRepository;
        this.pruebaClient = pruebaClient;
    }

    public List<Posicion> listar() {
        return posicionRepository.findAll();
    }

    public List<PosicionDTO> listarDTO() {
        return posicionRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }


    public Optional<Posicion> obtenerPorId(Long id) {
        return posicionRepository.findById(id);
    }

    public Posicion crear(Posicion posicion) {
        Long idVehiculo = Long.valueOf(posicion.getVehiculo().getId());

        // Validar si el vehículo está en una prueba activa
        boolean estaEnPrueba = pruebaClient.vehiculoEstaEnPrueba(idVehiculo);
        if (!estaEnPrueba) {
            throw new IllegalArgumentException("El vehículo no está en una prueba activa. No se puede registrar la posición.");
        }

        Posicion guardada = posicionRepository.save(posicion);

        // Llamar automáticamente a pruebas-service para validación
        PosicionDTO dto = toDTO(guardada);
        pruebaClient.validarPosicion(dto);

        return guardada;
    }

    public Optional<Posicion> actualizar(Long id, Posicion posicion) {
        return posicionRepository.findById(id)
                .map(p -> {
                    p.setVehiculo(posicion.getVehiculo());
                    p.setFechaHora(posicion.getFechaHora());
                    p.setLatitud(posicion.getLatitud());
                    p.setLongitud(posicion.getLongitud());
                    return posicionRepository.save(p);
                });
    }

    public boolean eliminar(Long id) {
        if (posicionRepository.existsById(id)) {
            posicionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Posicion> buscarPorVehiculo(Long idVehiculo) {
        return posicionRepository.findByVehiculoId(idVehiculo);
    }

    public List<Posicion> buscarPorRangoFechas(LocalDateTime inicio, LocalDateTime fin) {
        return posicionRepository.findByFechaHoraBetween(inicio, fin);
    }

    public PosicionDTO toDTO(Posicion posicion) {
        return new PosicionDTO(
                posicion.getId(),
                posicion.getFechaHora(),
                posicion.getLatitud(),
                posicion.getLongitud(),
                posicion.getVehiculo() != null ? posicion.getVehiculo().getId().longValue() : null
        );
    }


    @Autowired
    private VehiculoRepository vehiculoRepository;

    public Posicion fromDTO(PosicionDTO dto, Long idVehiculo) {
        Vehiculo vehiculo = vehiculoRepository.findById(idVehiculo)
                .orElseThrow(() -> new IllegalArgumentException("Vehículo no encontrado"));
        Posicion p = new Posicion();
        p.setId(dto.getId());
        p.setLatitud(dto.getLatitud());
        p.setLongitud(dto.getLongitud());
        p.setFechaHora(dto.getFechaHora());
        p.setVehiculo(vehiculo);
        return p;
    }

}
