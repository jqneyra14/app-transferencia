package pe.edu.galaxy.training.java.api.gestion.pedidos.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import pe.edu.galaxy.training.java.api.gestion.pedidos.client.dto.SistemaResponseDto;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
public class SistemaApiClient {
    private static final Logger LOG = LoggerFactory.getLogger(SistemaApiClient.class);

    @Autowired
    RestClient restClient;

    @Autowired
    RestTemplate template;

    public List<SistemaResponseDto> getSistemaWithRestClient() {
        try {
            LOG.info("Consumiendo API de Sistema con Rest Client...");

            SistemaResponseDto[] sistemas = restClient.get()
                    .uri("/sistemas")
                    .retrieve()
                    .body(SistemaResponseDto[].class);
            return Arrays.asList(Objects.requireNonNull(sistemas));
        } catch (RestClientException e) {
            LOG.error("Error al consumir api externa con Rest Client: ", e);
            return List.of();
        }
    }
}
