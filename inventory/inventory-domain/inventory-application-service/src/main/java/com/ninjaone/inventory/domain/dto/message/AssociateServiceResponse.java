package com.ninjaone.inventory.domain.dto.message;

import com.ninjaone.domain.valueobject.AssociateServiceStatus;
import com.ninjaone.inventory.domain.dto.command.device.DeviceServiceResponse;
import com.ninjaone.inventory.domain.entity.DeviceService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class AssociateServiceResponse {
    private String id;
    private String sagaId;
    private String deviceId;
    private Instant createdAt;
    private AssociateServiceStatus associateServiceStatus;
    private List<String> failureMessages;
    private List<DeviceService> deviceServices;

}
