package com.ninjaone.inventory.dataaccess.inventory.adapter;


import com.ninjaone.domain.base.NinjaPage;
import com.ninjaone.domain.valueobject.CustomerId;
import com.ninjaone.domain.valueobject.DeviceId;
import com.ninjaone.domain.valueobject.ServiceTypeId;
import com.ninjaone.domain.valueobject.TypeDeviceId;
import com.ninjaone.inventory.dataaccess.inventory.entity.TypeDeviceEntity;
import com.ninjaone.inventory.dataaccess.inventory.mapper.DeviceServiceDataAccessMapper;
import com.ninjaone.inventory.dataaccess.inventory.mapper.TypeDeviceDataAccessMapper;
import com.ninjaone.inventory.dataaccess.inventory.repository.DeviceServiceJpaRepository;
import com.ninjaone.inventory.dataaccess.inventory.repository.TypeDeviceJpaRepository;
import com.ninjaone.inventory.domain.entity.DeviceService;
import com.ninjaone.inventory.domain.entity.TypeDevice;
import com.ninjaone.inventory.domain.ports.output.repository.DeviceServiceRepository;
import com.ninjaone.inventory.domain.ports.output.repository.TypeDeviceRepository;
import com.ninjaone.inventory.domain.valueobject.DeviceClassificationId;
import com.ninjaone.inventory.domain.valueobject.DeviceServiceId;
import com.ninjaone.inventory.domain.valueobject.OperativeSystemId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DeviceServiceRepositoryImpl implements DeviceServiceRepository {

    private final DeviceServiceJpaRepository deviceServiceJpaRepository;
    private final DeviceServiceDataAccessMapper deviceServiceDataAccessMapper;


    public DeviceServiceRepositoryImpl(DeviceServiceJpaRepository deviceServiceJpaRepository, DeviceServiceDataAccessMapper deviceServiceDataAccessMapper) {
        this.deviceServiceJpaRepository = deviceServiceJpaRepository;
        this.deviceServiceDataAccessMapper = deviceServiceDataAccessMapper;
    }


    @Override
    public DeviceService save(DeviceService deviceService) {

        return deviceServiceDataAccessMapper.deviceServiceEntityToDeviceService(
                deviceServiceJpaRepository.save(
                deviceServiceDataAccessMapper.deviceServiceToDeviceServiceEntity(deviceService))
        );
    }

    @Override
    public Optional<DeviceService> findById(DeviceServiceId deviceServiceId) {
        return deviceServiceJpaRepository.findById(deviceServiceId.getValue())
                .map(deviceServiceDataAccessMapper::deviceServiceEntityToDeviceService);
    }

    @Override
    public Optional<DeviceService> findTopByDeviceIdAndServiceTypeId(DeviceId deviceId, ServiceTypeId serviceTypeId) {
        return deviceServiceJpaRepository.findTopByDeviceIdAndServiceTypeId(deviceId.getValue(), serviceTypeId.getValue())
                .map(deviceServiceDataAccessMapper::deviceServiceEntityToDeviceService);
    }

    @Override
    public List<DeviceService> findByDeviceId(DeviceId deviceId) {
        return deviceServiceJpaRepository.findByDeviceId(deviceId.getValue())
                .stream()
                .map(deviceServiceDataAccessMapper::deviceServiceEntityToDeviceService).collect(Collectors.toList());
    }

    @Override
    public List<DeviceService> findByDeviceIdAndEnabled(DeviceId deviceId, boolean enabled) {
        return  deviceServiceJpaRepository.findByDeviceIdAndEnabled(deviceId.getValue(), enabled)
                .stream()
                .map(deviceServiceDataAccessMapper::deviceServiceEntityToDeviceService).collect(Collectors.toList());
    }

    @Override
    public List<DeviceService> findByDeviceCustomerIdAndEnabled(CustomerId customerId, boolean enabled) {
        return deviceServiceJpaRepository.findByDeviceCustomerIdAndEnabled(customerId.getValue(), enabled)
                .stream()
                .map(deviceServiceDataAccessMapper::deviceServiceEntityToDeviceService).collect(Collectors.toList());
    }

    @Override
    public List<DeviceService> findByDeviceCustomerIdAndDeviceEnabledTrue(CustomerId customerId) {
        return deviceServiceJpaRepository.findByDeviceCustomerIdAndDeviceEnabledTrue(customerId.getValue())
                .stream()
                .map(deviceServiceDataAccessMapper::deviceServiceEntityToDeviceService).collect(Collectors.toList());
    }

    @Override
    public List<DeviceService> findByDeviceIdAndDeviceEnabledTrue(DeviceId deviceId) {
        return  deviceServiceJpaRepository.findByDeviceIdAndDeviceEnabledTrue(deviceId.getValue())
                .stream()
                .map(deviceServiceDataAccessMapper::deviceServiceEntityToDeviceService).collect(Collectors.toList());
    }

    @Override
    public List<DeviceService> findByDeviceTypeDeviceIdAndEnabled(TypeDeviceId typeDeviceId, boolean enabled) {
        return deviceServiceJpaRepository.findByDeviceTypeDeviceIdAndEnabled(typeDeviceId.getValue(), enabled)
                .stream()
                .map(deviceServiceDataAccessMapper::deviceServiceEntityToDeviceService).collect(Collectors.toList());
    }

    @Override
    public List<DeviceService> findByDeviceTypeDeviceOperativeSystemIdAndEnabled(OperativeSystemId operativeSystemId, boolean enabled) {
        return deviceServiceJpaRepository.findByDeviceTypeDeviceOperativeSystemIdAndEnabled(operativeSystemId.getValue(), enabled)
                .stream()
                .map(deviceServiceDataAccessMapper::deviceServiceEntityToDeviceService).collect(Collectors.toList());
    }

    @Override
    public List<DeviceService> findByDeviceTypeDeviceDeviceClassificationIdAndEnabled(DeviceClassificationId deviceClassificationId, boolean enabled) {
        return deviceServiceJpaRepository.findByDeviceTypeDeviceDeviceClassificationIdAndEnabled(deviceClassificationId.getValue(), enabled)
                .stream()
                .map(deviceServiceDataAccessMapper::deviceServiceEntityToDeviceService).collect(Collectors.toList());
    }

}
