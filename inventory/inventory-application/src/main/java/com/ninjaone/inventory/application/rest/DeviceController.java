package com.ninjaone.inventory.application.rest;

import com.ninjaone.inventory.domain.dto.command.device.CreateDeviceCommand;
import com.ninjaone.inventory.domain.dto.command.device.DeviceResponse;
import com.ninjaone.inventory.domain.dto.command.device.DeviceSearchResponse;
import com.ninjaone.inventory.domain.dto.service.DeviceApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Slf4j
@Api(value = "Device", tags = "Device")
@RestController
@RequestMapping(value = "/devices", produces = "application/vnd.api.v1+json")
public class DeviceController {

    private final DeviceApplicationService deviceApplicationService;

    public DeviceController(DeviceApplicationService deviceApplicationService) {
        this.deviceApplicationService = deviceApplicationService;
    }

    @ApiOperation(value = "Endpoint to create a new Device and associate a list of services.", response = DeviceResponse.class, tags = "Device")
    @PostMapping
    public ResponseEntity<DeviceResponse> createDevice(@RequestBody CreateDeviceCommand createDeviceCommand) {
        log.info("Creating device for a customer: {}", createDeviceCommand.getCustomerID());
        DeviceResponse createDeviceResponse = deviceApplicationService.createDevice(createDeviceCommand);
        log.info("Device created with tracking id: {}", createDeviceResponse.getDeviceTrackingId());
        return ResponseEntity.ok(createDeviceResponse);
    }

    @ApiOperation(value = "Endpoint to modify a Device and associate a list of services.", response = DeviceResponse.class, tags = "Device")
    @PutMapping("/{id}")
    public ResponseEntity<DeviceResponse> update(@PathVariable("id") @NotNull String id, @RequestBody CreateDeviceCommand createDeviceCommand) {
        log.info("Updating device for a customer: {}", createDeviceCommand.getCustomerID());
        UUID deviceId = UUID.fromString(id);
        DeviceResponse createDeviceResponse = deviceApplicationService.update(deviceId, createDeviceCommand);
        log.info("Device updated with tracking id: {}", createDeviceResponse.getDeviceTrackingId());
        return ResponseEntity.ok(createDeviceResponse);
    }

    @ApiOperation(value = "Endpoint to search a Device by id", response = DeviceSearchResponse.class, tags = "Device")
    @GetMapping("/{id}")
    public ResponseEntity<DeviceSearchResponse> find(@PathVariable("id") @NotNull String id) {
        log.info("Searching device with id: {}", id);
        UUID deviceId = UUID.fromString(id);
        DeviceSearchResponse deviceResponse = deviceApplicationService.find(deviceId);
        return ResponseEntity.ok(deviceResponse);
    }


    @ApiOperation(value = "Endpoint to search a Device", response = DeviceSearchResponse.class, tags = "Device")
    @DeleteMapping("/{id}")
    public ResponseEntity<DeviceResponse> delete(@PathVariable("id") @NotNull String id) {
        log.info("Searching device with id: {}", id);
        UUID deviceId = UUID.fromString(id);
        DeviceResponse deviceResponse = deviceApplicationService.delete(deviceId);
        return ResponseEntity.ok(deviceResponse);
    }

}
