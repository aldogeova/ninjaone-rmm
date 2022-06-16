package com.ninjaone.inventory.dataaccess.inventory.adapter;


import com.ninjaone.domain.valueobject.CustomerId;
import com.ninjaone.domain.valueobject.DeviceId;
import com.ninjaone.domain.valueobject.TypeDeviceId;
import com.ninjaone.inventory.dataaccess.inventory.mapper.DeviceDataAccessMapper;
import com.ninjaone.inventory.dataaccess.inventory.repository.DeviceJpaRepository;
import com.ninjaone.inventory.domain.entity.Device;
import com.ninjaone.inventory.domain.ports.output.repository.DeviceRepository;
import com.ninjaone.inventory.domain.valueobject.TrackingId;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DeviceRepositoryImpl implements DeviceRepository {

    private final DeviceJpaRepository deviceJpaRepository;
    private final DeviceDataAccessMapper deviceDataAccessMapper;

    public DeviceRepositoryImpl(DeviceJpaRepository deviceJpaRepository, DeviceDataAccessMapper deviceDataAccessMapper) {
        this.deviceJpaRepository = deviceJpaRepository;
        this.deviceDataAccessMapper = deviceDataAccessMapper;
    }

    @Override
    public Device save(Device device) {
        return deviceDataAccessMapper.deviceEntityToDevice(
                deviceJpaRepository.save(deviceDataAccessMapper.deviceToDeviceEntity(device))
        );
    }

    @Override
    public Optional<Device> findById(DeviceId deviceId) {
        return deviceJpaRepository.findById(deviceId.getValue())
                .map(deviceDataAccessMapper::deviceEntityToDevice);
    }

    @Override
    public Optional<Device> findByTrackingId(TrackingId trackingId) {
        return deviceJpaRepository.findByTrackingId(trackingId.getValue())
                .map(deviceDataAccessMapper::deviceEntityToDevice);
    }

    @Override
    public Optional<Device> findTopByCustomerIdAndTypeDeviceIdAndSystemName(CustomerId customerId, TypeDeviceId typeDeviceId, String systemName) {
        return deviceJpaRepository.findTopByCustomerIdAndTypeDeviceIdAndSystemName(customerId.getValue(), typeDeviceId.getValue(), systemName)
                .map(deviceDataAccessMapper::deviceEntityToDevice);
    }

    @Override
    public Optional<Device> findTopByCustomerIdAndTypeDeviceIdAndSystemNameAndIdNot(CustomerId customerId, TypeDeviceId typeDeviceId, String systemName, DeviceId deviceId) {
        return deviceJpaRepository.findTopByCustomerIdAndTypeDeviceIdAndSystemNameAndIdNot(customerId.getValue(), typeDeviceId.getValue(), systemName, deviceId.getValue())
                .map(deviceDataAccessMapper::deviceEntityToDevice);
    }


}
