package com.ninjaone.inventory.domain.ports.output.repository;

import com.ninjaone.inventory.domain.entity.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {
    Optional<Customer> findById(UUID customerId);
}
