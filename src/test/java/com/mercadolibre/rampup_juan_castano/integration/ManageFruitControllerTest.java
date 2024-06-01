package com.mercadolibre.rampup_juan_castano.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.mercadolibre.rampup_juan_castano.dtos.FruitSearchResult;
import com.mercadolibre.rampup_juan_castano.dtos.InfoFruitDTO;
import com.mercadolibre.rampup_juan_castano.dtos.PagingDTO;
import com.mercadolibre.rampup_juan_castano.util.Constants;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mercadolibre.rampup_juan_castano.services.ManageFruitService;
import com.mercadolibre.rampup_juan_castano.controller.ManageFruitController;
import com.mercadolibre.rampup_juan_castano.dtos.FruitDTO;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ManageFruitControllerTest {

    @Mock
    ManageFruitService manageFruitsBean;

    @InjectMocks
    ManageFruitController manageFruitController;

    @Test
    void saveFruit() throws Exception {
        FruitDTO fruitBuildTest = FruitDTO.builder()
                .id("1")
                .name("naranja")
                .quantity(12)
                .price(12.3)
                .owner("test")
                .status(Constants.STATUS_EDIBLE)
                .dateCreated(new Date())
                .lastUpdated(new Date())
                .build();
        doReturn(ResponseEntity.status(HttpStatus.SC_OK).body(fruitBuildTest)).when(manageFruitsBean).saveFruit(any(), any());
        ResponseEntity<FruitDTO>response=manageFruitController.saveFruit("test", fruitBuildTest);
        assertEquals("naranja", response.getBody().getName());
    }

    @Test
    void getFruitById() throws Exception {
        FruitDTO fruitBuildTest = FruitDTO.builder()
                .id("0b3acd89-38b3-42d5-a8a8-11d6b55eabad1")
                .name("naranja")
                .quantity(12)
                .price(12.3)
                .owner("test")
                .status(Constants.STATUS_EDIBLE)
                .dateCreated(new Date())
                .lastUpdated(new Date())
                .build();
        doReturn(ResponseEntity.status(HttpStatus.SC_OK).body(fruitBuildTest.toString()))
                .when(manageFruitsBean).getFruitById(any());
        manageFruitController.getFruitById("0b3acd89-38b3-42d5-a8a8-11d6b55eabad1");
        assertEquals("0b3acd89-38b3-42d5-a8a8-11d6b55eabad1", fruitBuildTest.getId());
    }

    @Test
    void updateFruit() throws Exception {
        FruitDTO fruitBuildTest = FruitDTO.builder()
                .id("d4c07bb8-8263-404d-a08f-fa54e1cb9fb31")
                .name("pera")
                .quantity(12)
                .price(12.3)
                .owner("test")
                .status(Constants.STATUS_EDIBLE)
                .dateCreated(new Date())
                .lastUpdated(new Date())
                .build();


        doReturn(ResponseEntity.status(HttpStatus.SC_OK).body(fruitBuildTest.toString()))
                .when(manageFruitsBean).updateFruit(any(), any());
        InfoFruitDTO infoFruitDTO = new InfoFruitDTO();
        infoFruitDTO.setPrice(1.2);
        infoFruitDTO.setQuantity(10);
        manageFruitController.updateFruit("d4c07bb8-8263-404d-a08f-fa54e1cb9fb31", infoFruitDTO);
        assertEquals(12.3, fruitBuildTest.getPrice());
    }

    @Test
    void deleteFruit() throws Exception {
        FruitDTO fruitBuildTest = FruitDTO.builder()
                .id("d4c07bb8-8263-404d-a08f-fa54e1cb9fb31")
                .name("pera")
                .quantity(12)
                .price(12.3)
                .owner("test")
                .status(Constants.STATUS_ROTTEN)
                .dateCreated(new Date())
                .lastUpdated(new Date())
                .build();
        doReturn(ResponseEntity.status(HttpStatus.SC_OK).body(fruitBuildTest.toString()))
                .when(manageFruitsBean).deleteFruit(any());
        manageFruitController.deleteFruit("d4c07bb8-8263-404d-a08f-fa54e1cb9fb31");
        assertEquals("podrido", fruitBuildTest.getStatus());
    }

    @Test
    void searchFruit() throws Exception {

        FruitDTO fruitBuildTest = FruitDTO.builder()
                .id("d4c07bb8-8263-404d-a08f-fa54e1cb9fb31")
                .name("pera")
                .quantity(12)
                .price(12.3)
                .owner("test")
                .status(Constants.STATUS_ROTTEN)
                .dateCreated(new Date())
                .lastUpdated(new Date())
                .build();
        List<FruitDTO> listaFrutas = new ArrayList<>();
        listaFrutas.add(fruitBuildTest);

        FruitSearchResult fruitSearchResultBuild = FruitSearchResult.builder().paging(PagingDTO
                .builder()
                .total(1)
                .offset(0)
                .limit(10)
                .build())
                .results(listaFrutas)
                .build();

        doReturn(ResponseEntity.status(HttpStatus.SC_OK).body(fruitSearchResultBuild))
                .when(manageFruitsBean).searchFruits(anyString(), anyString(), anyInt(), anyInt());
        manageFruitController.searchFruits("pera", "podrido", 0, 10);
        assertEquals("podrido", fruitSearchResultBuild.getResults().get(0).getStatus());
    }
}
