package pe.edu.galaxy.training.java.api.gestion.pedidos.dto.response;

public record CategoriaResponseDto (
    Long id,
    String nombre,
    String slug,
    String imagen
) {
}
