package pe.edu.galaxy.training.java.api.gestion.pedidos.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record TransactionRequestDto (
        BigDecimal amount,
        @Schema(description = "Tipo de transacción", allowableValues = {"deposit", "withdrawal"})
        TransactionType type,
        Integer accountId
){
}
