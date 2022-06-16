package com.ninjaone.inventory.application.rest;

import com.ninjaone.domain.base.NinjaPage;
import com.ninjaone.inventory.domain.dto.command.device.classification.DeviceClassificationCommand;
import com.ninjaone.inventory.domain.dto.command.device.classification.DeviceClassificationResponse;
import com.ninjaone.inventory.domain.dto.command.operative.system.OperativeSystemResponse;
import com.ninjaone.inventory.domain.dto.service.DeviceClassificationApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.UUID;

import static com.ninjaone.utils.NinjaUuiiUtils.identifierToUuid;

@Slf4j
@Api(value = "Device Classification", tags = "DeviceClassification")
@RestController
@RequestMapping(value = "/deviceClassifications", produces = "application/vnd.api.v1+json")
public class DeviceClassificationController {

    private final DeviceClassificationApplicationService deviceClassificationApplicationService;

    public DeviceClassificationController(DeviceClassificationApplicationService deviceClassificationApplicationService) {
        this.deviceClassificationApplicationService = deviceClassificationApplicationService;
    }

    @ApiOperation(value = "Endpoint to show a list of device classifications ", response = NinjaPage.class, tags = "DeviceClassification")
    @GetMapping
    public ResponseEntity<NinjaPage<DeviceClassificationResponse>> getAllOperativeSystems(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        log.info("Obtaining all operative systems");
        NinjaPage<DeviceClassificationResponse> response = deviceClassificationApplicationService.getAll(page, size);
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Endpoint to search a device classification by id", response = DeviceClassificationResponse.class, tags = "DeviceClassification")
    @GetMapping("/{id}")
    public ResponseEntity<DeviceClassificationResponse> getById(@PathVariable("id") @NotNull String id) {
        log.info("Obtaining operative system with id: {}", id);
        UUID uuid = identifierToUuid(id);
        DeviceClassificationResponse response = deviceClassificationApplicationService.findById(uuid);
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Endpoint to save a device classification", response = DeviceClassificationResponse.class, tags = "DeviceClassification")
    @PostMapping
    public ResponseEntity<DeviceClassificationResponse> save(@RequestBody DeviceClassificationCommand operativeSystemCommand) {
        log.info("Creating operative system with name: {}", operativeSystemCommand.getName());
        DeviceClassificationResponse saveOperativeSystemResponse = deviceClassificationApplicationService.save(operativeSystemCommand);
        log.info("Operative system created with id: {}", saveOperativeSystemResponse.getId());
        return ResponseEntity.ok(saveOperativeSystemResponse);
    }

    @ApiOperation(value = "Endpoint to update a device classification", response = DeviceClassificationResponse.class, tags = "DeviceClassification")
    @PutMapping("/{id}")
    public ResponseEntity<DeviceClassificationResponse> update(@PathVariable("id") @NotNull String id, @RequestBody DeviceClassificationCommand operativeSystemCommand) {
        UUID uuid = identifierToUuid(id);
        log.info("Updating operative system with id: {}", id);
        DeviceClassificationResponse updateOperativeSystemResponse = deviceClassificationApplicationService.update(uuid, operativeSystemCommand);
        log.info("Operative system updated with id: {}", updateOperativeSystemResponse.getId());
        return ResponseEntity.ok(updateOperativeSystemResponse);
    }

    @ApiOperation(value = "Endpoint to delete a device classification", response = DeviceClassificationResponse.class, tags = "DeviceClassification")
    @DeleteMapping("/{id}")
    public ResponseEntity<DeviceClassificationResponse> delete(@PathVariable("id") @NotNull String id) {
        UUID uuid = identifierToUuid(id);
        log.info("Deleting operative system with id: {}", id);
        DeviceClassificationResponse deleteOperativeSystemResponse = deviceClassificationApplicationService.delete(uuid);
        log.info("Operative system deleted with id: {}", deleteOperativeSystemResponse.getId());
        return ResponseEntity.ok(deleteOperativeSystemResponse);
    }

}
