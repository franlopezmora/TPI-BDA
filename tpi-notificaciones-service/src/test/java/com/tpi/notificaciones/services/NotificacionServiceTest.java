package com.tpi.notificaciones.services;

import com.tpi.notificaciones.dtos.NotificacionDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Map;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NotificacionServiceTest {

    @Test
    void testEnviarNotificacion_usesWebhookUrlAndSendsPayload() {
        // Configuro el servicio con un webhook URL de prueba
        NotificacionService service = new NotificacionService();
        ReflectionTestUtils.setField(service, "discordWebhookUrl", "https://discord/webhook");

        // Creo un DTO de ejemplo
        NotificacionDTO dto = new NotificacionDTO(
                "Mensaje de prueba",
                "ALERTA",
                LocalDateTime.of(2025, 6, 18, 12, 0)
        );

        // Intercepto cualquier construcción de RestTemplate
        try (MockedConstruction<RestTemplate> mocks = Mockito.mockConstruction(RestTemplate.class,
                (mock, context) -> {
                    when(mock.postForEntity(
                            anyString(),
                            any(),
                            eq(String.class)
                    )).thenReturn(ResponseEntity.ok("ok"));
                })
        ) {
            // Ejecuto el método bajo test
            service.enviarNotificacion(dto);

            // Verifico la llamada a RestTemplate.postForEntity
            RestTemplate rest = mocks.constructed().get(0);
            verify(rest).postForEntity(
                    eq("https://discord/webhook"),
                    argThat(obj -> {
                        if (!(obj instanceof Map)) {
                            return false;
                        }
                        @SuppressWarnings("unchecked")
                        Map<String, Object> payload = (Map<String, Object>) obj;
                        Object contentObj = payload.get("content");
                        if (contentObj == null) {
                            return false;
                        }
                        String content = contentObj.toString();
                        return content.contains(dto.getTipo())
                                && content.contains(dto.getFecha().toString())
                                && content.contains(dto.getMensaje());
                    }),
                    eq(String.class)
            );
        }
    }
}
