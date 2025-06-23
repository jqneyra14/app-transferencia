package pe.edu.galaxy.training.java.api.gestion.pedidos.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Getter
@Setter
@Table(name = "[transaction]")
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaction")
    private Integer id;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "type")
    private String type;

    @Column(name = "fecha_creacion")
    private LocalDateTime creationdate;

    @Column(name = "id_account")
    private Integer accountId;

    @Column(name = "current_amount")
    private BigDecimal currentAmount;

    @Column(name = "previous_amount")
    private BigDecimal previousAmount;
}
