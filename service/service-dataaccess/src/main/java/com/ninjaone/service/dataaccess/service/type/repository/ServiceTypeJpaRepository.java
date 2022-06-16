package com.ninjaone.service.dataaccess.service.type.repository;


import com.ninjaone.service.dataaccess.service.type.entity.ServiceTypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ServiceTypeJpaRepository extends PagingAndSortingRepository<ServiceTypeEntity, UUID> {

    Optional<ServiceTypeEntity> findByIdAndTypeDeviceId(UUID serviceTypeId, UUID typeDeviceId);

    List<ServiceTypeEntity> findByTypeDeviceId(UUID typeDeviceId);

    List<ServiceTypeEntity> findByServiceId(UUID serviceId);

    Page<ServiceTypeEntity> findAllByEnabledTrue(Pageable page);

    Optional<ServiceTypeEntity> findTopByTypeDeviceIdAndServiceId(UUID typeDeviceId, UUID serviceId);

    Optional<ServiceTypeEntity> findTopByTypeDeviceIdAndServiceIdAndIdNot(UUID typeDeviceId, UUID serviceId, UUID serviceTypeId);

}
