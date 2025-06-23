package pe.edu.galaxy.training.java.api.gestion.pedidos;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class ApiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Sistema API V1")
						.version("1.0.0")
						.summary("API para el mantenimiento de Sistema.")
						.description("Este API es creado para mantener los sistemas de la empresa pepitoSAC.")
						);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Arrancando proyecto Spring Boot...");
	}
}
