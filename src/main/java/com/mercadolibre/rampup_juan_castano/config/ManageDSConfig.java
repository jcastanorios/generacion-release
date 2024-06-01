package com.mercadolibre.rampup_juan_castano.config;

import com.mercadolibre.dsclient.config.DsClientConfiguration;
import com.mercadolibre.dsclient.exception.DsClientException;
import com.mercadolibre.dsclient.impl.EntityDsClient;
import org.springframework.boot.origin.SystemEnvironmentOrigin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ManageDSConfig {
    /**
    @Bean
    public EntityDsClient entityDsClient()throws DsClientException {
        DsClientConfiguration conf = DsClientConfiguration.builder()
                .withServiceName(System.getenv("SANDBOX_SERVICE_NAME"))
                .build();
        return new EntityDsClient(conf);
    }
     */
}