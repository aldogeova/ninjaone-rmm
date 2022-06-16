package com.ninjaone.inventory.domain.dto.command.device;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@ApiModel(description = "This model represents a service associated with device.")
public class DeviceServiceAssociateCommand {

    @NonNull
    @ApiModelProperty(name = "serviceTypeId", required = true, example = "2c9a80a0-6d68-cf0c-016d-68cf2b877760",  value = "The identifier of a service type associate.")
    private final String serviceTypeId;

    @ApiModelProperty(name = "price", required = false, example = "7.00",  value = "The price of a service type associate.", dataType = "java.math.BigDecimal")
    private final BigDecimal price;
}

