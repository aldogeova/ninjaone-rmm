package com.ninjaone.service.domain.entity;

import com.ninjaone.domain.entity.AggregateRoot;
import com.ninjaone.domain.valueobject.ServiceId;
import com.ninjaone.service.domain.exception.ServiceDomainException;

import java.util.UUID;

import static com.ninjaone.utils.NinjaStringUtils.isBlank;

public class Service extends AggregateRoot<ServiceId> {

    private final String name;
    private final String description;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void initialize() {
        setId(new ServiceId(UUID.randomUUID()));
    }

    public void validate() {
        if(isBlank(name)){
            throw new ServiceDomainException("Name is empty, please fill it");
        }
    }


    private Service(Builder builder) {
        setId(builder.serviceId);
        setEnabled(builder.enabled);
        setCreatedDate(builder.createdDate);
        setModifiedDate(builder.modifiedDate);
        name = builder.name;
        description = builder.description;
    }

    public static Builder builder() {
        return new Builder();
    }


    public static final class Builder {
        private ServiceId serviceId;
        private Boolean enabled;
        private long createdDate;
        private long modifiedDate;
        private String name;
        private String description;

        private Builder() {
        }

        public Builder serviceId(ServiceId val) {
            serviceId = val;
            return this;
        }

        public Builder enabled(Boolean val) {
            enabled = val;
            return this;
        }

        public Builder createdDate(long val) {
            createdDate = val;
            return this;
        }

        public Builder modifiedDate(long val) {
            modifiedDate = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder description(String val) {
            description = val;
            return this;
        }

        public Service build() {
            return new Service(this);
        }
    }
}
