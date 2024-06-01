package com.mercadolibre.rampup_juan_castano.config;

import com.mercadolibre.metrics.datadog.DatadogMetricCollector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ManageMetricConfig {

    @Bean
    public DatadogMetricCollector datadogMetricCollector() {
        return new DatadogMetricCollector();
    }
}
