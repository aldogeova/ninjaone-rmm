package com.ninjaone.service.domain.dto.command.service.type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
public class ServiceTypeCommand {

    @NotNull
    private final String serviceId;

    @NotNull
    private final String typeDeviceId;

    @NotNull
    private final BigDecimal price;
}
