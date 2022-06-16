package com.ninjaone.inventory.messaging.mapper;

import com.ninjaone.domain.valueobject.DeviceId;
import com.ninjaone.domain.valueobject.Money;
import com.ninjaone.domain.valueobject.ServiceTypeId;
import com.ninjaone.inventory.domain.dto.message.AssociateServiceResponse;
import com.ninjaone.inventory.domain.entity.Device;
import com.ninjaone.inventory.domain.event.AssociateServicesEvent;
import com.ninjaone.inventory.domain.valueobject.DeviceServiceId;
import com.ninjaone.kafka.associate.service.avro.model.*;
import org.springframework.stereotype.Component;
import static com.ninjaone.utils.NinjaUuiiUtils.identifierToUuid;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class DeviceMessagingDataMapper {

    public AssociateServiceRequestAvroModel associateServiceEventToAssociateServiceRequestAvroModel(AssociateServicesEvent associateServicesEvent) {
        Device device = associateServicesEvent.getDevice();
        return AssociateServiceRequestAvroModel.newBuilder()
                .setId(UUID.randomUUID().toString())
                .setSagaId("")
                .setDeviceId(device.getId().getValue().toString())
                .setCreatedAt(associateServicesEvent.getCreatedAt().toInstant())
                .setAssociateServiceStatus(AssociateServiceStatus.PENDING)
                .setDeviceServices(device.getDeviceServices().stream().map(deviceService ->
                                        DeviceService.newBuilder()
                                                .setId(deviceService.getId().getValue().toString())
                                                .setServiceTypeId(deviceService.getServiceTypeId().getValue().toString())
                                                .setTypeDeviceId(device.getTypeDevice().getId().getValue().toString())
                                                .build()
                                ).collect(Collectors.toList()))
                .build();
    }


    public AssociateServiceResponse associateServiceResponseAvroModelToAssociateServiceResponse(AssociateServiceResponseAvroModel associateServiceResponseAvroModel) {
        return AssociateServiceResponse.builder()
                .id(associateServiceResponseAvroModel.getId() )
                .sagaId(associateServiceResponseAvroModel.getSagaId())
                .deviceId(associateServiceResponseAvroModel.getDeviceId())
                .createdAt(associateServiceResponseAvroModel.getCreatedAt())
                .associateServiceStatus(com.ninjaone.domain.valueobject.AssociateServiceStatus.valueOf(associateServiceResponseAvroModel.getAssociateServiceStatus().name()))
                .failureMessages(associateServiceResponseAvroModel.getFailureMessages())
                .deviceServices(associateServiceResponseAvroModel.getDeviceServicesResponseAvroModel().stream().map(deviceService ->
                        com.ninjaone.inventory.domain.entity.DeviceService.builder()
                                .id(new DeviceServiceId(identifierToUuid(deviceService.getId())))
                                .serviceTypeId(new ServiceTypeId(identifierToUuid(deviceService.getServiceTypeId())))
                                .status(deviceService.getValidation().name())
                                .enabled(deviceService.getValidation().name().equals("EXISTENT")?true:false)
                                .message(deviceService.getMessage())
                                .price(new Money(deviceService.getPrice()))
                                .deviceId(new DeviceId(identifierToUuid(associateServiceResponseAvroModel.getDeviceId())))
                                .build()
                ).collect(Collectors.toList()))
                .build();
    }

}

