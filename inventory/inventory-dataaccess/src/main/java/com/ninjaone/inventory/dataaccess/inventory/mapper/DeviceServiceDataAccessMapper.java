package com.ninjaone.inventory.dataaccess.inventory.mapper;

import com.ninjaone.domain.valueobject.DeviceServiceStatus;
import com.ninjaone.domain.valueobject.Money;
import com.ninjaone.domain.valueobject.ServiceTypeId;
import com.ninjaone.inventory.dataaccess.inventory.entity.DeviceEntity;
import com.ninjaone.inventory.dataaccess.inventory.entity.DeviceServiceEntity;
import com.ninjaone.inventory.dataaccess.inventory.entity.TypeDeviceEntity;
import com.ninjaone.inventory.domain.entity.DeviceService;
import com.ninjaone.inventory.domain.entity.TypeDevice;
import com.ninjaone.inventory.domain.valueobject.DeviceServiceId;
import org.springframework.stereotype.Component;
import static com.ninjaone.utils.NinjaUuiiUtils.identifierToUuid;


@Component
public class DeviceServiceDataAccessMapper {


    public DeviceServiceEntity deviceServiceToDeviceServiceEntity(DeviceService deviceService) {

        if(deviceService.getDeviceId() != null) {
            return DeviceServiceEntity.builder()
                    .id(deviceService.getId().getValue())
                    .serviceTypeId(deviceService.getServiceTypeId().getValue())
                    .price(deviceService.getPrice().getCost())
                    .message(deviceService.getMessage())
                    .status(deviceService.getStatus())
                    .device(DeviceEntity.builder().id(deviceService.getDeviceId().getValue()).build())
                    .enabled(deviceService.getEnabled() != null ? deviceService.getEnabled() : false)
                    .build();
        }else{
            return DeviceServiceEntity.builder()
                    .id(deviceService.getId().getValue())
                    .serviceTypeId(deviceService.getServiceTypeId().getValue())
                    .price(deviceService.getPrice().getCost())
                    .message(deviceService.getMessage())
                    .status(deviceService.getStatus())
                    .enabled(deviceService.getEnabled() != null ? deviceService.getEnabled() : false)
                    .build();
        }

    }

    public DeviceService deviceServiceEntityToDeviceService(DeviceServiceEntity deviceServiceEntity) {
        return DeviceService.builder()
                .id(new DeviceServiceId(deviceServiceEntity.getId()))
                .serviceTypeId(new ServiceTypeId(deviceServiceEntity.getServiceTypeId()))
                .price(new Money(deviceServiceEntity.getPrice()))
                .message(deviceServiceEntity.getMessage() != null ? deviceServiceEntity.getMessage() : "")
                .status(deviceServiceEntity.getStatus())
                .enabled(deviceServiceEntity.isEnabled())
                .build();
    }

}
