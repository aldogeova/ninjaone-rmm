package com.ninjaone.inventory.domain.dto.command.operative.system;

import com.ninjaone.domain.base.DefaultResponse;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class OperativeSystemResponse extends DefaultResponse {
    @NotNull
    private final String name;
    private final String description;

}
