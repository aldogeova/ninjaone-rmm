package com.ninjaone.service.domain.dto.command.associate.device.service;

import com.ninjaone.domain.valueobject.AssociateServiceStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class AsociateDeviceServiceResponse {

    private String id;
    private String deviceId;
    private AssociateServiceStatus associateServiceStatus;
    private List<DeviceServiceResponse> deviceServices;

}
