package com.ninjaone.inventory.application.rest;

import com.ninjaone.domain.base.NinjaPage;
import com.ninjaone.inventory.domain.dto.command.charge.ChargeResponse;
import com.ninjaone.inventory.domain.dto.command.operative.system.OperativeSystemCommand;
import com.ninjaone.inventory.domain.dto.command.operative.system.OperativeSystemResponse;
import com.ninjaone.inventory.domain.dto.service.ChargeApplicationService;
import com.ninjaone.inventory.domain.dto.service.OperativeSystemApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.UUID;

import static com.ninjaone.utils.NinjaUuiiUtils.identifierToUuid;

@Slf4j
@Api(value = "Charges", tags = "Charges")
@RestController
@RequestMapping(value = "/charges", produces = "application/vnd.api.v1+json")
public class ChargeController {

    private final ChargeApplicationService chargeApplicationService;

    public ChargeController(ChargeApplicationService chargeApplicationService) {
        this.chargeApplicationService = chargeApplicationService;
    }

    @ApiOperation(value = "Endpoint to show the total amount of charges of services by client", response = ChargeResponse.class, tags = "Charges")
    @GetMapping("/{clientId}/client")
    public ResponseEntity<ChargeResponse> byClient(@PathVariable("clientId") @NotNull String clientId) {
        log.info("Obtaining charges by client id: {}", clientId);
        UUID clientUuid = identifierToUuid(clientId);
        ChargeResponse response = chargeApplicationService.byCustomer(clientUuid);
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Endpoint to show the total amount of of charges of services by device", response = ChargeResponse.class, tags = "Charges")
    @GetMapping("/{deviceId}/device")
    public ResponseEntity<ChargeResponse> byDevice(@PathVariable("deviceId") @NotNull String deviceId) {
        log.info("Obtaining charges by device id: {}", deviceId);
        UUID deviceUuid = identifierToUuid(deviceId);
        ChargeResponse response = chargeApplicationService.byDevice(deviceUuid);
        return ResponseEntity.ok(response);
    }

}
