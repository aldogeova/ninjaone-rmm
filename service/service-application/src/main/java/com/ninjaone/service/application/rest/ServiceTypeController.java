package com.ninjaone.service.application.rest;

import com.ninjaone.domain.base.NinjaPage;
import com.ninjaone.service.domain.dto.command.service.ServiceResponse;
import com.ninjaone.service.domain.dto.command.service.type.ServiceTypeCommand;
import com.ninjaone.service.domain.dto.command.service.type.ServiceTypeResponse;
import com.ninjaone.service.domain.dto.service.ServiceTypeApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.UUID;

import static com.ninjaone.utils.NinjaUuiiUtils.identifierToUuid;

@Slf4j
@Api(value = "Service Type", tags = "ServiceType")
@RestController
@RequestMapping(value = "/serviceTypes", produces = "application/vnd.api.v1+json")
public class ServiceTypeController {


    private final ServiceTypeApplicationService serviceTypeApplicationService;

    public ServiceTypeController(ServiceTypeApplicationService serviceTypeApplicationService) {
        this.serviceTypeApplicationService = serviceTypeApplicationService;
    }

    @ApiOperation(value = "Endpoint to show a list of service types", response = NinjaPage.class, tags = "ServiceType")
    @GetMapping
    public ResponseEntity<NinjaPage<ServiceTypeResponse>> getAllServices(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        log.info("Obtaining all services");
        NinjaPage<ServiceTypeResponse> response = serviceTypeApplicationService.getAll(page, size);
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Endpoint to search a service type by id", response = ServiceTypeResponse.class, tags = "ServiceType")
    @GetMapping("/{id}")
    public ResponseEntity<ServiceTypeResponse> getById(@PathVariable("id") @NotNull String id) {
        log.info("Obtaining service type with id: {}", id);
        UUID uuid = identifierToUuid(id);
        ServiceTypeResponse response = serviceTypeApplicationService.findById(uuid);
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Endpoint to save a service type", response = ServiceResponse.class, tags = "ServiceType")
    @PostMapping
    public ResponseEntity<ServiceTypeResponse> save(@RequestBody ServiceTypeCommand serviceTypeCommand) {
        log.info("Creating service type with service: {} and type device {}", serviceTypeCommand.getServiceId(), serviceTypeCommand.getTypeDeviceId());
        ServiceTypeResponse saveServiceResponse = serviceTypeApplicationService.save(serviceTypeCommand);
        log.info("Operative system created with id: {}", saveServiceResponse.getId());
        return ResponseEntity.ok(saveServiceResponse);
    }

    @ApiOperation(value = "Endpoint to update a service type", response = ServiceResponse.class, tags = "ServiceType")
    @PutMapping("/{id}")
    public ResponseEntity<ServiceTypeResponse> update(@PathVariable("id") @NotNull String id, @RequestBody ServiceTypeCommand serviceTypeCommand) {
        UUID uuid = identifierToUuid(id);
        log.info("Updating service type with id: {}", id);
        ServiceTypeResponse updateServiceResponse = serviceTypeApplicationService.update(uuid, serviceTypeCommand);
        log.info("Operative system updated with id: {}", updateServiceResponse.getId());
        return ResponseEntity.ok(updateServiceResponse);
    }

    @ApiOperation(value = "Endpoint to delete a service type", response = ServiceResponse.class, tags = "ServiceType")
    @DeleteMapping("/{id}")
    public ResponseEntity<ServiceTypeResponse> delete(@PathVariable("id") @NotNull String id) {
        UUID uuid = identifierToUuid(id);
        log.info("Deleting service type with id: {}", id);
        ServiceTypeResponse deleteServiceResponse = serviceTypeApplicationService.delete(uuid);
        log.info("Operative system deleted with id: {}", deleteServiceResponse.getId());
        return ResponseEntity.ok(deleteServiceResponse);
    }
    
}
