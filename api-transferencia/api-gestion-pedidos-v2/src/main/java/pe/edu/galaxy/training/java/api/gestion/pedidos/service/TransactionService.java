package pe.edu.galaxy.training.java.api.gestion.pedidos.service;

import pe.edu.galaxy.training.java.api.gestion.pedidos.dto.request.CustomerRequestDto;
import pe.edu.galaxy.training.java.api.gestion.pedidos.dto.request.TransactionFilterRequestDto;
import pe.edu.galaxy.training.java.api.gestion.pedidos.dto.request.TransactionRequestDto;
import pe.edu.galaxy.training.java.api.gestion.pedidos.dto.response.CustomerResponseDto;
import pe.edu.galaxy.training.java.api.gestion.pedidos.dto.response.TransacctionResponseDto;
import pe.edu.galaxy.training.java.api.gestion.pedidos.dto.response.TransactionPageResponseDto;
import pe.edu.galaxy.training.java.api.gestion.pedidos.entity.Transaction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TransactionService {
    List<TransacctionResponseDto> getTransaction();

    Optional<TransacctionResponseDto> getByIdAccount(Integer id);

    TransacctionResponseDto create(TransactionRequestDto transactionRequestDto);

    void delete(Integer id);

    TransactionPageResponseDto getFilteredTransactions(TransactionFilterRequestDto request);
}
