package com.ninjaone.service.domain.dto.command.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
public class ServiceCommand {
    @NotNull
    private final String name;
    private final String description;
}
