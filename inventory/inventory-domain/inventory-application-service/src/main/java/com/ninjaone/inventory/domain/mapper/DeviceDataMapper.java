package com.ninjaone.inventory.domain.mapper;

import com.ninjaone.domain.base.BasicResponse;
import com.ninjaone.domain.valueobject.*;
import com.ninjaone.inventory.domain.dto.command.device.*;
import com.ninjaone.inventory.domain.dto.track.TrackDeviceResponse;
import com.ninjaone.inventory.domain.entity.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.ninjaone.utils.NinjaUuiiUtils.identifierToUuid;
import static com.ninjaone.utils.NinjaStringUtils.defaultFormat;
import static com.ninjaone.utils.NinjaStringUtils.stripAccents;



@Component
public class DeviceDataMapper {

    public Device createDeviceCommandOrderToDevice(CreateDeviceCommand createDeviceCommand){
        return Device.builder()
                .customerId(new CustomerId(identifierToUuid(createDeviceCommand.getCustomerID())))
                .systemName(stripAccents(defaultFormat(createDeviceCommand.getSystemName())))
                .typeDevice(deviceTypeDeviceToTypeDevice(identifierToUuid(createDeviceCommand.getTypeDeviceId())))
                .deviceStatus(DeviceStatus.PENDING)
                .deviceServices(deviceServiceCommandToDeviceService(createDeviceCommand.getDeviceServices()))
                .enabled(true)
                .build();
    }

    public List<DeviceService> deviceServiceCommandToDeviceService(List<DeviceServiceAssociateCommand> deviceServicesAssociateCommand){
        return deviceServicesAssociateCommand.stream().map(dsac ->
             DeviceService.builder()
                    .serviceTypeId(new ServiceTypeId(identifierToUuid(dsac.getServiceTypeId())))
                    .price(Money.ZERO)
                    .deviceServiceStatus(DeviceServiceStatus.PENDING)
                    .build()
        ).collect(Collectors.toList());
    }

    public DeviceResponse deviceToCreateDeviceResponse(Device device, String message){

        return DeviceResponse.builder()
                .id(device.getId().getValue())
                .deviceStatus(device.getDeviceStatus())
                .deviceTrackingId(device.getTrackingId().getValue())
                .typeDevice(BasicResponse.builder()
                        .id(device.getTypeDevice().getId().getValue())
                        .name(device.getTypeDevice().getDescription() != null ? device.getTypeDevice().getDescription() : "")
                        .build())
                .message(message)
                .deviceServices(device.getDeviceServices().stream().map(ds ->
                        DeviceServiceResponse.builder()
                                .serviceTypeId(ds.getServiceTypeId().getValue())
                                .status(ds.getStatus())
                                .message(ds.getMessage() != null ? ds.getMessage() : "")
                                .price(ds.getPrice().getCost())

                                .build()
                ).collect(Collectors.toList()))
                .build();
    }

    private TypeDevice deviceTypeDeviceToTypeDevice(UUID typeDeviceId) {
        return new TypeDevice(new TypeDeviceId(typeDeviceId));
    }

    public TrackDeviceResponse deviceToTrackDeviceResponse(Device device) {
        return TrackDeviceResponse.builder()
                .deviceTrackingId(device.getTrackingId().getValue())
                .deviceStatus(device.getDeviceStatus())
                .failureMessages(device.getFailureMessages())
                .build();
    }

    public DeviceSearchResponse deviceToDeviceSearchResponse(Device device) {
        return DeviceSearchResponse.builder()
                .id(device.getId().getValue())
                .deviceStatus(device.getDeviceStatus())
                .deviceTrackingId(device.getTrackingId().getValue())
                .systemName(device.getSystemName())
                .typeDevice(BasicResponse.builder()
                        .id(device.getTypeDevice().getId().getValue())
                        .name(device.getTypeDevice().getDescription() != null ? device.getTypeDevice().getDescription() : "")
                        .build())
                .deviceServices(device.getDeviceServices().stream().map(ds ->
                        DeviceServiceSearchResponse.builder()
                                .id(ds.getId().getValue())
                                .serviceTypeId(ds.getServiceTypeId().getValue())
                                .status(ds.getStatus())
                                .message(ds.getMessage() != null ? ds.getMessage() : "")
                                .price(ds.getPrice().getCost())
                                .status(ds.getStatus())
                                .build()
                ).collect(Collectors.toList()))
                .build();
    }


}
