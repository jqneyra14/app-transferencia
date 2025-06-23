package pe.edu.galaxy.training.java.api.gestion.pedidos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import pe.edu.galaxy.training.java.api.gestion.pedidos.dto.request.TransactionRequestDto;
import pe.edu.galaxy.training.java.api.gestion.pedidos.dto.response.TransacctionResponseDto;
import pe.edu.galaxy.training.java.api.gestion.pedidos.dto.response.TransactionPageResultDto;
import pe.edu.galaxy.training.java.api.gestion.pedidos.entity.Transaction;

@Mapper(componentModel = "spring")
public interface TransacctionMapper {


    TransacctionResponseDto toResponse(Transaction transaction);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationdate", ignore = true)
    Transaction toEntity(TransactionRequestDto transactionRequestDto);


    //    @Mapping(target = "id", ignore = true)
    void entityToUpdate(TransactionRequestDto request, @MappingTarget Transaction entity);

    TransactionPageResultDto toResponseFilter(Transaction transaction);
}
