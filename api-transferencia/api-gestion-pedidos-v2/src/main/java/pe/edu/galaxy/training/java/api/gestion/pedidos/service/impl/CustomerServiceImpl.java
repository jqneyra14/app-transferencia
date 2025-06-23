package pe.edu.galaxy.training.java.api.gestion.pedidos.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.galaxy.training.java.api.gestion.pedidos.dto.request.CustomerRequestDto;
import pe.edu.galaxy.training.java.api.gestion.pedidos.dto.response.CustomerResponseDto;
import pe.edu.galaxy.training.java.api.gestion.pedidos.entity.Customer;
import pe.edu.galaxy.training.java.api.gestion.pedidos.mapper.CustomerMapper;
import pe.edu.galaxy.training.java.api.gestion.pedidos.repository.CustomerRepository;
import pe.edu.galaxy.training.java.api.gestion.pedidos.service.CustomerService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public List<CustomerResponseDto> getCustomer() {
        return customerRepository
                .findAll()
                .stream()
                .map(customerMapper::toResponse)
                .toList();
    }

    @Override
    public Optional<CustomerResponseDto> getById(Integer id) {
            return Optional.ofNullable(
                    customerRepository
                            .findById(id)
                            .map(customerMapper::toResponse)
                            .orElseThrow(() -> new RuntimeException("Customer no encontrado"))
            );
    }

    @Override
    @Transactional
    public CustomerResponseDto create(CustomerRequestDto customerRequestDto) {
        var customerEntity = new Customer();



        customerEntity = customerMapper.toEntity(customerRequestDto);

        customerEntity.setFullName(customerRequestDto.nombres() + " "+ customerRequestDto.apellidos());
        customerEntity.setFechaCreacion(LocalDateTime.now());
        var customerSaved = customerRepository.save(customerEntity);
        return customerMapper.toResponse(customerSaved);
    }

    @Override
    public CustomerResponseDto update(CustomerRequestDto customerRequestDto, Integer id) {
        var customerEntity = new Customer();

        return  customerRepository.findById(id)
                .map(entity -> {
                    customerMapper.entityToUpdate(customerRequestDto, entity);
                    entity.setFechaActualizacion(LocalDateTime.now());
                    entity.setFullName(customerRequestDto.nombres() + " "+ customerRequestDto.apellidos());
                    return customerRepository.save(entity);
                })
                .map(customerMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Cliente con ID " + id + " no existe."));


    }

    @Override
    public void delete(Integer id) {
        customerRepository.findById(id)
                .map(entity -> {
                    customerRepository.delete(entity);
                    return true; // o puedes retornar un mensaje
                })
                .orElseThrow(() -> new RuntimeException("Cliente con ID " + id + " no existe."));

    }
}
