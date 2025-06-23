package pe.edu.galaxy.training.java.api.gestion.pedidos.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_account")
    private Integer idAccount;

    @Column(name = "total_ammount", precision = 10, scale = 2, nullable = false)
    private BigDecimal totalAmmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_customer", nullable = false)
    @JsonIgnoreProperties({"accounts"}) // Evita loop infinito al serializar Customer
    private Customer customer;
}