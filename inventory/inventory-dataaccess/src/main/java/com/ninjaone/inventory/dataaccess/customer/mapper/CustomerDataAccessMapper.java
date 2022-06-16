package com.ninjaone.inventory.dataaccess.customer.mapper;

import com.ninjaone.domain.valueobject.CustomerId;
import com.ninjaone.inventory.dataaccess.customer.entity.CustomerEntity;
import com.ninjaone.inventory.domain.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerDataAccessMapper {
    public Customer customerEntityToCustomer(CustomerEntity customerEntity) {
        return new Customer(new CustomerId(customerEntity.getId()));
    }
}
