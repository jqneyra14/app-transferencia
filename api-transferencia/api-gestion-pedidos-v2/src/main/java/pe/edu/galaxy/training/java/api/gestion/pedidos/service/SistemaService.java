package pe.edu.galaxy.training.java.api.gestion.pedidos.service;


import pe.edu.galaxy.training.java.api.gestion.pedidos.client.dto.SistemaResponseDto;

public interface SistemaService {
    SistemaResponseDto getSistemById(Integer id);
}
