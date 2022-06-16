package com.ninjaone.inventory.domain.mapper;

import com.ninjaone.domain.base.NinjaPage;
import com.ninjaone.inventory.domain.dto.command.operative.system.OperativeSystemCommand;
import com.ninjaone.inventory.domain.dto.command.operative.system.OperativeSystemResponse;
import com.ninjaone.inventory.domain.entity.OperativeSystem;
import com.ninjaone.inventory.domain.valueobject.OperativeSystemId;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

import static com.ninjaone.utils.NinjaStringUtils.defaultFormat;

@Component
public class OperativeSystemDataMapper {

    public OperativeSystem operativeSystemCommandToOperativeSystem(OperativeSystemCommand operativeSystemCommand) {
        return OperativeSystem.builder()
                .name(defaultFormat(operativeSystemCommand.getName()))
                .description(defaultFormat(operativeSystemCommand.getDescription()))
                .enabled(true)
                .build();
    }

    public OperativeSystem operativeSystemCommandToOperativeSystemWithId(UUID id, OperativeSystemCommand operativeSystemCommand) {
        return OperativeSystem.builder()
                .id(new OperativeSystemId(id))
                .name(defaultFormat(operativeSystemCommand.getName()))
                .description(defaultFormat(operativeSystemCommand.getDescription()))
                .enabled(true)
                .build();
    }

    public OperativeSystemResponse operativeSystemToOperativeSystemResponse(OperativeSystem operativeSystem, String operativeSystem_created_successfully) {
        return OperativeSystemResponse.builder()
                .id(operativeSystem.getId().getValue())
                .name(operativeSystem.getName())
                .description(operativeSystem.getDescription())
                .enabled(operativeSystem.getEnabled())
                .createdDate(operativeSystem.getCreatedDate())
                .modifiedDate(operativeSystem.getModifiedDate())
                .message(operativeSystem_created_successfully)
                .build();
    }

    public NinjaPage<OperativeSystemResponse> operativeSystemNinjaPageToOperativeSystemResponseNinjaPage(NinjaPage<OperativeSystem> operativeSystemNinjaPage) {
        return NinjaPage.builder()
                .page(operativeSystemNinjaPage.getPage())
                .size(operativeSystemNinjaPage.getSize())
                .totalElements(operativeSystemNinjaPage.getTotalElements())
                .totalPages(operativeSystemNinjaPage.getTotalPages())
                .content(operativeSystemNinjaPage.getContent().stream().map(os -> {
                    return operativeSystemToOperativeSystemResponse(os,"");
                }).collect(Collectors.toList()))
                .build()
                ;
    }
}
