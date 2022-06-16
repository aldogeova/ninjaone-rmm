package com.ninjaone.inventory.domain;

import com.ninjaone.domain.valueobject.DeviceId;
import com.ninjaone.domain.valueobject.DeviceStatus;
import com.ninjaone.inventory.domain.dto.message.AssociateServiceResponse;
import com.ninjaone.inventory.domain.entity.Device;
import com.ninjaone.inventory.domain.ports.input.message.listener.associateservices.AssociateServicesResponseMessageListener;
import com.ninjaone.inventory.domain.ports.output.repository.DeviceRepository;
import com.ninjaone.inventory.domain.ports.output.repository.DeviceServiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import static com.ninjaone.utils.NinjaUuiiUtils.identifierToUuid;

@Slf4j
@Validated
@Service
public class AssociateServicesResponseMessageListenerImpl implements AssociateServicesResponseMessageListener {

    private final DeviceServiceRepository deviceServiceRepository;
    private final DeviceRepository deviceRepository;

    public AssociateServicesResponseMessageListenerImpl(DeviceServiceRepository deviceServiceRepository,
                                                        DeviceRepository deviceRepository) {
        this.deviceServiceRepository = deviceServiceRepository;
        this.deviceRepository = deviceRepository;
    }

    @Override
    public void servicesAssociated(AssociateServiceResponse associateServiceResponse) {
        Device device = deviceRepository.findById(new DeviceId(identifierToUuid(associateServiceResponse.getDeviceId())))
                .orElseThrow(() -> new IllegalArgumentException("Device not found"));

        device.setEnabled(true);
        device.setDeviceStatus(DeviceStatus.VALIDATED);
        deviceRepository.save(device);

        associateServiceResponse.getDeviceServices().forEach(deviceService -> {
            deviceService.setEnabled(true);
            deviceServiceRepository.save(deviceService);
        });
    }

    @Override
    public void servicesNoAssociated(AssociateServiceResponse associateServiceResponse) {

        Device device = deviceRepository.findById(new DeviceId(identifierToUuid(associateServiceResponse.getDeviceId())))
                .orElseThrow(() -> new IllegalArgumentException("Device not found"));

        device.setDeviceStatus(DeviceStatus.INVALID);
        device.setEnabled(device.getEnabled() != null ? device.getEnabled().booleanValue() : true);
        deviceRepository.save(device);


        associateServiceResponse.getDeviceServices().forEach(deviceService -> {
            deviceService.setEnabled(false);
            deviceServiceRepository.save(deviceService);
        });
    }
}
