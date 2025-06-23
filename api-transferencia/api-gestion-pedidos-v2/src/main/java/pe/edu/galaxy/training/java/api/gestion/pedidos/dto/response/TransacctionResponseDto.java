package pe.edu.galaxy.training.java.api.gestion.pedidos.dto.response;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransacctionResponseDto (

        Integer id,
        BigDecimal amount,
        String type,
        LocalDateTime creationdate,
        Integer accountId

){

}
