package com.ninjaone.service.dataaccess.service.type.entity;

import com.ninjaone.dataaccess.base.entity.BaseEntityJpa;
import com.ninjaone.service.dataaccess.service.entity.ServiceEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "service_type")
@Entity
public class ServiceTypeEntity extends BaseEntityJpa {


    private UUID typeDeviceId;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private ServiceEntity service;

    private BigDecimal price;


}
