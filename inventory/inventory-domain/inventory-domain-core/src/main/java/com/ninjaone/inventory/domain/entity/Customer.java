package com.ninjaone.inventory.domain.entity;

import com.ninjaone.domain.entity.AggregateRoot;
import com.ninjaone.domain.valueobject.CustomerId;

public class Customer extends AggregateRoot<CustomerId> {

    private String identification;
    private String name;
    private String mail;

    public Customer(CustomerId customerId) {
        super.setId(customerId);
    }

    public Customer(String identification, String name, String mail) {
        this.identification = identification;
        this.name = name;
        this.mail = mail;
    }

    public String getIdentification() {
        return identification;
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }
}
