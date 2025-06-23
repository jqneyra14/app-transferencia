package pe.edu.galaxy.training.java.api.gestion.pedidos.dto.response;

import java.math.BigDecimal;

public record SistemaResponseDto(
        Integer id,
        String name,
        Integer activo,
        Integer aperturado
) {
}
