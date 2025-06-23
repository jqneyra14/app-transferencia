package pe.edu.galaxy.training.java.api.gestion.pedidos.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.galaxy.training.java.api.gestion.pedidos.dto.request.SistemaRequestDto;
import pe.edu.galaxy.training.java.api.gestion.pedidos.dto.response.SistemaResponseDto;
import pe.edu.galaxy.training.java.api.gestion.pedidos.repository.SistemaRepository;
import pe.edu.galaxy.training.java.api.gestion.pedidos.service.SistemaService;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/sistemas")
@Tag(name = "Sistema", description = "API REST para mantener sistemas.")
@RequiredArgsConstructor
public class SistemaController {

    private final SistemaRepository sistemaRepository;

    @Autowired
    private SistemaService sistemaService;





    @GetMapping
    public ResponseEntity<List<SistemaResponseDto>> getAccount() {
        try {
            List<SistemaResponseDto> sistemas = sistemaService.getSistemas();
            if (sistemas.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(sistemas);
        } catch (Exception e) {
//            log.error("Error al obtener productos: ", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                    @RequestBody SistemaRequestDto sistema) {
        try {
            SistemaResponseDto customerSaved = sistemaService.update(sistema, id);
            if (customerSaved == null) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(customerSaved);
        } catch (Exception e) {
            log.error("Error al actualizar producto: ", e);
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
