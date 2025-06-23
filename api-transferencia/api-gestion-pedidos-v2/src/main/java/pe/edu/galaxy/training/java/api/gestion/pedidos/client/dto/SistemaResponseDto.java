package pe.edu.galaxy.training.java.api.gestion.pedidos.client.dto;

public record SistemaResponseDto(
        Integer id,
        String name,
        Integer activo,
        Integer aperturado
) {
}