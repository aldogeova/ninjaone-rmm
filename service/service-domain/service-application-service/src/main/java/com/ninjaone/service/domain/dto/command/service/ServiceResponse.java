package com.ninjaone.service.domain.dto.command.service;

import com.ninjaone.domain.base.DefaultResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class ServiceResponse extends DefaultResponse {
    @NotNull
    private final String name;
    private final String description;

}
