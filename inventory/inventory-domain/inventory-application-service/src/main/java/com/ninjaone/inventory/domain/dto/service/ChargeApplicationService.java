package com.ninjaone.inventory.domain.dto.service;

import com.ninjaone.domain.base.NinjaPage;
import com.ninjaone.inventory.domain.dto.command.charge.ChargeResponse;
import com.ninjaone.inventory.domain.dto.command.operative.system.OperativeSystemCommand;
import com.ninjaone.inventory.domain.dto.command.operative.system.OperativeSystemResponse;

import java.util.UUID;

public interface ChargeApplicationService {

    ChargeResponse byCustomer(UUID clientId);

    ChargeResponse byDevice(UUID deviceID);

}
