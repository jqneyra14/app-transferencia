package pe.edu.galaxy.training.java.api.gestion.pedidos.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.galaxy.training.java.api.gestion.pedidos.client.SistemaApiClient;
import pe.edu.galaxy.training.java.api.gestion.pedidos.client.dto.SistemaResponseDto;
import pe.edu.galaxy.training.java.api.gestion.pedidos.service.SistemaService;

@Service
@AllArgsConstructor
public class SistemaServiceImpl  implements SistemaService {
    private final SistemaApiClient client;
    @Override
    public SistemaResponseDto getSistemById(Integer id) {
        var lista = client
                .getSistemaWithRestClient()
                .stream()
                .map(sistema ->
                        new SistemaResponseDto(
                                sistema.id(),
                                sistema.name(),
                                sistema.activo(),
                                sistema.aperturado()
                        ))
                .toList();
        SistemaResponseDto sistema = lista.stream()
                .filter(c -> c.id() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Categoría con ID 1 no encontrada"));

        return sistema;
    }
}
