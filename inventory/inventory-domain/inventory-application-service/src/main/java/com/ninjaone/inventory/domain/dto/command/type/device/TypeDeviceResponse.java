package com.ninjaone.inventory.domain.dto.command.type.device;

import com.ninjaone.domain.base.BasicResponse;
import com.ninjaone.domain.base.DefaultResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class TypeDeviceResponse extends DefaultResponse {
    @NotNull
    private final BasicResponse operativeSystem;
    @NotNull
    private final BasicResponse deviceClassification;
    @NotNull
    private final String description;

}
