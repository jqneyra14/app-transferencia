package pe.edu.galaxy.training.java.api.gestion.pedidos.mapper;

import org.mapstruct.Mapper;
import pe.edu.galaxy.training.java.api.gestion.pedidos.dto.request.SistemaRequestDto;
import pe.edu.galaxy.training.java.api.gestion.pedidos.dto.response.SistemaResponseDto;
import pe.edu.galaxy.training.java.api.gestion.pedidos.entity.Sistema;
@Mapper(componentModel = "spring")
public interface SistemaMapper {

    SistemaResponseDto toResponse(Sistema sistema);


    Sistema toEntity(SistemaRequestDto sistemaRequestDto);
}
