package ca.jarvis.iex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    private Logger logger = LoggerFactory.getLogger(AppConfig.class);

    @Bean
    public MarketDataConfig marketDataConfig() {
        // Instantiate and configure MarketDataConfig
        return new MarketDataConfig();
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    // You can add more beans or configurations as needed
}

