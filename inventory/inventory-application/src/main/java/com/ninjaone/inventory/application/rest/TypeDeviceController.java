package com.ninjaone.inventory.application.rest;

import com.ninjaone.domain.base.NinjaPage;
import com.ninjaone.inventory.domain.dto.command.operative.system.OperativeSystemResponse;
import com.ninjaone.inventory.domain.dto.command.type.device.TypeDeviceCommand;
import com.ninjaone.inventory.domain.dto.command.type.device.TypeDeviceResponse;
import com.ninjaone.inventory.domain.dto.service.TypeDeviceApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.UUID;

import static com.ninjaone.utils.NinjaUuiiUtils.identifierToUuid;

@Slf4j
@Api(value = "Type Device", tags = "TypeDevice")
@RestController
@RequestMapping(value = "/typeDevices", produces = "application/vnd.api.v1+json")
public class TypeDeviceController {

    private final TypeDeviceApplicationService typeDeviceApplicationService;

    public TypeDeviceController(TypeDeviceApplicationService typeDeviceApplicationService) {
        this.typeDeviceApplicationService = typeDeviceApplicationService;
    }

    @ApiOperation(value = "Endpoint to show a list of type devices", response = NinjaPage.class, tags = "TypeDevice")
    @GetMapping
    public ResponseEntity<NinjaPage<TypeDeviceResponse>> getAll(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        log.info("Obtaining all type devices");
        NinjaPage<TypeDeviceResponse> response = typeDeviceApplicationService.getAll(page, size);
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Endpoint to search a type device by id", response = OperativeSystemResponse.class, tags = "TypeDevice")
    @GetMapping("/{id}")
    public ResponseEntity<TypeDeviceResponse> getById(@PathVariable("id") @NotNull String id) {
        log.info("Obtaining type device with id: {}", id);
        UUID uuid = identifierToUuid(id);
        TypeDeviceResponse response = typeDeviceApplicationService.findById(uuid);
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Endpoint to save a type device", response = TypeDeviceResponse.class, tags = "TypeDevice")
    @PostMapping
    public ResponseEntity<TypeDeviceResponse> save(@RequestBody TypeDeviceCommand typeDeviceCommand) {
        log.info("Creating type device with description: {}", typeDeviceCommand.getDescription());
        TypeDeviceResponse saveOperativeSystemResponse = typeDeviceApplicationService.save(typeDeviceCommand);
        log.info("Type device created with id: {}", saveOperativeSystemResponse.getId());
        return ResponseEntity.ok(saveOperativeSystemResponse);
    }

    @ApiOperation(value = "Endpoint to update a type device", response = OperativeSystemResponse.class, tags = "TypeDevice")
    @PutMapping("/{id}")
    public ResponseEntity<TypeDeviceResponse> update(@PathVariable("id") @NotNull String id, @RequestBody TypeDeviceCommand typeDeviceCommand) {
        UUID uuid = identifierToUuid(id);
        log.info("Updating type device with id: {}", id);
        TypeDeviceResponse updateOperativeSystemResponse = typeDeviceApplicationService.update(uuid, typeDeviceCommand);
        log.info("Type device updated with id: {}", updateOperativeSystemResponse.getId());
        return ResponseEntity.ok(updateOperativeSystemResponse);
    }

    @ApiOperation(value = "Endpoint to delete a type device", response = OperativeSystemResponse.class, tags = "TypeDevice")
    @DeleteMapping("/{id}")
    public ResponseEntity<TypeDeviceResponse> delete(@PathVariable("id") @NotNull String id) {
        UUID uuid = identifierToUuid(id);
        log.info("Deleting type device with id: {}", id);
        TypeDeviceResponse deleteOperativeSystemResponse = typeDeviceApplicationService.delete(uuid);
        log.info("Type device deleted with id: {}", deleteOperativeSystemResponse.getId());
        return ResponseEntity.ok(deleteOperativeSystemResponse);
    }

}
