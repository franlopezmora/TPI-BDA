package com.tpi.admin.services;

import com.tpi.admin.entities.Notificacion;
import com.tpi.admin.repositories.NotificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    public List<Notificacion> obtenerTodas() {
        return notificacionRepository.findAll();
    }

    public Optional<Notificacion> obtenerPorId(Integer id) {
        return notificacionRepository.findById(id);
    }

    public Notificacion crear(Notificacion notificacion) {
        return notificacionRepository.save(notificacion);
    }

    public Optional<Notificacion> actualizar(Integer id, Notificacion nuevaNotificacion) {
        return notificacionRepository.findById(id).map(notificacionExistente -> {
            notificacionExistente.setMensaje(nuevaNotificacion.getMensaje());
            notificacionExistente.setTipo(nuevaNotificacion.getTipo());
            notificacionExistente.setTelefono(nuevaNotificacion.getTelefono());
            notificacionExistente.setFecha(nuevaNotificacion.getFecha());
            notificacionExistente.setPrueba(nuevaNotificacion.getPrueba());
            notificacionExistente.setTipoIncidente(nuevaNotificacion.getTipoIncidente());
            return notificacionRepository.save(notificacionExistente);
        });
    }

    public boolean eliminar(Integer id) {
        if (notificacionRepository.existsById(id)) {
            notificacionRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
