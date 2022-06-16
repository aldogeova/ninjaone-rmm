package com.ninjaone.inventory.domain.ports.output.repository;

import com.ninjaone.domain.valueobject.CustomerId;
import com.ninjaone.domain.valueobject.DeviceId;
import com.ninjaone.domain.valueobject.TypeDeviceId;
import com.ninjaone.inventory.domain.entity.Device;
import com.ninjaone.inventory.domain.valueobject.TrackingId;

import java.util.Optional;

public interface DeviceRepository {

    Device save(Device device);
    Optional<Device> findById(DeviceId deviceId);

    Optional<Device> findByTrackingId(TrackingId trackingId);
    Optional<Device> findTopByCustomerIdAndTypeDeviceIdAndSystemName(CustomerId customerId, TypeDeviceId typeDeviceId, String stripAccents);

    Optional<Device> findTopByCustomerIdAndTypeDeviceIdAndSystemNameAndIdNot(CustomerId customerId, TypeDeviceId typeDeviceId, String stripAccents, DeviceId deviceId);
}
