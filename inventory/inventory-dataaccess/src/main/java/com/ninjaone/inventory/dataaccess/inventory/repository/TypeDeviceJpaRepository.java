package com.ninjaone.inventory.dataaccess.inventory.repository;

import com.ninjaone.inventory.dataaccess.inventory.entity.TypeDeviceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TypeDeviceJpaRepository extends PagingAndSortingRepository<TypeDeviceEntity, UUID> {

    Page<TypeDeviceEntity> findAllByEnabledTrue(Pageable page);

    Optional<TypeDeviceEntity> findByOperativeSystemIdAndDeviceClassificationId(UUID operativeSystemId, UUID deviceClassificationId);

    Optional<TypeDeviceEntity> findTopByOperativeSystemIdAndDeviceClassificationIdAndIdNot(UUID operativeSystemId, UUID deviceClassificationId, UUID id);
}
