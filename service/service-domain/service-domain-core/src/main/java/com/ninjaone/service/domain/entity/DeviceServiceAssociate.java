package com.ninjaone.service.domain.entity;

import com.ninjaone.domain.entity.BaseEntity;
import com.ninjaone.domain.valueobject.Money;
import com.ninjaone.service.domain.valueobject.DeviceServiceAssociatetId;

import java.util.UUID;

public class  DeviceServiceAssociate extends BaseEntity<DeviceServiceAssociatetId> {

    private final UUID serviceTypeId;
    private final Money price;
    private final Boolean validated;
    private final String message;


    private DeviceServiceAssociate(Builder builder) {
        setId(builder.deviceServiceAssociatetId);
        setEnabled(builder.enabled);
        setCreatedDate(builder.createdDate);
        setModifiedDate(builder.modifiedDate);
        serviceTypeId = builder.serviceTypeId;
        price = builder.price;
        validated = builder.validated;
        message = builder.message;
    }


    public UUID getServiceTypeId() {
        return serviceTypeId;
    }

    public Money getPrice() {
        return price;
    }

    public Boolean getValidated() {
        return validated;
    }

    public String getMessage() {
        return message;
    }

    public static Builder builder() {
        return new Builder();
    }


    public static final class Builder {
        private DeviceServiceAssociatetId deviceServiceAssociatetId;
        private Boolean enabled;
        private long createdDate;
        private long modifiedDate;
        private UUID serviceTypeId;
        private Money price;
        private Boolean validated;
        private String message;

        private Builder() {
        }

        public Builder deviceServiceAssociatetId(DeviceServiceAssociatetId val) {
            deviceServiceAssociatetId = val;
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

        public Builder serviceTypeId(UUID val) {
            serviceTypeId = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder validated(Boolean val) {
            validated = val;
            return this;
        }

        public Builder message(String val) {
            message = val;
            return this;
        }

        public DeviceServiceAssociate build() {
            return new DeviceServiceAssociate(this);
        }

    }
}
