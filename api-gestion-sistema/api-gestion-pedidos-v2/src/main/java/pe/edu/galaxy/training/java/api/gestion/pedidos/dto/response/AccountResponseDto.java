package pe.edu.galaxy.training.java.api.gestion.pedidos.dto.response;

import java.math.BigDecimal;
import java.util.List;

public record AccountResponseDto (
        Integer idAccount,
        BigDecimal totalAmmount,
        Integer idCustomer
//        List<CustomerResponseDto> detalles
)
{}
