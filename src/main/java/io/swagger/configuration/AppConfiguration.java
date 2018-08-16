package io.swagger.configuration;

import io.swagger.client.FlightInfoClient;
import io.swagger.client.flightstats.FlightStatsClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    @Value("${flightStats.appId}")
    private String appId;
    @Value("$(flightStats.appKey")
    private String appKey;

    @Bean
    FlightInfoClient flightInfoClient() {
        return new FlightStatsClient(appId, appKey);
    }
}
