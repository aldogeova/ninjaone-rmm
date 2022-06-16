package com.ninjaone.service.messaging.mapper;

import com.ninjaone.kafka.associate.service.avro.model.*;
import com.ninjaone.service.domain.dto.command.associate.device.service.AssociateDeviceServiceRequest;
import com.ninjaone.service.domain.dto.command.associate.device.service.DeviceServiceRequest;
import com.ninjaone.service.domain.event.AssociatedDeviceServiceEvent;
import com.ninjaone.service.domain.event.IncorrectDeviceServiceEvent;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;
import static com.ninjaone.utils.NinjaUuiiUtils.identifierToUuid;

@Component
public class AssociateDeviceServiceMessagingDataMapper {

    public AssociateServiceResponseAvroModel associatedDeviceServiceEventToAssociateServiceResponseAvroModel(AssociatedDeviceServiceEvent associatedDeviceServicesEvent) {
        return AssociateServiceResponseAvroModel.newBuilder()
                .setId(UUID.randomUUID().toString())
                .setSagaId("")
                .setDeviceId(associatedDeviceServicesEvent.getAssociateDeviceService().getDeviceId().getValue().toString())
                .setCreatedAt(associatedDeviceServicesEvent.getCreatedAt().toInstant())
                .setAssociateServiceStatus(AssociateServiceStatus.valueOf(associatedDeviceServicesEvent.getAssociateDeviceService().getDeviceStatus().name()))
                .setFailureMessages(associatedDeviceServicesEvent.getFailureMessages())
                .setDeviceServicesResponseAvroModel(associatedDeviceServicesEvent.getAssociateDeviceService().getDeviceServicesAssociate().stream().map(deviceService ->
                        DeviceServiceResponseAvroModel.newBuilder()
                                .setId(deviceService.getId().getValue().toString())
                                .setServiceTypeId(deviceService.getServiceTypeId().toString())
                                .setMessage(deviceService.getMessage() != null ? deviceService.getMessage() : "")
                                .setPrice(deviceService.getPrice().getCost())
                                .setValidation(deviceService.getValidated() ? DeviceServiceStatus.VALIDATED : DeviceServiceStatus.INVALID)
                                .build()
                ).collect(Collectors.toList()))
                .build();
    }


    public AssociateServiceResponseAvroModel incorrectDeviceServiceEventToAssociateServiceResponseAvroModel(IncorrectDeviceServiceEvent incorrectDeviceServicesEvent) {
        return AssociateServiceResponseAvroModel.newBuilder()
                .setId(UUID.randomUUID().toString())
                .setSagaId("")
                .setDeviceId(incorrectDeviceServicesEvent.getAssociateDeviceService().getDeviceId().getValue().toString())
                .setCreatedAt(incorrectDeviceServicesEvent.getCreatedAt().toInstant())
                .setAssociateServiceStatus(AssociateServiceStatus.valueOf(incorrectDeviceServicesEvent.getAssociateDeviceService().getDeviceStatus().name()))
                .setFailureMessages(incorrectDeviceServicesEvent.getFailureMessages())
                .setDeviceServicesResponseAvroModel(incorrectDeviceServicesEvent.getAssociateDeviceService().getDeviceServicesAssociate().stream().map(deviceService ->
                        DeviceServiceResponseAvroModel.newBuilder()
                                .setId(deviceService.getId().getValue().toString())
                                .setServiceTypeId(deviceService.getServiceTypeId().toString())
                                .setMessage(deviceService.getMessage() != null ? deviceService.getMessage() : "")
                                .setPrice(deviceService.getPrice().getCost())
                                .setValidation(deviceService.getValidated() ? DeviceServiceStatus.VALIDATED : DeviceServiceStatus.INVALID)
                                .build()
                ).collect(Collectors.toList()))
                .build();
    }

    public AssociateDeviceServiceRequest associateServiceRequestAvroModelToAssociateDeviceServiceRequest(AssociateServiceRequestAvroModel associateServiceRequestAvroModel) {
        return AssociateDeviceServiceRequest.builder()
                .id(associateServiceRequestAvroModel.getId())
                .sagaId(associateServiceRequestAvroModel.getSagaId())
                .deviceId(associateServiceRequestAvroModel.getDeviceId())
                .associateServiceStatus(com.ninjaone.domain.valueobject.AssociateServiceStatus.valueOf(associateServiceRequestAvroModel.getAssociateServiceStatus().name()))
                .deviceServices(associateServiceRequestAvroModel.getDeviceServices().stream().map(deviceService ->
                        DeviceServiceRequest.builder()
                                .id(deviceService.getId())
                                .serviceTypeId(deviceService.getServiceTypeId())
                                .typeDeviceId(deviceService.getTypeDeviceId())
                                .build()
                ).collect(Collectors.toList()))
                .build();
    }

}
