package pe.edu.galaxy.training.java.api.gestion.pedidos.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionPageResponseDto {
    private List<TransactionPageResultDto> transactions;
    private int totalPages;
    private int currentPage;
    private long totalElements;
}