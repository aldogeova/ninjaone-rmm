package com.ninjaone.service.dataaccess.service.entity;

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

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "service")
@Entity
public class ServiceEntity extends BaseEntityJpa {

    private String name;
    private String description;

}
