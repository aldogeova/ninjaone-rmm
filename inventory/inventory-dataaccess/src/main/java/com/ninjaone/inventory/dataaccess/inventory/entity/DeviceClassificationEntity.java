package com.ninjaone.inventory.dataaccess.inventory.entity;

import com.ninjaone.dataaccess.base.entity.BaseEntityJpa;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "device_classification")
@Entity
public class DeviceClassificationEntity extends BaseEntityJpa {

    private String name;
    private String description;

}
