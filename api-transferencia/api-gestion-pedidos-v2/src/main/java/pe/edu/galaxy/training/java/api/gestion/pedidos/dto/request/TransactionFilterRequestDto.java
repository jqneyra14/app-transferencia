package pe.edu.galaxy.training.java.api.gestion.pedidos.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionFilterRequestDto {
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer idAccount;
    private String type; // "deposit" o "withdrawal"
    private int page;    // número de página (inicia en 0)
    private int size;    // cantidad de resultados por página
}