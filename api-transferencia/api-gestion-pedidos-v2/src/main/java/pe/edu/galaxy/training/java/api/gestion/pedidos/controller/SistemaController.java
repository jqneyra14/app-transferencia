package pe.edu.galaxy.training.java.api.gestion.pedidos.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.galaxy.training.java.api.gestion.pedidos.client.dto.SistemaResponseDto;
import pe.edu.galaxy.training.java.api.gestion.pedidos.service.SistemaService;

@Slf4j
@RestController
@RequestMapping("/v2/sistemas")
@Tag(name = "Sistema", description = "API REST para listar Sistemas.")
@AllArgsConstructor
public class SistemaController {

    private final SistemaService sistemaService;

    @GetMapping("/{id}")
    public ResponseEntity<SistemaResponseDto> getSistemaById(@PathVariable Integer id) {
        try {
            SistemaResponseDto sistema = sistemaService.getSistemById(id);
//            if (sistema.()) {
//                return ResponseEntity.noContent().build();
//            }
            return ResponseEntity.ok(sistema);
        } catch (Exception e) {
            log.error("Error al obtener Categorias desde api externa: ", e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
