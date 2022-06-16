package com.ninjaone.inventory.domain.dto.command.device;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@ApiModel(description = "This model represents the command to create a new device.")
public class CreateDeviceCommand {

    @NotNull
    @ApiModelProperty(name = "typeDeviceId", required = true, example = "4028ab42-813e-3a09-0181-3e3f6a9e0000", value = "The identifier of a type device associate.")
    private final String typeDeviceId;

    @NotNull
    @ApiModelProperty(name = "customerID", required = true, example = "2c9a80a0-6d68-cf0c-016d-68cf2b877760",  value = "The identifier of a customer associate.")
    private final String customerID;

    @NotNull
    @ApiModelProperty(name = "systemName", required = true, example = "W10ABC0001",  value = "The name associated to device. This name is unique by customer and type device")
    private final String systemName;

    @NotNull
    @ApiModelProperty(name = "deviceServices", required = true, value = "A list of services type associate to a device",
    example = "[{ \"serviceTypeId\":\"2c9a80a0-6d68-cf0c-016d-68cf2b877760\" }, { \"serviceTypeId\":\"4028ab42-813e-3a09-0181-3e3f6a9e0000\" }, { \"serviceTypeId\":\"2c9a80a0-6d68-cf0c-016d-68cf2c870019\" }]")
    private final List<DeviceServiceAssociateCommand> deviceServices;

}
