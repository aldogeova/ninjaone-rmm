package com.ninjaone.inventory.domain.entity;

import com.ninjaone.domain.entity.BaseEntity;
import com.ninjaone.domain.valueobject.Money;
import com.ninjaone.domain.valueobject.ServiceId;
import com.ninjaone.domain.valueobject.ServiceTypeId;

public class ServiceType extends BaseEntity<ServiceTypeId> {
    private final ServiceId serviceId;
    private final TypeDevice typeDevice;
    private final Money price;

    public ServiceId getServiceId() {
        return serviceId;
    }

    public TypeDevice getTypeDevice() {
        return typeDevice;
    }

    public Money getPrice() {
        return price;
    }

    private ServiceType(Builder builder) {
        super.setId(builder.serviceTypeId);
        serviceId = builder.serviceId;
        typeDevice = builder.typeDevice;
        price = builder.price;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private ServiceTypeId serviceTypeId;
        private ServiceId serviceId;
        private TypeDevice typeDevice;
        private Money price;

        private Builder() {
        }

        public Builder id(ServiceTypeId val) {
            serviceTypeId = val;
            return this;
        }

        public Builder serviceId(ServiceId val) {
            serviceId = val;
            return this;
        }

        public Builder typeDevice(TypeDevice val) {
            typeDevice = val;
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
