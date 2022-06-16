package com.ninjaone.service.application.rest;

import com.ninjaone.domain.base.NinjaPage;
import com.ninjaone.service.domain.dto.command.service.ServiceCommand;
import com.ninjaone.service.domain.dto.command.service.ServiceResponse;
import com.ninjaone.service.domain.dto.service.ServiceApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.UUID;

import static com.ninjaone.utils.NinjaUuiiUtils.identifierToUuid;

@Slf4j
@Api(value = "Services", tags = "Services")
@RestController
@RequestMapping(value = "/services", produces = "application/vnd.api.v1+json")
public class ServiceController {


    private final ServiceApplicationService serviceApplicationService;

    public ServiceController(ServiceApplicationService serviceApplicationService) {
        this.serviceApplicationService = serviceApplicationService;
    }

    @ApiOperation(value = "Endpoint to show a list of services", response = NinjaPage.class, tags = "Services")
    @GetMapping
    public ResponseEntity<NinjaPage<ServiceResponse>> getAllServices(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        log.info("Obtaining all services");
        NinjaPage<ServiceResponse> response = serviceApplicationService.getAll(page, size);
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Endpoint to search a service by id", response = ServiceResponse.class, tags = "Services")
    @GetMapping("/{id}")
    public ResponseEntity<ServiceResponse> getById(@PathVariable("id") @NotNull String id) {
        log.info("Obtaining service with id: {}", id);
        UUID uuid = identifierToUuid(id);
        ServiceResponse response = serviceApplicationService.findById(uuid);
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Endpoint to save a service", response = ServiceResponse.class, tags = "Services")
    @PostMapping
    public ResponseEntity<ServiceResponse> save(@RequestBody ServiceCommand serviceCommand) {
        log.info("Creating service with name: {}", serviceCommand.getName());
        ServiceResponse saveServiceResponse = serviceApplicationService.save(serviceCommand);
        log.info("Operative system created with id: {}", saveServiceResponse.getId());
        return ResponseEntity.ok(saveServiceResponse);
    }

    @ApiOperation(value = "Endpoint to update a service", response = ServiceResponse.class, tags = "Services")
    @PutMapping("/{id}")
    public ResponseEntity<ServiceResponse> update(@PathVariable("id") @NotNull String id, @RequestBody ServiceCommand serviceCommand) {
        UUID uuid = identifierToUuid(id);
        log.info("Updating service with id: {}", id);
        ServiceResponse updateServiceResponse = serviceApplicationService.update(uuid, serviceCommand);
        log.info("Operative system updated with id: {}", updateServiceResponse.getId());
        return ResponseEntity.ok(updateServiceResponse);
    }

    @ApiOperation(value = "Endpoint to delete a service", response = ServiceResponse.class, tags = "Services")
    @DeleteMapping("/{id}")
    public ResponseEntity<ServiceResponse> delete(@PathVariable("id") @NotNull String id) {
        UUID uuid = identifierToUuid(id);
        log.info("Deleting service with id: {}", id);
        ServiceResponse deleteServiceResponse = serviceApplicationService.delete(uuid);
        log.info("Operative system deleted with id: {}", deleteServiceResponse.getId());
        return ResponseEntity.ok(deleteServiceResponse);
    }




}
