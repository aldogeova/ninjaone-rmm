package com.ninjaone.inventory.domain.dto.command.device.classification;

import com.ninjaone.domain.base.DefaultResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class DeviceClassificationResponse extends DefaultResponse {
    @NotNull
    private final String name;
    private final String description;

}
