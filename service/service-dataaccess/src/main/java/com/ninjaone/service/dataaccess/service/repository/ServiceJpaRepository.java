package com.ninjaone.service.dataaccess.service.repository;

import com.ninjaone.service.dataaccess.service.entity.ServiceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface ServiceJpaRepository extends PagingAndSortingRepository<ServiceEntity, UUID> {
    Optional<ServiceEntity> findTopByName(String name);

   Optional<ServiceEntity> findTopByNameAndIdNot(String name, UUID value);

   Page<ServiceEntity> findAllByEnabledTrue(Pageable page);

}
