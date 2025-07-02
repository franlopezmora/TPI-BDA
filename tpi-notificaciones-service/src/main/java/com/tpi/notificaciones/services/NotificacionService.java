package com.tpi.notificaciones.services;

import com.tpi.notificaciones.dtos.NotificacionDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class NotificacionService {

    @Value("${discord.webhook.url}")
    private String discordWebhookUrl;

    public void enviarNotificacion(NotificacionDTO dto) {
        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> payload = new HashMap<>();
        payload.put("content", String.format("🛎️ *%s*\n\n📅 %s\n\n📝 %s", dto.getTipo(), dto.getFecha(), dto.getMensaje()));

        restTemplate.postForEntity(discordWebhookUrl, payload, String.class);
    }
}
