package com.ninjaone.service.dataaccess.type.device.adapter;

import com.ninjaone.domain.valueobject.TypeDeviceId;
import com.ninjaone.service.dataaccess.type.device.mapper.TypeDeviceViewDataAccessMapper;
import com.ninjaone.service.dataaccess.type.device.repository.TypeDeviceViewJpaRepository;
import com.ninjaone.service.domain.entity.TypeDeviceView;
import com.ninjaone.service.domain.ports.output.repository.TypeDeviceViewRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TypeDeviceViewRepositoryImpl implements TypeDeviceViewRepository {

    private final TypeDeviceViewJpaRepository typeDeviceViewJpaRepository;
    private final TypeDeviceViewDataAccessMapper typeDeviceViewDataAccessMapper;

    public TypeDeviceViewRepositoryImpl(TypeDeviceViewJpaRepository typeDeviceViewJpaRepository, TypeDeviceViewDataAccessMapper typeDeviceViewDataAccessMapper) {
        this.typeDeviceViewJpaRepository = typeDeviceViewJpaRepository;
        this.typeDeviceViewDataAccessMapper = typeDeviceViewDataAccessMapper;
    }

    @Override
    public Optional<TypeDeviceView> findById(TypeDeviceId typeDeviceId) {
        return typeDeviceViewJpaRepository.findById(typeDeviceId.getValue())
                .map(typeDeviceViewDataAccessMapper::typeDeviceViewEntityToTypeDeviceView);
    }

    @Override
    public Optional<TypeDeviceView> findByIdAndEnabled(TypeDeviceId typeDeviceId, Boolean enabled) {
    return typeDeviceViewJpaRepository.findByIdAndEnabled(typeDeviceId.getValue(), enabled)
            .map(typeDeviceViewDataAccessMapper::typeDeviceViewEntityToTypeDeviceView);
    }
}
