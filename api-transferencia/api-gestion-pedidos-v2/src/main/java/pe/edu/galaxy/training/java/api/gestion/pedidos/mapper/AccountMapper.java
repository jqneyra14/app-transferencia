package pe.edu.galaxy.training.java.api.gestion.pedidos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.galaxy.training.java.api.gestion.pedidos.dto.request.CustomerRequestDto;
import pe.edu.galaxy.training.java.api.gestion.pedidos.dto.response.AccountResponseDto;
import pe.edu.galaxy.training.java.api.gestion.pedidos.entity.Account;
import pe.edu.galaxy.training.java.api.gestion.pedidos.entity.Customer;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    @Mapping(source = "idAccount", target = "idAccount")
    @Mapping(source = "customer.idCustomer", target = "idCustomer")
    AccountResponseDto toResponse(Account account);

    @Mapping(target = "customer", ignore = true)
    Account toEntity(AccountResponseDto accountResponseDto);
}
