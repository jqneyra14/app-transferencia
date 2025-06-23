package pe.edu.galaxy.training.java.api.gestion.pedidos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import pe.edu.galaxy.training.java.api.gestion.pedidos.dto.request.CustomerRequestDto;
import pe.edu.galaxy.training.java.api.gestion.pedidos.dto.response.CustomerResponseDto;
import pe.edu.galaxy.training.java.api.gestion.pedidos.entity.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    @Mapping(source = "idCustomer", target = "idCustomer")
    @Mapping(source = "fullName", target = "nombreCompleto")
    CustomerResponseDto toResponse(Customer customer);


    @Mapping(target = "fullName", ignore = true)
    Customer toEntity(CustomerRequestDto customerDTO);


//    @Mapping(target = "id", ignore = true)
    void entityToUpdate(CustomerRequestDto request, @MappingTarget Customer entity);
}
