package com.ninjaone.inventory.domain.ports.output.repository;

import com.ninjaone.domain.base.NinjaPage;
import com.ninjaone.domain.valueobject.TypeDeviceId;
import com.ninjaone.inventory.domain.entity.TypeDevice;
import com.ninjaone.inventory.domain.valueobject.DeviceClassificationId;
import com.ninjaone.inventory.domain.valueobject.OperativeSystemId;

import java.util.Optional;

public interface TypeDeviceRepository {

    TypeDevice save(TypeDevice typeDevice);

    Optional<TypeDevice> findById(TypeDeviceId typeDeviceId);

    Optional<TypeDevice> findByOperativeSystemIdAndDeviceClassificationId(OperativeSystemId operativeSystemId, DeviceClassificationId deviceClassificationId);

    Optional<TypeDevice> findTopByOperativeSystemIdAndDeviceClassificationIdAndIdNot(OperativeSystemId operativeSystemId, DeviceClassificationId deviceClassificationId, TypeDeviceId typeDeviceId);

    NinjaPage<TypeDevice> findAll(int page, int size);
}
