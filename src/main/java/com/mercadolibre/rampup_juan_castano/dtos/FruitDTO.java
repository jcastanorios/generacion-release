package com.mercadolibre.rampup_juan_castano.dtos;

import java.util.Date;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mercadolibre.rampup_juan_castano.util.Messages;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Esta clase permite gestionar la información que conforma una fruta
 *
 * @author jucastano
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
public class FruitDTO {


	private String id;
	@NotNull(message = Messages.NOT_NULL)
	@Pattern(regexp = "[a-zA-Z ]{1,50}", message = Messages.NAME_NOT_VALID)
	private String name;
	@Min(value = 1, message = Messages.GREATHER_THAN_ZERO)
	private int quantity;
	@DecimalMin(value = "0.1", message = Messages.FLOAT_GREATHER_THAN_ZERO)
	private double price;
	@JsonProperty("date_created")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Date dateCreated;
	@JsonProperty("date_last_updated")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Date lastUpdated;
	private String owner;
	private String status;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getPrice() {
		return price;
	}

	public String getStatus() {
		return status;
	}
}
