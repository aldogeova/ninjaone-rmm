package com.ninjaone.service.domain.dto.command.service.type;

import com.ninjaone.domain.base.BasicResponse;
import com.ninjaone.domain.base.DefaultResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class ServiceTypeResponse extends DefaultResponse {

    @NotNull
    private final BasicResponse service;

    @NotNull
    private final UUID typeDeviceId;

    @NotNull
    private final BigDecimal price;
}
