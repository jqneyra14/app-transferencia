package pe.edu.galaxy.training.java.api.gestion.pedidos.dto.response;

import jakarta.persistence.Column;

import java.util.List;

public record CustomerResponseDto (
        Integer idCustomer,
        String nombreCompleto,
        String nombres,
        String apellidos,
        String email,String direccion,
        List<AccountResponseDto> accounts

){
}
