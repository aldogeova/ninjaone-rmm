package com.ninjaone.inventory.dataaccess.inventory.mapper;

import com.ninjaone.domain.valueobject.CustomerId;
import com.ninjaone.domain.valueobject.DeviceId;
import com.ninjaone.inventory.dataaccess.inventory.entity.DeviceEntity;
import com.ninjaone.inventory.dataaccess.inventory.entity.DeviceServiceEntity;
import com.ninjaone.inventory.dataaccess.inventory.entity.TypeDeviceEntity;
import com.ninjaone.inventory.domain.entity.Device;
import com.ninjaone.inventory.domain.entity.DeviceService;
import com.ninjaone.inventory.domain.valueobject.TrackingId;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DeviceDataAccessMapper {

    private final TypeDeviceDataAccessMapper typeDeviceDataAccessMapper;

    private final DeviceServiceDataAccessMapper deviceServiceDataAccessMapper;

    public DeviceDataAccessMapper(TypeDeviceDataAccessMapper typeDeviceDataAccessMapper, DeviceServiceDataAccessMapper deviceServiceDataAccessMapper) {
        this.typeDeviceDataAccessMapper = typeDeviceDataAccessMapper;
        this.deviceServiceDataAccessMapper = deviceServiceDataAccessMapper;
    }

    public DeviceEntity deviceToDeviceEntity(Device device) {
        DeviceEntity deviceEntity;

        if(device.getDeviceServices() != null){
            deviceEntity = DeviceEntity.builder()
                    .id(device.getId().getValue())
                    .customerId(device.getCustomerId().getValue())
                    .trackingId(device.getTrackingId().getValue())
                    .systemName(device.getSystemName())
                    .typeDevice(TypeDeviceEntity.builder()
                            .id(device.getTypeDevice().getId().getValue())
                            .build())
                    .deviceServices(deviceServicesToDeviceServicesEntity(device.getDeviceServices()))
                    .deviceStatus(device.getDeviceStatus())
                    .failureMessages(device.getFailureMessages() != null ?
                            String.join(Device.FAILURE_MESSAGE_DELIMITER, device.getFailureMessages()) : ""
                    )
                    .build();

            deviceEntity.getDeviceServices().forEach(deviceServiceEntity -> deviceServiceEntity.setDevice(deviceEntity));
        }else{
            deviceEntity = DeviceEntity.builder()
                    .id(device.getId().getValue())
                    .customerId(device.getCustomerId().getValue())
                    .trackingId(device.getTrackingId().getValue())
                    .systemName(device.getSystemName())
                    .enabled(device.getEnabled())
                    .typeDevice(TypeDeviceEntity.builder()
                            .id(device.getTypeDevice().getId().getValue())
                            .build())
                    .deviceStatus(device.getDeviceStatus())
                    .failureMessages(device.getFailureMessages() != null ?
                            String.join(Device.FAILURE_MESSAGE_DELIMITER, device.getFailureMessages()) : ""
                    )
                    .build();
        }

        return deviceEntity;

    }

    public Device deviceEntityToDevice(DeviceEntity deviceEntity) {
        Device device = Device.builder()
                .deviceId(new DeviceId(deviceEntity.getId()))
                .customerId(new CustomerId(deviceEntity.getCustomerId()))
                .systemName(deviceEntity.getSystemName())
                .typeDevice(typeDeviceDataAccessMapper.typeDeviceEntityToTypeDevice(deviceEntity.getTypeDevice()))
                .trackingId(new TrackingId(deviceEntity.getTrackingId()))
                .deviceStatus(deviceEntity.getDeviceStatus())
                .failureMessages(deviceEntity.getFailureMessages().isEmpty() ?
                         new ArrayList<>() : new ArrayList<>(Arrays.asList(deviceEntity.getFailureMessages().split(Device.FAILURE_MESSAGE_DELIMITER))))
                .build();
        return device;
    }

    private List<DeviceService> deviceServicesEntityToDeviceServices(List<DeviceServiceEntity> deviceServices) {
        return deviceServices.stream()
                .map(deviceServiceDataAccessMapper::deviceServiceEntityToDeviceService)
                .collect(Collectors.toList());
    }


    private List<DeviceServiceEntity> deviceServicesToDeviceServicesEntity(List<DeviceService> deviceServices) {
            return deviceServices.stream().map(deviceServiceDataAccessMapper::deviceServiceToDeviceServiceEntity).collect(Collectors.toList());
    }

}
