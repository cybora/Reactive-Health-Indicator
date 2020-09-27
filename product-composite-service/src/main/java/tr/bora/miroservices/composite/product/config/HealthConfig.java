package tr.bora.miroservices.composite.product.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.CompositeReactiveHealthContributor;
import org.springframework.boot.actuate.health.ReactiveHealthContributor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tr.bora.miroservices.composite.product.service.indicator.ProductCompositeHealth;
import tr.bora.miroservices.composite.product.service.indicator.ProductHealth;
import tr.bora.miroservices.composite.product.service.indicator.RecommendationHealth;
import tr.bora.miroservices.composite.product.service.indicator.ReviewHealth;

import java.util.Map;

@Configuration
public class HealthConfig {

    private final ProductHealth productHealth;

    private final ReviewHealth reviewHealth;

    private final RecommendationHealth recommendationHealth;

    private final ProductCompositeHealth productCompositeHealth;

    @Autowired
    public HealthConfig(ProductHealth productHealth, ReviewHealth reviewHealth, RecommendationHealth recommendationHealth, ProductCompositeHealth productCompositeHealth) {
        this.productHealth = productHealth;
        this.reviewHealth = reviewHealth;
        this.recommendationHealth = recommendationHealth;
        this.productCompositeHealth = productCompositeHealth;
    }

    @Bean
    public ReactiveHealthContributor coreServices() {
        return CompositeReactiveHealthContributor.fromMap(Map.of(
                "productHealthContrib", productHealth,
                "recommendationHealthContrib", recommendationHealth,
                "reviewHealthContrib", reviewHealth,
                "productCompositeHealthContrib", productCompositeHealth
        ));
    }
}
