package com.ninjaone.inventory.dataaccess.inventory.entity;

import com.ninjaone.dataaccess.base.entity.BaseEntityJpa;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "type_device")
@Entity
public class TypeDeviceEntity extends BaseEntityJpa {

    private String description;

    @ManyToOne
    @JoinColumn(name = "operative_system_id")
    private OperativeSystemEntity operativeSystem;

    @ManyToOne
    @JoinColumn(name = "device_classification_id")
    private DeviceClassificationEntity deviceClassification;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeDeviceEntity that = (TypeDeviceEntity) o;
        return id.equals(that.id) && operativeSystem.equals(that.operativeSystem) && deviceClassification.equals(that.deviceClassification);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, operativeSystem, deviceClassification);
    }
}
