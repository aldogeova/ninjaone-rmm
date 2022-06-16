package com.ninjaone.inventory.dataaccess.inventory.mapper;

import com.ninjaone.domain.base.NinjaPage;
import com.ninjaone.inventory.dataaccess.inventory.entity.DeviceClassificationEntity;
import com.ninjaone.inventory.dataaccess.inventory.entity.OperativeSystemEntity;
import com.ninjaone.inventory.domain.entity.DeviceClassification;
import com.ninjaone.inventory.domain.entity.OperativeSystem;
import com.ninjaone.inventory.domain.valueobject.OperativeSystemId;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OperativeSystemDataAccessMapper {

    public OperativeSystemEntity operativeSystemToOperativeSystemEntity(OperativeSystem operativeSystem) {
        return OperativeSystemEntity.builder()
                .id(operativeSystem.getId().getValue())
                .name(operativeSystem.getName())
                .description(operativeSystem.getDescription())
                .enabled(operativeSystem.getEnabled() != null ? operativeSystem.getEnabled() : true)
                .build();
    }

    public OperativeSystem operativeSystemEntityToOperativeSystem(OperativeSystemEntity operativeSystem) {
        return OperativeSystem.builder()
                .id(new OperativeSystemId(operativeSystem.getId()))
                .name(operativeSystem.getName())
                .description(operativeSystem.getDescription())
                .enabled(operativeSystem.isEnabled())
                .createdDate(operativeSystem.getCreatedDate())
                .lastModifiedDate(operativeSystem.getModifiedDate())
                .build();
    }

    public NinjaPage<OperativeSystem> operativeSystemEntityPageToOperativeSystemNinjaPage(Page<OperativeSystemEntity> entities) {
        return NinjaPage.builder()
                .totalElements(entities.getTotalElements())
                .totalPages(entities.getTotalPages())
                .page(entities.getNumber())
                .size(entities.getSize())
                .content(entities.getContent().stream().map(this::operativeSystemEntityToOperativeSystem).toList())
                .build();
    }
}
