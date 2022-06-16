package com.ninjaone.inventory.dataaccess.inventory.adapter;


import com.ninjaone.domain.base.NinjaPage;
import com.ninjaone.domain.valueobject.TypeDeviceId;
import com.ninjaone.inventory.dataaccess.inventory.entity.TypeDeviceEntity;
import com.ninjaone.inventory.dataaccess.inventory.mapper.TypeDeviceDataAccessMapper;
import com.ninjaone.inventory.dataaccess.inventory.repository.TypeDeviceJpaRepository;
import com.ninjaone.inventory.domain.entity.TypeDevice;
import com.ninjaone.inventory.domain.ports.output.repository.TypeDeviceRepository;
import com.ninjaone.inventory.domain.valueobject.DeviceClassificationId;
import com.ninjaone.inventory.domain.valueobject.OperativeSystemId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TypeDeviceRepositoryImpl implements TypeDeviceRepository {

    private final TypeDeviceJpaRepository typeDeviceJpaRepository;
    private final TypeDeviceDataAccessMapper typeDeviceDataAccessMapper;

    public TypeDeviceRepositoryImpl(TypeDeviceJpaRepository TypeDeviceJpaRepository, TypeDeviceDataAccessMapper TypeDeviceDataAccessMapper) {
        this.typeDeviceJpaRepository = TypeDeviceJpaRepository;
        this.typeDeviceDataAccessMapper = TypeDeviceDataAccessMapper;
    }


    @Override
    public TypeDevice save(TypeDevice typeDevice) {
        return typeDeviceDataAccessMapper.typeDeviceEntityToTypeDevice(
                typeDeviceJpaRepository.save(typeDeviceDataAccessMapper.typeDeviceToTypeDeviceEntity(typeDevice))
        );
    }

    @Override
    public Optional<TypeDevice> findById(TypeDeviceId typeDeviceId) {
        return typeDeviceJpaRepository.findById(typeDeviceId.getValue())
                .map(typeDeviceDataAccessMapper::typeDeviceEntityToTypeDevice);
    }

    @Override
    public Optional<TypeDevice> findByOperativeSystemIdAndDeviceClassificationId(OperativeSystemId operativeSystemId, DeviceClassificationId deviceClassificationId) {
        return typeDeviceJpaRepository.findByOperativeSystemIdAndDeviceClassificationId(operativeSystemId.getValue(), deviceClassificationId.getValue())
                .map(typeDeviceDataAccessMapper::typeDeviceEntityToTypeDevice);
    }

    @Override
    public Optional<TypeDevice> findTopByOperativeSystemIdAndDeviceClassificationIdAndIdNot(OperativeSystemId operativeSystemId, DeviceClassificationId deviceClassificationId, TypeDeviceId typeDeviceId) {
        return typeDeviceJpaRepository.findTopByOperativeSystemIdAndDeviceClassificationIdAndIdNot(operativeSystemId.getValue(), deviceClassificationId.getValue(), typeDeviceId.getValue())
                .map(typeDeviceDataAccessMapper::typeDeviceEntityToTypeDevice);
    }

    @Override
    public NinjaPage<TypeDevice> findAll(int page, int size) {
        Page<TypeDeviceEntity> typeDevicePage = typeDeviceJpaRepository.findAllByEnabledTrue(PageRequest.of(page, size));
        return typeDeviceDataAccessMapper.typeDeviceEntityPageToTypeDeviceNinjaPage(typeDevicePage);
    }
}
