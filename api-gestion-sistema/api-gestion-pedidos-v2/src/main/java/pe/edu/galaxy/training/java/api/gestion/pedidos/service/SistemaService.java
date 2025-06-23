package pe.edu.galaxy.training.java.api.gestion.pedidos.service;

import pe.edu.galaxy.training.java.api.gestion.pedidos.dto.request.SistemaRequestDto;
import pe.edu.galaxy.training.java.api.gestion.pedidos.dto.response.SistemaResponseDto;

import java.util.List;
import java.util.Optional;

public interface SistemaService {
    List<SistemaResponseDto> getSistemas();

    Optional<SistemaResponseDto> getById(Integer id);
    SistemaResponseDto update(SistemaRequestDto sistemaRequestDto, Integer id);
}
