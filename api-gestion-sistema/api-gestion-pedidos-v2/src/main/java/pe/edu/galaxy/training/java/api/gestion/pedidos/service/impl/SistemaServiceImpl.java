package pe.edu.galaxy.training.java.api.gestion.pedidos.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.galaxy.training.java.api.gestion.pedidos.dto.request.SistemaRequestDto;
import pe.edu.galaxy.training.java.api.gestion.pedidos.dto.response.SistemaResponseDto;
import pe.edu.galaxy.training.java.api.gestion.pedidos.mapper.SistemaMapper;
import pe.edu.galaxy.training.java.api.gestion.pedidos.repository.SistemaRepository;
import pe.edu.galaxy.training.java.api.gestion.pedidos.service.SistemaService;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class SistemaServiceImpl implements SistemaService {
    private final SistemaRepository sistemaRepository;
    private final SistemaMapper sistemaMapper;
    @Override
    public List<SistemaResponseDto> getSistemas() {
        return sistemaRepository
                .findAll()
                .stream()
                .map(sistemaMapper::toResponse)
                .toList();
    }

    @Override
    public Optional<SistemaResponseDto> getById(Integer id) {
        return Optional.ofNullable(
                sistemaRepository
                        .findById(id)
                        .map(sistemaMapper::toResponse)
                        .orElseThrow(() -> new RuntimeException("Customer no encontrado"))
        );
    }

    @Override
    @Transactional
    public SistemaResponseDto update(SistemaRequestDto sistemaRequestDto, Integer id) {


        return  sistemaRepository.findById(id)
                .map(entity -> {
                    var data = sistemaMapper.toEntity(sistemaRequestDto);
                    entity.setActivo(data.getActivo());
                    entity.setAperturado(data.getAperturado());
                    entity.setName(data.getName());
                    return sistemaRepository.save(entity);
                })
                .map(sistemaMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Sistema con ID " + id + " no existe."));
    }
}
