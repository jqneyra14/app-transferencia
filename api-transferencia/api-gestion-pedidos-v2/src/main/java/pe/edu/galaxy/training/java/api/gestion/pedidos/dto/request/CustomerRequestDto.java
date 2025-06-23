package pe.edu.galaxy.training.java.api.gestion.pedidos.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import pe.edu.galaxy.training.java.api.gestion.pedidos.dto.validators.AlphaNumericWithSpaces;

import java.math.BigDecimal;

public record CustomerRequestDto (
        @NotNull(message = "El nombres email es obligatorio")
        @AlphaNumericWithSpaces(message = "Solo numero, espacios y letras")
        @NotEmpty(message = "El campo nombres es obligatorio")
        String nombres,

        @NotNull(message = "El campo apellidos es obligatorio")
        @AlphaNumericWithSpaces(message = "Solo numero, espacios y letras")
        @NotEmpty(message = "El campo apellidos es obligatorio")
        String apellidos,


        @NotEmpty(message = "El campo email es obligatorio")
        String email,

        @NotEmpty(message = "El campo direccion es obligatorio")
        String direccion


) {
}
