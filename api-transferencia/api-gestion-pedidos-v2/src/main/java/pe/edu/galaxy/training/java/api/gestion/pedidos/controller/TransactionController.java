package pe.edu.galaxy.training.java.api.gestion.pedidos.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.galaxy.training.java.api.gestion.pedidos.client.dto.SistemaResponseDto;
import pe.edu.galaxy.training.java.api.gestion.pedidos.dto.request.CustomerRequestDto;
import pe.edu.galaxy.training.java.api.gestion.pedidos.dto.request.TransactionFilterRequestDto;
import pe.edu.galaxy.training.java.api.gestion.pedidos.dto.request.TransactionRequestDto;
import pe.edu.galaxy.training.java.api.gestion.pedidos.dto.response.CustomerResponseDto;
import pe.edu.galaxy.training.java.api.gestion.pedidos.dto.response.TransacctionResponseDto;
import pe.edu.galaxy.training.java.api.gestion.pedidos.dto.response.TransactionPageResponseDto;
import pe.edu.galaxy.training.java.api.gestion.pedidos.entity.Transaction;
import pe.edu.galaxy.training.java.api.gestion.pedidos.repository.CustomerRepository;
import pe.edu.galaxy.training.java.api.gestion.pedidos.service.CustomerService;
import pe.edu.galaxy.training.java.api.gestion.pedidos.service.SistemaService;
import pe.edu.galaxy.training.java.api.gestion.pedidos.service.TransactionService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/Transaction")
@Tag(name = "Transaction", description = "API REST para mantener transacciones de clientes / cuentas.")
@RequiredArgsConstructor
public class TransactionController {

    private final CustomerRepository customerRepository;
    private final SistemaService sistemaService;

    @Autowired
    private TransactionService transactionService;
    @GetMapping
    public ResponseEntity<?>  getTransaction() {
        try {
            SistemaResponseDto sistema = sistemaService.getSistemById(1);
            if (sistema == null || sistema.activo() != 1 || sistema.aperturado() != 1) {
                return ResponseEntity
                        .status(HttpStatus.FORBIDDEN)
                        .body("No puede consumir el API Transferencia si el sistema no se encuentra activo y aperturado");
            }
            List<TransacctionResponseDto> transactiones = transactionService.getTransaction();
            if (transactiones.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(transactiones);
        } catch (Exception e) {
            log.error("Error al obtener Customer: ", e);
            return ResponseEntity.internalServerError().build();
        }
    }
    @Operation(
            summary = "Obtener el Customer por su ID",
            description = "Permite consultar la trasaction por su ID registrado en el sistema",
            operationId = "getById"
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        try {
            SistemaResponseDto sistema = sistemaService.getSistemById(1);
            if (sistema == null || sistema.activo() != 1 || sistema.aperturado() != 1) {
                return ResponseEntity
                        .status(HttpStatus.FORBIDDEN)
                        .body("No puede consumir el API Transferencia si el sistema no se encuentra activo y aperturado");
            }
            Optional<TransacctionResponseDto> transferencia = transactionService.getByIdAccount(id);
            if (transferencia.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(transferencia);
        } catch (Exception e) {
            log.error("Error al obtener pedido por id: ", e);
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @Operation(
            summary = "Crear Transaccion ",
            description = "Permite crear un transacciones en el sistema",
            operationId = "create"
    )
    @PostMapping
    public ResponseEntity<?> create(@RequestBody TransactionRequestDto request) {
        SistemaResponseDto sistema = sistemaService.getSistemById(1);
        if (sistema == null || sistema.activo() != 1 || sistema.aperturado() != 1) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("No puede consumir el API Transferencia si el sistema no se encuentra activo y aperturado");
        }
        try {
            var pedido = transactionService.create(request);
            return new ResponseEntity<>(pedido, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error al crear pedido: ", e);
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @Operation(
            summary = "Consultar por fecha, cuenta y tipo tambien paginacion ",
            description = "Para obtener transferencias realizadas correspondiente a una cuenta filtrados por fecha, cuenta y tipo de transferencia",
            operationId = "delete"
    )
    @GetMapping("/filter")
    public ResponseEntity<?> getFilteredTransactions(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String  endDate,
            @RequestParam Integer idAccount,
            @Parameter(
                    description = "Tipo de transacción: 'deposit' o 'withdrawal'",
                    in = ParameterIn.QUERY,
                    required = true,
                    example = "deposit",
                    allowEmptyValue = false,
                    schema = @io.swagger.v3.oas.annotations.media.Schema(allowableValues = {"deposit", "withdrawal"})
            )
            @RequestParam String type,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        SistemaResponseDto sistema = sistemaService.getSistemById(1);
        if (sistema == null || sistema.activo() != 1 || sistema.aperturado() != 1) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("No puede consumir el API Transferencia si el sistema no se encuentra activo y aperturado");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        var filterDto = new TransactionFilterRequestDto(LocalDate.parse(startDate, formatter), LocalDate.parse(endDate, formatter), idAccount, type, page, size);
        var result = transactionService.getFilteredTransactions(filterDto);
        return ResponseEntity.ok(result);
    }
}
