package com.ninjaone.service.dataaccess.type.device.repository;

import com.ninjaone.domain.valueobject.TypeDeviceId;
import com.ninjaone.service.dataaccess.service.entity.ServiceEntity;
import com.ninjaone.service.dataaccess.type.device.entity.TypeDeviceViewEntity;
import com.ninjaone.service.domain.entity.TypeDeviceView;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TypeDeviceViewJpaRepository extends CrudRepository<TypeDeviceViewEntity, UUID> {

    Optional<TypeDeviceViewEntity> findByIdAndEnabled(UUID typeDeviceId, Boolean enabled);

}
