package pe.edu.galaxy.training.java.api.gestion.pedidos.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.galaxy.training.java.api.gestion.pedidos.client.dto.SistemaResponseDto;
import pe.edu.galaxy.training.java.api.gestion.pedidos.dto.request.CustomerRequestDto;
import pe.edu.galaxy.training.java.api.gestion.pedidos.dto.response.CustomerResponseDto;
import pe.edu.galaxy.training.java.api.gestion.pedidos.repository.CustomerRepository;
import pe.edu.galaxy.training.java.api.gestion.pedidos.service.CustomerService;
import pe.edu.galaxy.training.java.api.gestion.pedidos.service.SistemaService;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/Customer")
@Tag(name = "Customer", description = "API REST para mantener Clientes de cuentas.")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerRepository customerRepository;
    private final SistemaService sistemaService;

    @Autowired
    private CustomerService customerService;
    @GetMapping
    public ResponseEntity<?> getCustomer() {
        SistemaResponseDto sistema = sistemaService.getSistemById(1);
        if (sistema == null || sistema.activo() != 1 || sistema.aperturado() != 1) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("No puede consumir el API Transferencia si el sistema no se encuentra activo y aperturado");
        }
        try {
            List<CustomerResponseDto> customers = customerService.getCustomer();
            if (customers.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(customers);
        } catch (Exception e) {
            log.error("Error al obtener Customer: ", e);
            return ResponseEntity.internalServerError().build();
        }
    }
    @Operation(
            summary = "Obtener el Customer por su ID",
            description = "Permite consultar el Customer por su ID registrado en el sistema",
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
            Optional<CustomerResponseDto> pedido = customerService.getById(id);
            if (pedido.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(pedido);
        } catch (Exception e) {
            log.error("Error al obtener pedido por id: ", e);
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @Operation(
            summary = "Crear Customer ",
            description = "Permite crear un Customer (cliente) en el sistema",
            operationId = "create"
    )
    @PostMapping
    public ResponseEntity<?> create(@RequestBody CustomerRequestDto request) {
        try {
            SistemaResponseDto sistema = sistemaService.getSistemById(1);
            if (sistema == null || sistema.activo() != 1 || sistema.aperturado() != 1) {
                return ResponseEntity
                        .status(HttpStatus.FORBIDDEN)
                        .body("No puede consumir el API Transferencia si el sistema no se encuentra activo y aperturado");
            }
            var pedido = customerService.create(request);
            return new ResponseEntity<>(pedido, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error al crear pedido: ", e);
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
    @Operation(
            summary = "Actualizar Customer ",
            description = "Permite actualizar un Customer (cliente) en el sistema",
            operationId = "update"
    )
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                    @RequestBody CustomerRequestDto customer) {
        SistemaResponseDto sistema = sistemaService.getSistemById(1);
        if (sistema == null || sistema.activo() != 1 || sistema.aperturado() != 1) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("No puede consumir el API Transferencia si el sistema no se encuentra activo y aperturado");
        }
        try {
            CustomerResponseDto customerSaved = customerService.update(customer, id);
            if (customerSaved == null) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(customerSaved);
        } catch (Exception e) {
            log.error("Error al actualizar producto: ", e);
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(
            summary = "Eliminar Customer ",
            description = "Permite eliminar un Customer (cliente) en el sistema",
            operationId = "delete"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        try {
            SistemaResponseDto sistema = sistemaService.getSistemById(1);
            if (sistema == null || sistema.activo() != 1 || sistema.aperturado() != 1) {
                return ResponseEntity
                        .status(HttpStatus.FORBIDDEN)
                        .body("No puede consumir el API Transferencia si el sistema no se encuentra activo y aperturado");
            }
            customerService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("Error al eliminar producto: ", e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
