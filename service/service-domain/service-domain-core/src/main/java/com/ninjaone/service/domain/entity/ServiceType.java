package com.ninjaone.service.domain.entity;

import com.ninjaone.domain.entity.AggregateRoot;
import com.ninjaone.domain.valueobject.Money;
import com.ninjaone.domain.valueobject.ServiceId;
import com.ninjaone.domain.valueobject.ServiceTypeId;
import com.ninjaone.domain.valueobject.TypeDeviceId;
import com.ninjaone.service.domain.exception.ServiceDomainException;

import java.util.UUID;

import static com.ninjaone.utils.NinjaStringUtils.isBlank;

public class ServiceType extends AggregateRoot<ServiceTypeId> {

    /**
     * FIELDS
     */

    private final Service service;

    private final TypeDeviceId typeDeviceId;

    private final Money price;

    /**
     * CONSTRUCTOR
     */

    private ServiceType(Builder builder) {
        setId(builder.serviceTypeId);
        setEnabled(builder.enabled);
        setCreatedDate(builder.createdDate);
        setModifiedDate(builder.modifiedDate);
        service = builder.service;
        typeDeviceId = builder.typeDeviceId;
        price = builder.price;
    }


    /**
     * GETTERS
     */

    public Service getService() {
        return service;
    }

    public TypeDeviceId getTypeDeviceId() {
        return typeDeviceId;
    }

    public Money getPrice() {
        return price;
    }



    /**
     * METHODS
     */

    public void initialize() {
        setId(new ServiceTypeId(UUID.randomUUID()));
    }

    public void validate() {
        if(service == null || service.getId() == null){
            throw new ServiceDomainException("Service is empty, please fill it");
        }

        if(typeDeviceId == null){
            throw new ServiceDomainException("Service is empty, please fill it");
        }

        if(price.isLowerThanZero()){
            throw new ServiceDomainException("Price is lower than zero, please put a price equal or greater than zero");
        }
    }



    /**
     * BUILDER SECTION
     */

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private ServiceTypeId serviceTypeId;
        private Boolean enabled;
        private long createdDate;
        private long modifiedDate;
        private Service service;
        private TypeDeviceId typeDeviceId;
        private Money price;

        private Builder() {
        }

        public Builder serviceTypeId(ServiceTypeId val) {
            serviceTypeId = val;
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

        public Builder service(Service val) {
            service = val;
            return this;
        }

        public Builder typeDeviceId(TypeDeviceId val) {
            typeDeviceId = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public ServiceType build() {
            return new ServiceType(this);
        }
    }
}
