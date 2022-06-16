package com.ninjaone.inventory.domain.dto.command.device.classification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
public class DeviceClassificationCommand {
    @NotNull
    private final String name;
    private final String description;
}
