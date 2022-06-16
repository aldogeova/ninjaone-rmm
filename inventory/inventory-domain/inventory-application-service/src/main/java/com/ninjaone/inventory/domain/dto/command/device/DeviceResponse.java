package com.ninjaone.inventory.domain.dto.command.device;

import com.ninjaone.domain.base.BasicResponse;
import com.ninjaone.domain.valueobject.DeviceStatus;
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
@ApiModel(description = "This model represents the response to a client when create a device.")
public class DeviceResponse {

    @NotNull
    @ApiModelProperty(name = "id", required = true, value = "The unique identifier of the device.")
    private final UUID id;

    @NotNull
    @ApiModelProperty(name = "typeDevice", required = true, value = "The type of device associate")
    private final BasicResponse typeDevice;

    @NotNull
    @ApiModelProperty(name = "deviceTrackingId", required = true, value = "The id of a tracking related to a device", example = "4028ab42-813e-3a09-0181-3e3f6a9e0000")
    private final UUID deviceTrackingId;

    @NotNull
    @ApiModelProperty(name = "deviceStatus", required = true, value = "The status of a device", example = "PENDING")
    private final DeviceStatus deviceStatus;

    @ApiModelProperty(name = "message", required = true, value = "The response of the request", example = "Associating services to a device")
    @NotNull
    private final String message;

    @NotNull
    @ApiModelProperty(name = "deviceServices", required = true, value = "A list of services type associate to a device",
            example = "[{ \"id\":\"2c9a80a0-6d68-cf0c-016d-68cf2b877760\", \"message\":\"PENDING\",  \"price\":7.00 }," +
                        " { \"id\":\"4028ab42-813e-3a09-0181-3e3f6a9e0000\", \"message\":\"PENDING\",  \"price\":4.00}]")
    private final List<DeviceServiceResponse> deviceServices;


}
