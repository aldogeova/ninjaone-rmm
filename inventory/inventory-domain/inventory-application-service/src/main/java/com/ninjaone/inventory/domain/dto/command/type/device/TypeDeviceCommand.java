package com.ninjaone.inventory.domain.dto.command.type.device;

import com.ninjaone.utils.NinjaUuiiUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class TypeDeviceCommand {
    @NotNull
    private final String deviceClassificationId;
    @NotNull
    private final String operativeSystemId;
    @NotNull
    private final String description;
}
