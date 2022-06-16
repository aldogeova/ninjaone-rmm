package com.ninjaone.inventory.dataaccess.inventory.entity;

import com.ninjaone.dataaccess.base.entity.BaseEntityJpa;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "device_service")
@Entity
public class DeviceServiceEntity extends BaseEntityJpa {

    private UUID serviceTypeId;
    private BigDecimal price;
    private String message;
    private String status;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private DeviceEntity device;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceServiceEntity that = (DeviceServiceEntity) o;
        return id.equals(that.id) && serviceTypeId.equals(that.serviceTypeId) && device.equals(that.device);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, serviceTypeId, device);
    }
}
