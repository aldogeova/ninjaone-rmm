package com.ninjaone.inventory.dataaccess.inventory.repository;

import com.ninjaone.inventory.dataaccess.inventory.entity.DeviceEntity;
import com.ninjaone.inventory.dataaccess.inventory.entity.OperativeSystemEntity;
import com.ninjaone.inventory.domain.entity.OperativeSystem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OperativeSystemJpaRepository extends PagingAndSortingRepository<OperativeSystemEntity, UUID> {
     Optional<OperativeSystemEntity> findTopByName(String name);

    Optional<OperativeSystemEntity> findTopByNameAndIdNot(String stripAccents, UUID id);

    Page<OperativeSystemEntity> findAllByEnabledTrue(Pageable page);

}
