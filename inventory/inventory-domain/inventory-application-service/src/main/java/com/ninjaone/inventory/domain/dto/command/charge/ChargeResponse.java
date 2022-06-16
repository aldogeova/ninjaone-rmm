package com.ninjaone.inventory.domain.dto.command.charge;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
@ApiModel(description = "This model represents the response to a client when need a charge associated to a device or all devices.")
public class ChargeResponse {

    @ApiModelProperty(name = "price", required = true, value = "Total price related to a device(s)", example = "21.00")
    @NotNull
    private final BigDecimal price;

    @ApiModelProperty(name = "message", required = true, value = "The response of the request", example = "There are some services no validated")
    @NotNull
    private final String message;


}
