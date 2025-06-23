package pe.edu.galaxy.training.java.api.gestion.pedidos.service;

import pe.edu.galaxy.training.java.api.gestion.pedidos.dto.response.AccountResponseDto;

import java.util.List;

public interface AccountService {

    List<AccountResponseDto> getAccount();

    AccountResponseDto getById(Integer id);
//    ProductoResponseDto create(ProductoRequestDto producto);
    AccountResponseDto update(AccountResponseDto accountResponseDto, Integer id);
//    void delete(Long id);
}
