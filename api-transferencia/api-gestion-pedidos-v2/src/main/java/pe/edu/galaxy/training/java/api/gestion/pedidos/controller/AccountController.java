package pe.edu.galaxy.training.java.api.gestion.pedidos.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.galaxy.training.java.api.gestion.pedidos.client.dto.SistemaResponseDto;
import pe.edu.galaxy.training.java.api.gestion.pedidos.dto.response.AccountResponseDto;
import pe.edu.galaxy.training.java.api.gestion.pedidos.entity.Account;
import pe.edu.galaxy.training.java.api.gestion.pedidos.entity.Customer;
import pe.edu.galaxy.training.java.api.gestion.pedidos.repository.AccountRepository;
import pe.edu.galaxy.training.java.api.gestion.pedidos.repository.CustomerRepository;
import pe.edu.galaxy.training.java.api.gestion.pedidos.service.AccountService;
import pe.edu.galaxy.training.java.api.gestion.pedidos.service.SistemaService;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@Tag(name = "Account", description = "API REST para mantener Cuentas de clientes del sistema.")
@RequiredArgsConstructor
public class AccountController {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final SistemaService sistemaService;
    @Autowired
    private AccountService accountService;

//    @GetMapping
//    public List<Account> getAllAccounts() {
//        return accountRepository.findAll();
//    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAccountById(@PathVariable Integer id) {
        SistemaResponseDto sistema = sistemaService.getSistemById(1);
        if (sistema == null || sistema.activo() != 1 || sistema.aperturado() != 1) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("No puede consumir el API Transferencia si el sistema no se encuentra activo y aperturado");
        }
        return accountRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<?> getAccount() {
        try {
            SistemaResponseDto sistema = sistemaService.getSistemById(1);
            if (sistema == null || sistema.activo() != 1 || sistema.aperturado() != 1) {
                return ResponseEntity
                        .status(HttpStatus.FORBIDDEN)
                        .body("No puede consumir el API Transferencia si el sistema no se encuentra activo y aperturado");
            }
            List<AccountResponseDto> productos = accountService.getAccount();
            if (productos.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(productos);
        } catch (Exception e) {
//            log.error("Error al obtener productos: ", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody Account account) {
        // Asegura que el customer existe antes de guardar la cuenta
        SistemaResponseDto sistema = sistemaService.getSistemById(1);
        if (sistema == null || sistema.activo() != 1 || sistema.aperturado() != 1) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("No puede consumir el API Transferencia si el sistema no se encuentra activo y aperturado");
        }
        Integer customerId = account.getCustomer().getIdCustomer();
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) {
            return ResponseEntity.badRequest().build();
        }

        account.setCustomer(customer);
        Account savedAccount = accountRepository.save(account);
        return ResponseEntity.ok(savedAccount);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable Integer id, @RequestBody Account updatedAccount) {
        SistemaResponseDto sistema = sistemaService.getSistemById(1);
        if (sistema == null || sistema.activo() != 1 || sistema.aperturado() != 1) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("No puede consumir el API Transferencia si el sistema no se encuentra activo y aperturado");
        }
        return accountRepository.findById(id).map(account -> {
            account.setTotalAmmount(updatedAccount.getTotalAmmount());
            account.setCustomer(updatedAccount.getCustomer());
            return ResponseEntity.ok(accountRepository.save(account));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Integer id) {
        SistemaResponseDto sistema = sistemaService.getSistemById(1);
        if (sistema == null || sistema.activo() != 1 || sistema.aperturado() != 1) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("No puede consumir el API Transferencia si el sistema no se encuentra activo y aperturado");
        }
        if (!accountRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        accountRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}