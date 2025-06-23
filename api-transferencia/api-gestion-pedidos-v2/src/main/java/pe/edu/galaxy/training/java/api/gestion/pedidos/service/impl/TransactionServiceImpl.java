package pe.edu.galaxy.training.java.api.gestion.pedidos.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.edu.galaxy.training.java.api.gestion.pedidos.dto.request.TransactionFilterRequestDto;
import pe.edu.galaxy.training.java.api.gestion.pedidos.dto.request.TransactionRequestDto;
import pe.edu.galaxy.training.java.api.gestion.pedidos.dto.response.TransacctionResponseDto;
import pe.edu.galaxy.training.java.api.gestion.pedidos.dto.response.TransactionPageResponseDto;
import pe.edu.galaxy.training.java.api.gestion.pedidos.entity.Account;
import pe.edu.galaxy.training.java.api.gestion.pedidos.entity.Customer;
import pe.edu.galaxy.training.java.api.gestion.pedidos.entity.Transaction;
import pe.edu.galaxy.training.java.api.gestion.pedidos.mapper.AccountMapper;
import pe.edu.galaxy.training.java.api.gestion.pedidos.mapper.TransacctionMapper;
import pe.edu.galaxy.training.java.api.gestion.pedidos.repository.AccountRepository;
import pe.edu.galaxy.training.java.api.gestion.pedidos.repository.TransactionRepository;
import pe.edu.galaxy.training.java.api.gestion.pedidos.repository.TransactionRepositoryCustom;
import pe.edu.galaxy.training.java.api.gestion.pedidos.service.AccountService;
import pe.edu.galaxy.training.java.api.gestion.pedidos.service.TransactionService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransacctionMapper transacctionMapper;
    private final AccountRepository accountRepository;
    private final AccountService accountService;
    private final AccountMapper accountMapper;
    @Override
    public List<TransacctionResponseDto> getTransaction() {
        return transactionRepository
                .findAll()
                .stream()
                .map(transacctionMapper::toResponse)
                .toList();
    }

    @Override
    public Optional<TransacctionResponseDto> getByIdAccount(Integer id) {
        return Optional.ofNullable(
                transactionRepository
                        .findById(id)
                        .map(transacctionMapper::toResponse)
                        .orElseThrow(() -> new RuntimeException("transaccion no encontrado"))
        );
    }


    @Override
    @Transactional
    public TransacctionResponseDto create(TransactionRequestDto transactionRequestDto) {


        // Obtener cuenta
        var accountDto = accountService.getById(transactionRequestDto.accountId());
        log.info("obteniendo cuenta por Id ", accountDto);
        // Validar si es null
        if (accountDto == null) {
            throw new RuntimeException("Cuenta no encontrada con ID: " + transactionRequestDto.accountId());
        }
        var dataAccount = accountRepository.findById(transactionRequestDto.accountId());
        BigDecimal amount = transactionRequestDto.amount();
        String type = transactionRequestDto.type().toString().toLowerCase();


        Account account = accountMapper.toEntity(accountDto);
        BigDecimal previos_ammount = account.getTotalAmmount();
        log.info("convirtiendo la cuenta en account para entidad ", account);
        // Validaciones y lógica de negocio
        if (type.equals("withdrawal")) {
            if (account.getTotalAmmount().compareTo(amount) < 0) {
                throw new RuntimeException("No tiene suficiente dinero para realizar la transacción 'withdrawal'");
            }
            // Restar el monto
            account.setTotalAmmount(account.getTotalAmmount().subtract(amount));
        } else if (type.equals("deposit")) {
            // Sumar el monto
            account.setTotalAmmount(account.getTotalAmmount().add(amount));
        } else {
            throw new RuntimeException("Tipo de transacción no válido. Debe ser 'deposit' o 'withdrawal'");
        }
        var customer = new Customer();
        customer.setIdCustomer(accountDto.idCustomer());
        account.setCustomer(customer);
        accountRepository.save(account);

        // Crear transacción
        var transactionEntity = transacctionMapper.toEntity(transactionRequestDto);
        transactionEntity.setCreationdate(LocalDateTime.now());
        transactionEntity.setCurrentAmount(account.getTotalAmmount());
        transactionEntity.setPreviousAmount(previos_ammount);
        var transactionSaved = transactionRepository.save(transactionEntity);
        return transacctionMapper.toResponse(transactionSaved);

    }

    private final TransactionRepositoryCustom transactionRepositoryCustom;


    @Override
    public TransactionPageResponseDto getFilteredTransactions(TransactionFilterRequestDto request) {
        var data  = transactionRepositoryCustom.getFilteredTransactions(request);
        return data;
    }

    @Override
    public void delete(Integer id) {

    }
}
