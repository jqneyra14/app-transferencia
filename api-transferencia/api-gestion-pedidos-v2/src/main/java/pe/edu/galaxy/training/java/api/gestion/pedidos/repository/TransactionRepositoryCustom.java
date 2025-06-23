package pe.edu.galaxy.training.java.api.gestion.pedidos.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pe.edu.galaxy.training.java.api.gestion.pedidos.dto.request.TransactionFilterRequestDto;
import pe.edu.galaxy.training.java.api.gestion.pedidos.dto.response.TransactionPageResponseDto;
import pe.edu.galaxy.training.java.api.gestion.pedidos.dto.response.TransactionPageResultDto;
import pe.edu.galaxy.training.java.api.gestion.pedidos.entity.Transaction;
import pe.edu.galaxy.training.java.api.gestion.pedidos.mapper.CustomerMapper;
import pe.edu.galaxy.training.java.api.gestion.pedidos.mapper.TransacctionMapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class TransactionRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private TransacctionMapper transacctionMapper;
    public TransactionPageResponseDto getFilteredTransactions(TransactionFilterRequestDto request) {

        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("sp_get_transactions_filtered_paginated", Transaction.class);

        query.registerStoredProcedureParameter("startDate", LocalDate.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("endDate", LocalDate.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("idAccount", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("type", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("page", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("size", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("totalElements", Integer.class, ParameterMode.OUT);

        query.setParameter("startDate", request.getStartDate());
        query.setParameter("endDate",  request.getEndDate());
        query.setParameter("idAccount",  request.getIdAccount());
        query.setParameter("type",  request.getType());
        query.setParameter("page",  request.getPage());
        query.setParameter("size",  request.getSize());
//        var data = query.getResultList();


        boolean hasResults = query.execute();
        List<Transaction> resultEntities = query.getResultList();

        List<TransactionPageResultDto> transactions = resultEntities.stream()
                .map(transacctionMapper::toResponseFilter)
                .toList();
// 1. Total elementos


        // Obtener el valor del parámetro de salida
        int totalElements = (Integer) query.getOutputParameterValue("totalElements");
        int totalPages = (int) Math.ceil((double) totalElements / request.getSize());

        return TransactionPageResponseDto.builder()
                .transactions(transactions)
                .totalElements(totalElements)
                .totalPages(totalPages)
                .currentPage(request.getPage())
                .build();

    }
}
