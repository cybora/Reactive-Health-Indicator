package tr.bora.miroservices.composite.product.service.indicator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.ReactiveHealthIndicator;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component("serviceHealth")
public class ProductHealth implements ReactiveHealthIndicator {

    private final WebClient webClient;

    public ProductHealth(final WebClient.Builder webClient) {
        this.webClient = webClient.build();
    }

    @Override
    public Mono<Health> health() {
        return webClient.get()
                .uri("http://product:8080/actuator/info")
                .retrieve()
                .bodyToMono(String.class)
                .map(s -> new Health.Builder().up().build())
                .onErrorResume(ex -> Mono.just(new Health.Builder().down(ex).build()))
                .log();
    }
}
