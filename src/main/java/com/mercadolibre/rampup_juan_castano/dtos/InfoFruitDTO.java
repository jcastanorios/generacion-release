package com.mercadolibre.rampup_juan_castano.dtos;

import com.mercadolibre.rampup_juan_castano.util.Messages;
import lombok.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class InfoFruitDTO {
    @Min(value = 1, message = Messages.GREATHER_THAN_ZERO)
    private int quantity;
    @DecimalMin(value = "0.1", message = Messages.FLOAT_GREATHER_THAN_ZERO)
    private double price;
}
