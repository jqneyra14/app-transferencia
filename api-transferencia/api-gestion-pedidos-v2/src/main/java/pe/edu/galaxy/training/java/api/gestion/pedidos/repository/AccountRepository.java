package pe.edu.galaxy.training.java.api.gestion.pedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.galaxy.training.java.api.gestion.pedidos.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {}