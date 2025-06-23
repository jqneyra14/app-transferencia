package pe.edu.galaxy.training.java.api.gestion.pedidos.runners;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class ClientDataSeeder implements CommandLineRunner {

  private final JdbcTemplate template;

  @Override
  public void run(String... args) throws Exception {
//    String sql = "INSERT INTO clientes (nombres, apellidos, email, direccion) VALUES (?, ?, ?, ?);";
//    template.update(sql, "AAAAA", "AAAAA", "AAAAA", "AAAAAAA");
//    template.update(sql, "BBBBB", "BBBB", "BBB", "BBB");
//    template.update(sql, "CCCC", "CCC", "CCCC", "CCC");
  }
}
