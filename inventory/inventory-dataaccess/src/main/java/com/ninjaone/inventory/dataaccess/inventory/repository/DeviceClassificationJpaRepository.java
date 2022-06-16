package com.ninjaone.inventory.dataaccess.inventory.repository;

import com.ninjaone.inventory.dataaccess.inventory.entity.DeviceClassificationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DeviceClassificationJpaRepository extends PagingAndSortingRepository<DeviceClassificationEntity, UUID> {
     Optional<DeviceClassificationEntity> findTopByName(String name);

    Optional<DeviceClassificationEntity> findTopByNameAndIdNot(String stripAccents, UUID id);

    Page<DeviceClassificationEntity> findAllByEnabledTrue(Pageable page);

}
