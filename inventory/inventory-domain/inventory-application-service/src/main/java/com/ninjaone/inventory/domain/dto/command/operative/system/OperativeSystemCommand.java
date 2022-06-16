package com.ninjaone.inventory.domain.dto.command.operative.system;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
public class OperativeSystemCommand {
    @NotNull
    private final String name;
    private final String description;
}
