package com.ninjaone.inventory.domain.services.charge;

import com.ninjaone.inventory.domain.dto.command.charge.ChargeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class ChargeApplicationCommandHandler {

    private final ChargeApplicationHelper chargeApplicationHelper;

    public ChargeApplicationCommandHandler(ChargeApplicationHelper chargeApplicationHelper) {
        this.chargeApplicationHelper = chargeApplicationHelper;
    }

    public ChargeResponse byCustomer(UUID clientId) {
        return chargeApplicationHelper.byCustomer(clientId);
    }

    public ChargeResponse byDevice(UUID deviceId) {
        return chargeApplicationHelper.byDevice(deviceId);
    }

}
