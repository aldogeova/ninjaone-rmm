package com.ninjaone.inventory.domain.ports.output.repository;

import com.ninjaone.domain.base.NinjaPage;
import com.ninjaone.domain.valueobject.DeviceId;
import com.ninjaone.inventory.domain.entity.Device;
import com.ninjaone.inventory.domain.entity.OperativeSystem;
import com.ninjaone.inventory.domain.valueobject.OperativeSystemId;
import com.ninjaone.inventory.domain.valueobject.TrackingId;

import java.util.Optional;

public interface OperativeSystemRepository {

    OperativeSystem save(OperativeSystem operativeSystem);

    Optional<OperativeSystem> findById(OperativeSystemId operativeSystemId);
    Optional<OperativeSystem> findTopByName(String name);

    Optional<OperativeSystem> findTopByNameAndIdNot(String stripAccents, OperativeSystemId operativeSystemId);

    NinjaPage<OperativeSystem> findAll(int page, int size);
}
