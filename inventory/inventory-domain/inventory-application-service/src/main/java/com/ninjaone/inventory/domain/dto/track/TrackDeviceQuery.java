package com.ninjaone.inventory.domain.dto.track;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class TrackDeviceQuery {
    @NonNull
    private final UUID deviceTrackingId;
}
