package com.ninjaone.service.domain.dto.command.associate.device.service;

import com.ninjaone.domain.valueobject.Money;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class DeviceServiceResponse {

    private String serviceTypeId;
    private Money price;
    private Boolean validated;
    private String message;

}
