package pe.edu.galaxy.training.java.api.gestion.pedidos.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import pe.edu.galaxy.training.java.api.gestion.pedidos.dto.validators.AlphaNumericWithSpaces;

public record SistemaRequestDto(
        @NotNull(message = "El name es obligatorio")
        @AlphaNumericWithSpaces(message = "Solo numero, espacios y letras")
        @NotEmpty(message = "El campo name es obligatorio")
        String name,

        @NotNull(message = "El campo precio es obligatorio")
        @Min(value = 0, message = "El valor minimo de activo es 0")
        String activo,


        @NotEmpty(message = "El campo email es obligatorio")
        @Min(value = 0, message = "El valor minimo de aperturado es 0")
        String aperturado

) {
}
