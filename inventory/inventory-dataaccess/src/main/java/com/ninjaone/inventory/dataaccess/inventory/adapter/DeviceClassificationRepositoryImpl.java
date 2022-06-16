package com.ninjaone.inventory.dataaccess.inventory.adapter;


import com.ninjaone.domain.base.NinjaPage;
import com.ninjaone.inventory.dataaccess.inventory.entity.DeviceClassificationEntity;
import com.ninjaone.inventory.dataaccess.inventory.mapper.DeviceClassificationDataAccessMapper;
import com.ninjaone.inventory.dataaccess.inventory.repository.DeviceClassificationJpaRepository;
import com.ninjaone.inventory.domain.entity.DeviceClassification;
import com.ninjaone.inventory.domain.ports.output.repository.DeviceClassificationRepository;
import com.ninjaone.inventory.domain.valueobject.DeviceClassificationId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DeviceClassificationRepositoryImpl implements DeviceClassificationRepository {

    private final DeviceClassificationJpaRepository deviceClassificationJpaRepository;
    private final DeviceClassificationDataAccessMapper deviceClassificationDataAccessMapper;

    public DeviceClassificationRepositoryImpl(DeviceClassificationJpaRepository deviceClassificationJpaRepository,
                                              DeviceClassificationDataAccessMapper deviceClassificationDataAccessMapper) {
        this.deviceClassificationJpaRepository = deviceClassificationJpaRepository;
        this.deviceClassificationDataAccessMapper = deviceClassificationDataAccessMapper;
    }


    @Override
    public DeviceClassification save(DeviceClassification deviceClassification) {
        return deviceClassificationDataAccessMapper.deviceClassificationEntityToDeviceClassification(
                deviceClassificationJpaRepository.save(deviceClassificationDataAccessMapper.deviceClassificationToDeviceClassificationEntity(deviceClassification))
        );
    }


    @Override
    public Optional<DeviceClassification> findById(DeviceClassificationId operativeSystemId) {
        return deviceClassificationJpaRepository.findById(operativeSystemId.getValue())
                .map(deviceClassificationDataAccessMapper::deviceClassificationEntityToDeviceClassification);
    }

    @Override
    public Optional<DeviceClassification> findTopByName(String name) {
        return deviceClassificationJpaRepository.findTopByName(name)
                .map(deviceClassificationDataAccessMapper::deviceClassificationEntityToDeviceClassification);
    }

    @Override
    public Optional<DeviceClassification> findTopByNameAndIdNot(String stripAccents, DeviceClassificationId operativeSystemId) {
        return deviceClassificationJpaRepository.findTopByNameAndIdNot(stripAccents, operativeSystemId.getValue())
                .map(deviceClassificationDataAccessMapper::deviceClassificationEntityToDeviceClassification);
    }

    @Override
    public NinjaPage<DeviceClassification> findAll(int page, int size) {
        Page<DeviceClassificationEntity> operativeSystemPage = deviceClassificationJpaRepository.findAllByEnabledTrue(PageRequest.of(page, size));
        return deviceClassificationDataAccessMapper.deviceClassificationEntityPageToDeviceClassificationNinjaPage(operativeSystemPage);
    }
}
