package com.ninjaone.service.domain.ports.input.message.listener;

import com.ninjaone.service.domain.dto.command.associate.device.service.AssociateDeviceServiceRequest;

public interface AssociateServicesRequestMessageListener {

    void review(AssociateDeviceServiceRequest associateDeviceServiceRequest);

}
