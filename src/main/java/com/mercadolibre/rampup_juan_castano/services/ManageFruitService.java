package com.mercadolibre.rampup_juan_castano.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import com.mercadolibre.ds.common.types.FieldType;
import com.mercadolibre.ds.common.types.SortOrder;
import com.mercadolibre.dsclient.SearchBuilder;
import com.mercadolibre.dsclient.config.DsClientConfiguration;
import com.mercadolibre.dsclient.exception.DsClientException;
import com.mercadolibre.dsclient.impl.EntityDsClient;
import com.mercadolibre.dsclient.response.search.SearchResponse;
import com.mercadolibre.dsclient.search.QueryBuilders;
import com.mercadolibre.dsclient.search.SortBuilders;
import com.mercadolibre.dsclient.search.builders.query.QueryBuilder;
import com.mercadolibre.json.exception.JsonException;
import com.mercadolibre.rampup_juan_castano.dtos.*;
import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.mercadolibre.kvsclient.ContainerKvsLowLevelClient;
import com.mercadolibre.kvsclient.exceptions.KvsException;
import com.mercadolibre.kvsclient.item.Item;
import com.mercadolibre.metrics.datadog.DatadogMetricCollector;
import com.mercadolibre.rampup_juan_castano.util.Constants;
import com.mercadolibre.rampup_juan_castano.util.Messages;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Esta clase permite mantener la gestión de frutas en cuanto a almacenarlas,
 * actualizarlas y eliminarlas
 *
 * @author jucastano
 * @version 0.0.1
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ManageFruitService {

    private final ContainerKvsLowLevelClient containerKvsLowLevelClient;
    private final DatadogMetricCollector datadogMetricCollector;

    /**
     * Este método permite consulta una fruta por su identificador
     *
     * @param idFruit key en el kvs
     * @return información referente a que ha ocurrido un error o por el contrario se encontró información correspondiente y se retorno
     */
    public ResponseEntity<String> getFruitById(String idFruit) {

        try {
            Item item = containerKvsLowLevelClient.get(idFruit);
            if (Objects.isNull(item)) {
                return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(Messages.FRUIT_NOT_FOUND);
            }
            datadogMetricCollector.count(Constants.NAME_DD_FRUIT_METRIC, 1, Constants.ACTION_GET);
            return ResponseEntity.status(HttpStatus.SC_OK).body(item.getValueAsString());
        } catch (KvsException | JsonException exception) {
            log.error(Messages.ERROR_SEARCH_FRUIT, exception);
            return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body(Messages.ERROR_SEARCH_FRUIT);
        }
    }
}