package com.mercadolibre.rampup_juan_castano.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class FruitSearchResult {
    private PagingDTO paging;
    private List<FruitDTO> results;
}
