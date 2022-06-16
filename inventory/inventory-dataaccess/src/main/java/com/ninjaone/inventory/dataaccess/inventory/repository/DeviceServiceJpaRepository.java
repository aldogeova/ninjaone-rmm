package com.ninjaone.inventory.dataaccess.inventory.repository;

import com.ninjaone.domain.valueobject.CustomerId;
import com.ninjaone.domain.valueobject.DeviceId;
import com.ninjaone.inventory.dataaccess.inventory.entity.DeviceEntity;
import com.ninjaone.inventory.dataaccess.inventory.entity.DeviceServiceEntity;
import com.ninjaone.inventory.domain.entity.DeviceService;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DeviceServiceJpaRepository extends PagingAndSortingRepository<DeviceServiceEntity, UUID> {
    Optional<DeviceServiceEntity> findById(UUID id);

    Optional<DeviceServiceEntity> findTopByDeviceIdAndServiceTypeId(UUID deviceId, UUID serviceTypeId);

    List<DeviceServiceEntity> findByDeviceIdAndEnabled(UUID deviceId, boolean enabled);

    List<DeviceServiceEntity> findByDeviceTypeDeviceIdAndEnabled(UUID typeDeviceId, boolean enabled);

    List<DeviceServiceEntity> findByDeviceTypeDeviceOperativeSystemIdAndEnabled(UUID operativeSystemId, boolean enabled);

    List<DeviceServiceEntity> findByDeviceTypeDeviceDeviceClassificationIdAndEnabled(UUID deviceClassificationId, boolean enabled);

    List<DeviceServiceEntity> findByDeviceCustomerIdAndEnabled(UUID customerId, boolean enabled);

    List<DeviceServiceEntity> findByDeviceCustomerIdAndDeviceEnabledTrue(UUID customerId);

    List<DeviceServiceEntity> findByDeviceIdAndDeviceEnabledTrue(UUID deviceId);

    List<DeviceServiceEntity> findByDeviceId(UUID value);
}
