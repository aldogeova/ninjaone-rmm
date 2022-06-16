package com.ninjaone.inventory.domain.services.device;

import com.ninjaone.domain.valueobject.DeviceId;
import com.ninjaone.inventory.domain.dto.command.device.DeviceResponse;
import com.ninjaone.inventory.domain.dto.command.device.DeviceSearchResponse;
import com.ninjaone.inventory.domain.dto.track.TrackDeviceQuery;
import com.ninjaone.inventory.domain.dto.track.TrackDeviceResponse;
import com.ninjaone.inventory.domain.entity.Device;
import com.ninjaone.inventory.domain.entity.DeviceService;
import com.ninjaone.inventory.domain.exception.InventoryNotFoundException;
import com.ninjaone.inventory.domain.mapper.DeviceDataMapper;
import com.ninjaone.inventory.domain.ports.output.repository.DeviceRepository;
import com.ninjaone.inventory.domain.ports.output.repository.DeviceServiceRepository;
import com.ninjaone.inventory.domain.valueobject.TrackingId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class DeviceTrackCommandHandler {

    private final DeviceDataMapper deviceDataMapper;

    private final DeviceRepository deviceRepository;

    private final DeviceServiceRepository deviceServiceRepository;

    public DeviceTrackCommandHandler(DeviceDataMapper deviceDataMapper, DeviceRepository deviceRepository, DeviceServiceRepository deviceServiceRepository) {
        this.deviceDataMapper = deviceDataMapper;
        this.deviceRepository = deviceRepository;
        this.deviceServiceRepository = deviceServiceRepository;
    }

    @Transactional(readOnly = true)
    public TrackDeviceResponse trackDevice(TrackDeviceQuery trackDeviceQuery) {
        Optional<Device> deviceResult =
                deviceRepository.findByTrackingId(new TrackingId(trackDeviceQuery.getDeviceTrackingId()));
        if (deviceResult.isEmpty()) {
            log.warn("Could not find device with tracking id: {}", trackDeviceQuery.getDeviceTrackingId());
            throw new InventoryNotFoundException("Could not find device with tracking id: " +
                    trackDeviceQuery.getDeviceTrackingId());
        }
        return deviceDataMapper.deviceToTrackDeviceResponse(deviceResult.get());
    }

    public DeviceSearchResponse find(UUID deviceId) {
        Optional<Device> deviceResult = deviceRepository.findById(new DeviceId(deviceId));
        if (deviceResult.isEmpty()) {
            log.warn("Could not find device with id: {}", deviceId);
            throw new InventoryNotFoundException("Could not find device with id: " + deviceId);
        }

        deviceResult.get().setDeviceServices(deviceServiceRepository.findByDeviceId(new DeviceId(deviceId)));

        return deviceDataMapper.deviceToDeviceSearchResponse(deviceResult.get());

    }

    public DeviceResponse delete(UUID deviceId) {
        Optional<Device> deviceResult = deviceRepository.findById(new DeviceId(deviceId));
        if (deviceResult.isEmpty()) {
            log.warn("Could not find device with id: {}", deviceId);
            throw new InventoryNotFoundException("Could not find device with id: " + deviceId);
        }

        deviceResult.get().setEnabled(false);
        deviceRepository.save(deviceResult.get());

        List<DeviceService> deviceServices = deviceServiceRepository.findByDeviceId(new DeviceId(deviceId));

        for (DeviceService deviceService : deviceServices) {
            deviceService.setEnabled(false);
            deviceService.setDeviceId(deviceResult.get().getId());
            deviceServiceRepository.save(deviceService);
        }

        List<DeviceService> deviceServicesDeleted = deviceServiceRepository.findByDeviceId(new DeviceId(deviceId));

        deviceResult.get().setDeviceServices(deviceServicesDeleted);

        return deviceDataMapper.deviceToCreateDeviceResponse(deviceResult.get(), "Device deleted successfully");
    }
}
