package com.ninjaone.service.domain.ports.output.repository;

import com.ninjaone.domain.valueobject.TypeDeviceId;
import com.ninjaone.service.domain.entity.TypeDeviceView;

import java.util.Optional;

public interface TypeDeviceViewRepository {

    Optional<TypeDeviceView> findById(TypeDeviceId typeDeviceId);

    Optional<TypeDeviceView> findByIdAndEnabled(TypeDeviceId typeDeviceId, Boolean enabled);

}
