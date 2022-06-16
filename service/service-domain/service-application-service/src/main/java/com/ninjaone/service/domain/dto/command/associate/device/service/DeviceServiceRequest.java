package com.ninjaone.service.domain.dto.command.associate.device.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class DeviceServiceRequest {
    private String id;
    private String serviceTypeId;
    private String typeDeviceId;
}
