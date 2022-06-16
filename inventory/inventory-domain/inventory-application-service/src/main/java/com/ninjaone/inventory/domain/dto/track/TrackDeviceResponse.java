package com.ninjaone.inventory.domain.dto.track;

import com.ninjaone.domain.valueobject.DeviceStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class TrackDeviceResponse {
    @NotNull
    private final UUID deviceTrackingId;
    @NotNull
    private final DeviceStatus deviceStatus;
    private final List<String> failureMessages;
}
