package pe.edu.galaxy.training.java.api.gestion.pedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.galaxy.training.java.api.gestion.pedidos.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {}

