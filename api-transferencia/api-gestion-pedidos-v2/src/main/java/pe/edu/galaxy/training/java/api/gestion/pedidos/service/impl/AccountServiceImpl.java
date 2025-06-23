package pe.edu.galaxy.training.java.api.gestion.pedidos.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.galaxy.training.java.api.gestion.pedidos.dto.response.AccountResponseDto;
import pe.edu.galaxy.training.java.api.gestion.pedidos.entity.Customer;
import pe.edu.galaxy.training.java.api.gestion.pedidos.mapper.AccountMapper;
import pe.edu.galaxy.training.java.api.gestion.pedidos.repository.AccountRepository;
import pe.edu.galaxy.training.java.api.gestion.pedidos.service.AccountService;

import java.util.List;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Override
    public List<AccountResponseDto> getAccount() {
        return accountRepository
                .findAll()
                .stream()
                .map(accountMapper::toResponse)
                .toList();
    }

    @Override
    public AccountResponseDto getById(Integer id) {
        return  accountRepository
                .findById(id)
                .map(accountMapper::toResponse)
                .orElse(null);
    }

    @Override
    public AccountResponseDto update(AccountResponseDto accountResponseDto, Integer id) {
        var customerEntity = new Customer();

        return  accountRepository.findById(id)
                .map(entity -> {
                    accountMapper.toEntity(accountResponseDto);
                    entity.setTotalAmmount(accountResponseDto.totalAmmount());

                    return accountRepository.save(entity);
                })
                .map(accountMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Cuenta con ID " + id + " no existe."));
    }
//
//    @Override
//    public ProductoResponseDto getById(Long id) {
//        return productoRepository
//                .findById(id)
//                .map(productoMapper::toResponse)
//                .orElse(null);
//    }
//
//    @Override
//    public ProductoResponseDto create(ProductoRequestDto producto) {
//        return productoMapper.toResponse(
//                productoRepository
//                        .save(productoMapper
//                                .toEntity(producto)
//                        )
//        );
//    }
//
//    @Override
//    public ProductoResponseDto update(ProductoRequestDto producto, Long id) {
//        return productoRepository.findById(id)
//                .map(entity -> {
//                    productoMapper.entityToUpdate(producto, entity);
//                    return productoRepository.save(entity);
//                })
//                .map(productoMapper::toResponse)
//                .orElseThrow(() -> new RuntimeException("Producto con ID " + id + " no existe."));
//    }
//
//    @Override
//    public void delete(Long id) {
//        productoRepository.deleteById(id);
//    }
}
