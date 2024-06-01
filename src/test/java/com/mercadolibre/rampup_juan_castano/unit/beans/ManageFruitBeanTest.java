package com.mercadolibre.rampup_juan_castano.unit.beans;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.mercadolibre.ds.common.types.Aggregations;
import com.mercadolibre.dsclient.impl.EntityDsClient;
import com.mercadolibre.dsclient.response.search.SearchResponse;
import com.mercadolibre.kvsclient.item.Item;
import com.mercadolibre.rampup_juan_castano.dtos.FruitSearchResult;
import com.mercadolibre.rampup_juan_castano.dtos.InfoFruitDTO;
import com.mercadolibre.rampup_juan_castano.dtos.PagingDTO;
import com.mercadolibre.rampup_juan_castano.util.Constants;
import com.mercadolibre.rampup_juan_castano.util.Messages;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mercadolibre.metrics.datadog.DatadogMetricCollector;
import com.mercadolibre.kvsclient.ContainerKvsLowLevelClient;
import com.mercadolibre.kvsclient.exceptions.KvsException;
import com.mercadolibre.rampup_juan_castano.services.ManageFruitService;
import com.mercadolibre.rampup_juan_castano.dtos.FruitDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;


@ExtendWith(MockitoExtension.class)
class ManageFruitBeanTest {

    @Mock
    ContainerKvsLowLevelClient containerKvsLowLevelClient;
    @Mock
    DatadogMetricCollector datadogMetricCollector;
    @InjectMocks
    ManageFruitService manageFruitService;
    @Mock
    EntityDsClient entityDsClient;
    @Mock
    FruitSearchResult fruitSearchResult;


    @Test
    void saveFruitOk() throws Exception {
        doNothing().when(containerKvsLowLevelClient).save(any());
        doNothing().when(datadogMetricCollector).count(anyString(), anyLong(), anyString());
        FruitDTO fruitBuildTest = buildFruitDTO();
        ResponseEntity<FruitDTO> response = manageFruitService.saveFruit("test", fruitBuildTest);
        assertEquals("manzana", response.getBody().getName());
    }

    @Test
    void saveFruitException() throws Exception {
        doThrow(new KvsException("error")).when(containerKvsLowLevelClient).save(any());
        FruitDTO fruitBuildTest = buildFruitDTO();
        try {
            manageFruitService.saveFruit("test", fruitBuildTest);
        } catch (Exception e) {
            assertEquals("error", e.getMessage());
        }
    }

