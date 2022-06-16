package com.ninjaone.inventory.dataaccess.inventory.adapter;


import com.ninjaone.domain.base.NinjaPage;
import com.ninjaone.domain.valueobject.DeviceId;
import com.ninjaone.inventory.dataaccess.inventory.entity.OperativeSystemEntity;
import com.ninjaone.inventory.dataaccess.inventory.mapper.DeviceDataAccessMapper;
import com.ninjaone.inventory.dataaccess.inventory.mapper.OperativeSystemDataAccessMapper;
import com.ninjaone.inventory.dataaccess.inventory.repository.DeviceJpaRepository;
import com.ninjaone.inventory.dataaccess.inventory.repository.OperativeSystemJpaRepository;
import com.ninjaone.inventory.domain.entity.Device;
import com.ninjaone.inventory.domain.entity.OperativeSystem;
import com.ninjaone.inventory.domain.ports.output.repository.DeviceRepository;
import com.ninjaone.inventory.domain.ports.output.repository.OperativeSystemRepository;
import com.ninjaone.inventory.domain.valueobject.OperativeSystemId;
import com.ninjaone.inventory.domain.valueobject.TrackingId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class OperativeSystemRepositoryImpl implements OperativeSystemRepository {

    private final OperativeSystemJpaRepository operativeSystemJpaRepository;
    private final OperativeSystemDataAccessMapper operativeSystemDataAccessMapper;

    public OperativeSystemRepositoryImpl(OperativeSystemJpaRepository operativeSystemJpaRepository, OperativeSystemDataAccessMapper operativeSystemDataAccessMapper) {
        this.operativeSystemJpaRepository = operativeSystemJpaRepository;
        this.operativeSystemDataAccessMapper = operativeSystemDataAccessMapper;
    }


    @Override
    public OperativeSystem save(OperativeSystem operativeSystem) {
        return operativeSystemDataAccessMapper.operativeSystemEntityToOperativeSystem(
                operativeSystemJpaRepository.save(operativeSystemDataAccessMapper.operativeSystemToOperativeSystemEntity(operativeSystem))
        );
    }


    @Override
    public Optional<OperativeSystem> findById(OperativeSystemId operativeSystemId) {
        return operativeSystemJpaRepository.findById(operativeSystemId.getValue())
                .map(operativeSystemDataAccessMapper::operativeSystemEntityToOperativeSystem);
    }

    @Override
    public Optional<OperativeSystem> findTopByName(String name) {
        return operativeSystemJpaRepository.findTopByName(name)
                .map(operativeSystemDataAccessMapper::operativeSystemEntityToOperativeSystem);
    }

    @Override
    public Optional<OperativeSystem> findTopByNameAndIdNot(String stripAccents, OperativeSystemId operativeSystemId) {
        return operativeSystemJpaRepository.findTopByNameAndIdNot(stripAccents, operativeSystemId.getValue())
                .map(operativeSystemDataAccessMapper::operativeSystemEntityToOperativeSystem);
    }

    @Override
    public NinjaPage<OperativeSystem> findAll(int page, int size) {
        Page<OperativeSystemEntity> operativeSystemPage = operativeSystemJpaRepository.findAllByEnabledTrue(PageRequest.of(page, size));
        return operativeSystemDataAccessMapper.operativeSystemEntityPageToOperativeSystemNinjaPage(operativeSystemPage);
    }
}
