package com.ninjaone.service.domain.dto.command.associate.device.service;

import com.ninjaone.domain.valueobject.AssociateServiceStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class AssociateDeviceServiceRequest {

    private String id;
    private String sagaId;
    private String deviceId;
    private AssociateServiceStatus associateServiceStatus;
    private List<DeviceServiceRequest> deviceServices;

    public void setAssociateServiceStatus(AssociateServiceStatus associateServiceStatus) {
        this.associateServiceStatus = associateServiceStatus;
    }
}
