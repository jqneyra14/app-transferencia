package pe.edu.galaxy.training.java.api.gestion.pedidos.dto.response;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class TransactionPageResultDto {
    private Integer id;
    private BigDecimal amount;
    private String type;
    private LocalDateTime creationdate;
    private Integer accountId;
    private BigDecimal currentAmount;
    private BigDecimal previousAmount;
}
