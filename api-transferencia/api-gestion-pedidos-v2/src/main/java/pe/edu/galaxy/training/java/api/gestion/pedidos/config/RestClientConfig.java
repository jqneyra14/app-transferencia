package pe.edu.galaxy.training.java.api.gestion.pedidos.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestClientConfig {
  @Value("${app.config.urlsistema}")
  private String urlSistema;
  @Bean
  public RestClient restClient(RestClient.Builder builder) {
    return builder.baseUrl(urlSistema).build();
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
