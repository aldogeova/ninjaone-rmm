package com.ninjaone.inventory.dataaccess.service.entity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "inventory_service_type_m_view", schema = "service")
@Entity
public class ServiceTypeEntity {

    @Id
    private UUID serviceTypeId;
    private UUID serviceId;
    private UUID typeDeviceId;

    private BigDecimal price;
    private String serviceName;
    private String typeDeviceDescription;
    private UUID idOperativeSystem;
    private String nameOperativeSystem;
    private UUID idDeviceClassification;
    private String nameDeviceClassification;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceTypeEntity that = (ServiceTypeEntity) o;
        return serviceTypeId.equals(that.serviceTypeId) && serviceId.equals(that.serviceId) && typeDeviceId.equals(that.typeDeviceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceTypeId, serviceId, typeDeviceId);
    }
}
