package pe.edu.galaxy.training.java.api.gestion.pedidos.service;

import pe.edu.galaxy.training.java.api.gestion.pedidos.dto.request.CustomerRequestDto;
import pe.edu.galaxy.training.java.api.gestion.pedidos.dto.response.CustomerResponseDto;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<CustomerResponseDto> getCustomer();

    Optional<CustomerResponseDto> getById(Integer id);

    CustomerResponseDto create(CustomerRequestDto customerRequestDto);

    CustomerResponseDto update(CustomerRequestDto customerRequestDto, Integer id);

    void delete(Integer id);
}
