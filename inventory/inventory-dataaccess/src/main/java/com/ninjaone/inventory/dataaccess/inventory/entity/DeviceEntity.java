package com.ninjaone.inventory.dataaccess.inventory.entity;

import com.ninjaone.dataaccess.base.entity.BaseEntityJpa;
import com.ninjaone.domain.valueobject.DeviceStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "device")
@Entity
public class DeviceEntity extends BaseEntityJpa {

    private UUID customerId;
    private UUID trackingId;
    private String systemName;
    @Enumerated(EnumType.STRING)
    private DeviceStatus deviceStatus;
    private String failureMessages;

    @ManyToOne
    @JoinColumn(name = "type_device_id")
    private TypeDeviceEntity typeDevice;

    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL)
    private List<DeviceServiceEntity> deviceServices;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceEntity that = (DeviceEntity) o;
        return id.equals(that.id) && systemName.equals(that.systemName) && typeDevice.equals(that.typeDevice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, systemName, typeDevice);
    }
}
