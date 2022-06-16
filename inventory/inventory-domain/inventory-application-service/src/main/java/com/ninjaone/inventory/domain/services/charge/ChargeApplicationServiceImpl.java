package com.ninjaone.inventory.domain.services.charge;

import com.ninjaone.domain.base.NinjaPage;
import com.ninjaone.inventory.domain.dto.command.charge.ChargeResponse;
import com.ninjaone.inventory.domain.dto.command.operative.system.OperativeSystemCommand;
import com.ninjaone.inventory.domain.dto.command.operative.system.OperativeSystemResponse;
import com.ninjaone.inventory.domain.dto.service.ChargeApplicationService;
import com.ninjaone.inventory.domain.dto.service.OperativeSystemApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Slf4j
@Validated
@Service
public class ChargeApplicationServiceImpl implements ChargeApplicationService {

    private final ChargeApplicationCommandHandler chargeApplicationCommandHandler;


    public ChargeApplicationServiceImpl(ChargeApplicationCommandHandler chargeApplicationCommandHandler) {
        this.chargeApplicationCommandHandler = chargeApplicationCommandHandler;
    }


    @Override
    public ChargeResponse byCustomer(UUID clientId) {
        return chargeApplicationCommandHandler.byCustomer(clientId);
    }

    @Override
    public ChargeResponse byDevice(UUID deviceID) {
        return chargeApplicationCommandHandler.byDevice(deviceID);
    }
}
