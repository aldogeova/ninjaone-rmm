package com.ninjaone.domain.base;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@SuperBuilder
public class DefaultResponse {
    @NotNull
    private final UUID id;
    @NotNull
    private final boolean enabled;
    @NotNull
    private final long createdDate;
    @NotNull
    private final long modifiedDate;
    @NotNull
    private final String message;
}
