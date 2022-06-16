package com.ninjaone.domain.base;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@SuperBuilder
public class BasicResponse {
    @NotNull
    private final UUID id;
    @NotNull
    private final String name;
}
