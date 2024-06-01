package com.mercadolibre.rampup_juan_castano.config;

import com.mercadolibre.rampup_juan_castano.util.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mercadolibre.kvsclient.ContainerKvsLowLevelClient;
import com.mercadolibre.kvsclient.kvsapi.KvsapiConfiguration;
import com.mercadolibre.kvsclient.kvsapi.KvsapiLowLevelClient;
import com.mercadolibre.restclient.http.HttpMethod;

@Configuration
public class ManageKVSConfig {


	/**
	 * Método que permite construir una instancia de container kvs a partir de una
	 * configuración y un nombre
	 *
	 * @return container kvs
	 */
	@Bean
	public ContainerKvsLowLevelClient containerKvsLowLevelClient(){

		KvsapiConfiguration config = KvsapiConfiguration.builder().withSocketTimeout(Constants.SOCKET_TIMEOUT).withMaxWait(Constants.MAX_WAIT)
				.withConnectionTimeout(Constants.CONNECTION_TIMEOUT).withMaxConnections(Constants.MAX_CONNECTIONS).withMaxConnectionsPerRoute(Constants.MAX_CONNECTIONS_PER_ROUTE).withMaxRetries(Constants.MAX_RETRIES)
				.withRetryDelay(Constants.RETRY_DELAY).withRetryableMethods(HttpMethod.GET, HttpMethod.OPTIONS, HttpMethod.HEAD).build();

		return new ContainerKvsLowLevelClient(new KvsapiLowLevelClient(config),
				System.getenv("KEY_VALUE_STORE_SBOXQ5DLOZ5FJT_CONTAINER_NAME"));
	}

}