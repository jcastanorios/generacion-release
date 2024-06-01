package com.mercadolibre.rampup_juan_castano.dtos;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PagingDTO {
    private long total;
    private int limit;
    private int offset;
}
