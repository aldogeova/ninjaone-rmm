package com.ninjaone.inventory.domain.ports.output.repository;

import com.ninjaone.domain.valueobject.CustomerId;
import com.ninjaone.domain.valueobject.DeviceId;
import com.ninjaone.domain.valueobject.ServiceTypeId;
import com.ninjaone.domain.valueobject.TypeDeviceId;
import com.ninjaone.inventory.domain.entity.DeviceService;
import com.ninjaone.inventory.domain.valueobject.DeviceClassificationId;
import com.ninjaone.inventory.domain.valueobject.DeviceServiceId;
import com.ninjaone.inventory.domain.valueobject.OperativeSystemId;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DeviceServiceRepository {

    DeviceService save(DeviceService deviceService);

    Optional<DeviceService> findById(DeviceServiceId deviceServiceId);

    Optional<DeviceService> findTopByDeviceIdAndServiceTypeId(DeviceId deviceId, ServiceTypeId serviceTypeId);

    List<DeviceService> findByDeviceId(DeviceId deviceId);

    List<DeviceService> findByDeviceIdAndEnabled(DeviceId deviceId, boolean enabled);

    List<DeviceService> findByDeviceCustomerIdAndEnabled(CustomerId customerId, boolean enabled);

    List<DeviceService> findByDeviceCustomerIdAndDeviceEnabledTrue(CustomerId customerId);

    List<DeviceService> findByDeviceIdAndDeviceEnabledTrue(DeviceId deviceId);


    List<DeviceService> findByDeviceTypeDeviceIdAndEnabled(TypeDeviceId typeDeviceId, boolean enabled);

    List<DeviceService> findByDeviceTypeDeviceOperativeSystemIdAndEnabled(OperativeSystemId operativeSystemId, boolean enabled);

    List<DeviceService> findByDeviceTypeDeviceDeviceClassificationIdAndEnabled(DeviceClassificationId deviceClassificationId, boolean enabled);

}