    @Test
    void getFruitByIdOk() throws Exception {
        doReturn(new Item()).when(containerKvsLowLevelClient).get(any());
        doNothing().when(datadogMetricCollector).count(anyString(), anyLong(), anyString());
        ResponseEntity<String> response = manageFruitService.getFruitById("1");
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void getFruitByIdException() throws Exception {
        doThrow(new KvsException("error")).when(containerKvsLowLevelClient).get(any());
        try {
            manageFruitService.getFruitById("1");
        } catch (Exception e) {
            assertEquals("error", e.getMessage());
        }
    }

    @Test
    void getFruitByIdItemNull() throws Exception {
        doReturn(null).when(containerKvsLowLevelClient).get(any());
        ResponseEntity<String> response = manageFruitService.getFruitById("1");
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertEquals(response.getBody(), Messages.FRUIT_NOT_FOUND);
    }

    @Test
    void updateFruitByIdItemNull() throws Exception {
        doReturn(null).when(containerKvsLowLevelClient).get(any());
        InfoFruitDTO infoFruitDTO = new InfoFruitDTO();
        infoFruitDTO.setPrice(1.2);
        infoFruitDTO.setQuantity(10);
        ResponseEntity<String> response = manageFruitService.updateFruit("1", infoFruitDTO);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertEquals(response.getBody(), Messages.FRUIT_NOT_FOUND);
    }

    @Test
    void updateFruitByIdOk() throws Exception {
        Item item = new Item();
        item.setKey("1");
        item.setValue(buildFruitDTO());
        doReturn(item).when(containerKvsLowLevelClient).get(any());
        doNothing().when(containerKvsLowLevelClient).update(any());
        doNothing().when(datadogMetricCollector).count(anyString(), anyLong(), anyString());
        InfoFruitDTO infoFruitDTO = new InfoFruitDTO();
        infoFruitDTO.setPrice(1.2);
        infoFruitDTO.setQuantity(10);
        ResponseEntity<String> response = manageFruitService.updateFruit("1", infoFruitDTO);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void updateFruitByIdException() throws Exception {
        Item item = new Item();
        item.setKey("1");
        item.setValue(buildFruitDTO());
        doReturn(item).when(containerKvsLowLevelClient).get(any());
        doThrow(new KvsException("error")).when(containerKvsLowLevelClient).update(any());
        try {

            InfoFruitDTO infoFruitDTO = new InfoFruitDTO();
            infoFruitDTO.setPrice(1.2);
            infoFruitDTO.setQuantity(10);
            ResponseEntity<String> response = manageFruitService.updateFruit("1", infoFruitDTO);
        } catch (Exception e) {
            assertEquals("error", e.getMessage());
        }
    }

    @Test
    void deleteFruitByIdItemNull() throws Exception {
        doReturn(null).when(containerKvsLowLevelClient).get(any());
        ResponseEntity<FruitDTO> response = manageFruitService.deleteFruit("1");
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    void deleteFruitByIdOk() throws Exception {
        Item item = new Item();
        item.setKey("1");
        item.setValue(buildFruitDTO());
        doReturn(item).when(containerKvsLowLevelClient).get(any());
        doNothing().when(containerKvsLowLevelClient).update(any());
        doNothing().when(datadogMetricCollector).count(anyString(), anyLong(), anyString());
        ResponseEntity<FruitDTO> response = manageFruitService.deleteFruit("1");
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void deleteFruitByIdException() throws Exception {
        Item item = new Item();
        item.setKey("1");
        item.setValue(buildFruitDTO());
        doNothing().when(datadogMetricCollector).count(anyString(), anyLong(), anyString());
        doReturn(item).when(containerKvsLowLevelClient).get(any());
        doThrow(new KvsException("error")).when(containerKvsLowLevelClient).update(any());
        try {
            ResponseEntity<FruitDTO> response = manageFruitService.deleteFruit("1");
        } catch (Exception e) {
            assertEquals("error", e.getMessage());
        }
    }

    @Test
    void searchFruitFindOneFruitMatchWithParams() throws Exception {

        List<FruitDTO> documents = Arrays.asList(FruitDTO.builder()
                .id("id")
                .name("test")
                .price(1F)
                .quantity(1)
                .dateCreated(new Date())
                .lastUpdated(new Date())
                .owner("test-owner")
                .status("comestible")
                .build());

        SearchResponse<FruitDTO> searchResponse = new SearchResponse<>(1L, documents, Collections.emptyList(), new Aggregations(), 200);

        FruitSearchResult fruitSearchResult = FruitSearchResult.builder().paging(PagingDTO
                .builder()
                .total(1)
                .offset(0)
                .limit(5)
                .build())
                .results(new ArrayList<>(searchResponse.getDocuments()))
                .build();


        when(manageFruitService.searchFruits(anyString(), anyString(), anyInt(), anyInt()))
                .thenReturn(ResponseEntity.status(org.apache.http.HttpStatus.SC_OK).body(fruitSearchResult));
        ResponseEntity<FruitSearchResult> fruitSearchResultResponseEntity = manageFruitService.searchFruits("1", "1", 0, 100);
        assertEquals(200, fruitSearchResultResponseEntity.getStatusCode());

    }

    private FruitDTO buildFruitDTO() {
        return FruitDTO.builder()
                .id("1")
                .name("manzana")
                .quantity(12)
                .price(12.3)
                .owner("test")
                .status(Constants.STATUS_EDIBLE)
                .dateCreated(new Date())
                .lastUpdated(new Date())
                .build();
    }
}
