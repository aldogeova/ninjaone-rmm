package com.ninjaone.inventory.dataaccess.inventory.entity;

import com.ninjaone.dataaccess.base.entity.BaseEntityJpa;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;


@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "operative_system")
@Entity
public class OperativeSystemEntity extends BaseEntityJpa {

    private String name;
    private String description;

}
