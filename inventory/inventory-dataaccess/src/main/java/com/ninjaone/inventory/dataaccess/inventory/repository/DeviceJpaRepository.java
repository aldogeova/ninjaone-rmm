package com.ninjaone.inventory.dataaccess.inventory.repository;

import com.ninjaone.domain.valueobject.CustomerId;
import com.ninjaone.domain.valueobject.TypeDeviceId;
import com.ninjaone.inventory.dataaccess.inventory.entity.DeviceEntity;
import com.ninjaone.inventory.domain.entity.Device;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DeviceJpaRepository extends PagingAndSortingRepository<DeviceEntity, UUID> {
    Optional<DeviceEntity> findByTrackingId(UUID trackingId);
    Optional<DeviceEntity> findTopByCustomerIdAndTypeDeviceIdAndSystemName(UUID customerId, UUID typeDeviceId, String systemName);
    Optional<DeviceEntity> findTopByCustomerIdAndTypeDeviceIdAndSystemNameAndIdNot(UUID customerId, UUID typeDeviceId, String systemName, UUID deviceId);
}
