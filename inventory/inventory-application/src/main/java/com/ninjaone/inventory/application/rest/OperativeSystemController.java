package com.ninjaone.inventory.application.rest;

import com.ninjaone.domain.base.NinjaPage;
import com.ninjaone.inventory.domain.dto.command.device.DeviceSearchResponse;
import com.ninjaone.inventory.domain.dto.command.operative.system.OperativeSystemCommand;
import com.ninjaone.inventory.domain.dto.command.operative.system.OperativeSystemResponse;
import com.ninjaone.inventory.domain.dto.service.OperativeSystemApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static com.ninjaone.utils.NinjaUuiiUtils.identifierToUuid;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Slf4j
@Api(value = "Operative System", tags = "OperativeSystem")
@RestController
@RequestMapping(value = "/operativeSystems", produces = "application/vnd.api.v1+json")
public class OperativeSystemController {

    private final OperativeSystemApplicationService operativeSystemApplicationService;

    public OperativeSystemController(OperativeSystemApplicationService operativeSystemApplicationService) {
        this.operativeSystemApplicationService = operativeSystemApplicationService;
    }

    @ApiOperation(value = "Endpoint to show a list of operative systems", response = NinjaPage.class, tags = "OperativeSystem")
    @GetMapping
    public ResponseEntity<NinjaPage<OperativeSystemResponse>> getAllOperativeSystems(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        log.info("Obtaining all operative systems");
        NinjaPage<OperativeSystemResponse> response = operativeSystemApplicationService.getAll(page, size);
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Endpoint to search a operative system by id", response = OperativeSystemResponse.class, tags = "OperativeSystem")
    @GetMapping("/{id}")
    public ResponseEntity<OperativeSystemResponse> getById(@PathVariable("id") @NotNull String id) {
        log.info("Obtaining operative system with id: {}", id);
        UUID uuid = identifierToUuid(id);
        OperativeSystemResponse response = operativeSystemApplicationService.findById(uuid);
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Endpoint to save a operative system", response = OperativeSystemResponse.class, tags = "OperativeSystem")
    @PostMapping
    public ResponseEntity<OperativeSystemResponse> save(@RequestBody OperativeSystemCommand operativeSystemCommand) {
        log.info("Creating operative system with name: {}", operativeSystemCommand.getName());
        OperativeSystemResponse saveOperativeSystemResponse = operativeSystemApplicationService.save(operativeSystemCommand);
        log.info("Operative system created with id: {}", saveOperativeSystemResponse.getId());
        return ResponseEntity.ok(saveOperativeSystemResponse);
    }

    @ApiOperation(value = "Endpoint to update a operative system", response = OperativeSystemResponse.class, tags = "OperativeSystem")
    @PutMapping("/{id}")
    public ResponseEntity<OperativeSystemResponse> update(@PathVariable("id") @NotNull String id, @RequestBody OperativeSystemCommand operativeSystemCommand) {
        UUID uuid = identifierToUuid(id);
        log.info("Updating operative system with id: {}", id);
        OperativeSystemResponse updateOperativeSystemResponse = operativeSystemApplicationService.update(uuid, operativeSystemCommand);
        log.info("Operative system updated with id: {}", updateOperativeSystemResponse.getId());
        return ResponseEntity.ok(updateOperativeSystemResponse);
    }

    @ApiOperation(value = "Endpoint to delete a operative system", response = OperativeSystemResponse.class, tags = "OperativeSystem")
    @DeleteMapping("/{id}")
    public ResponseEntity<OperativeSystemResponse> delete(@PathVariable("id") @NotNull String id) {
        UUID uuid = identifierToUuid(id);
        log.info("Deleting operative system with id: {}", id);
        OperativeSystemResponse deleteOperativeSystemResponse = operativeSystemApplicationService.delete(uuid);
        log.info("Operative system deleted with id: {}", deleteOperativeSystemResponse.getId());
        return ResponseEntity.ok(deleteOperativeSystemResponse);
    }

}
