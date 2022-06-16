package com.ninjaone.inventory.domain.services.charge;

import com.ninjaone.domain.valueobject.CustomerId;
import com.ninjaone.domain.valueobject.DeviceId;
import com.ninjaone.domain.valueobject.Money;
import com.ninjaone.inventory.domain.dto.command.charge.ChargeResponse;
import com.ninjaone.inventory.domain.entity.DeviceService;
import com.ninjaone.inventory.domain.entity.OperativeSystem;
import com.ninjaone.inventory.domain.exception.InventoryDomainException;
import com.ninjaone.inventory.domain.ports.output.repository.DeviceServiceRepository;
import com.ninjaone.inventory.domain.services.device.DeviceCreateHelper;
import com.ninjaone.inventory.domain.valueobject.OperativeSystemId;
import jdk.dynalink.linker.LinkerServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class ChargeApplicationHelper {

    private final DeviceServiceRepository deviceServiceRepository;
    private final DeviceCreateHelper deviceCreateHelper;

    public ChargeApplicationHelper(DeviceServiceRepository deviceServiceRepository, DeviceCreateHelper deviceCreateHelper) {
        this.deviceServiceRepository = deviceServiceRepository;
        this.deviceCreateHelper = deviceCreateHelper;
    }


    public ChargeResponse byCustomer(UUID customerId) {
        deviceCreateHelper.checkCustomer(customerId);
        List<DeviceService> deviceServices = deviceServiceRepository.findByDeviceCustomerIdAndDeviceEnabledTrue(new CustomerId(customerId));
        Money totalCharge = Money.ZERO;
        String message = deviceServices.stream().anyMatch(ds -> !ds.getEnabled()) ? "Some services aren't validated" : "";

        for (DeviceService deviceService : deviceServices) {
            if(deviceService.getEnabled()) {
               totalCharge = new Money(totalCharge.add(deviceService.getPrice()).getCost());
            }
        }

        return ChargeResponse.builder()
                .message(message)
                .price(totalCharge.getCost())
                .build();
    }


    public ChargeResponse byDevice(UUID deviceId) {
        deviceCreateHelper.controlExistence(deviceId);
        List<DeviceService> deviceServices = deviceServiceRepository.findByDeviceIdAndDeviceEnabledTrue(new DeviceId(deviceId));
        Money totalCharge = Money.ZERO;
        String message = deviceServices.stream().anyMatch(ds -> !ds.getEnabled()) ? "Some services aren't validated" : "";

        for (DeviceService deviceService : deviceServices) {
            if(deviceService.getEnabled()) {
                totalCharge = new Money(totalCharge.add(deviceService.getPrice()).getCost());
            }
        }

        return ChargeResponse.builder()
                .message(message)
                .price(totalCharge.getCost())
                .build();
    }



}
