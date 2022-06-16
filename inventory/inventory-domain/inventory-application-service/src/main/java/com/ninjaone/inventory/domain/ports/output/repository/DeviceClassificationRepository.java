package com.ninjaone.inventory.domain.ports.output.repository;

import com.ninjaone.domain.base.NinjaPage;
import com.ninjaone.inventory.domain.entity.DeviceClassification;
import com.ninjaone.inventory.domain.valueobject.DeviceClassificationId;

import java.util.Optional;

public interface DeviceClassificationRepository {

    DeviceClassification save(DeviceClassification deviceClassification);

    Optional<DeviceClassification> findById(DeviceClassificationId deviceClassificationId);
    Optional<DeviceClassification> findTopByName(String name);

    Optional<DeviceClassification> findTopByNameAndIdNot(String stripAccents, DeviceClassificationId deviceClassificationId);

    NinjaPage<DeviceClassification> findAll(int page, int size);
}
