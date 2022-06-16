package com.ninjaone.inventory.domain.dto.command.device;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@ApiModel(description = "This model represents the response to a service associate a device.")
public class DeviceServiceSearchResponse {

    @NotNull
    @ApiModelProperty(name = "id", required = true, example = "2c9a80a0-6d68-cf0c-016d-68cf2b877760",  value = "The identifier of a device service")
    private final UUID id;

    @NotNull
    @ApiModelProperty(name = "serviceTypeId", required = true, example = "2c9a80a0-6d68-cf0c-016d-68cf2b877760",  value = "The identifier of a service type associate.")
    private final UUID serviceTypeId;

    @NotNull
    @ApiModelProperty(name = "price", required = true, example = "7.00",  value = "The price of a service type associate.", dataType = "java.math.BigDecimal")
    private final BigDecimal price;

    @ApiModelProperty(name = "status", required = true, example = "PENDING",  value = "Indicates if the device service entity are synchronized with other service.", dataType = "java.math.BigDecimal")
    private final String status;

    @NotNull
    @ApiModelProperty(name = "message", required = true, value = "The response of the request", example = "Associating services to a device")
    private final String message;

}
