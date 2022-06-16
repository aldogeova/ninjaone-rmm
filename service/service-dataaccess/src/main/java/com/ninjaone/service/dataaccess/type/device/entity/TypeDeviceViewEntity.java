package com.ninjaone.service.dataaccess.type.device.entity;

import com.ninjaone.dataaccess.base.entity.BaseEntityJpa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "service_type_device_m_view", schema = "inventory")
@Entity
public class TypeDeviceViewEntity extends BaseEntityJpa {

    private UUID operativeSystemId;
    private UUID deviceClassificationId;

}
