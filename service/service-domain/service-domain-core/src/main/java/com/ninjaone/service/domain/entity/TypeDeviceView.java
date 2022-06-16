package com.ninjaone.service.domain.entity;

import com.ninjaone.domain.entity.AggregateRoot;
import com.ninjaone.domain.valueobject.TypeDeviceId;
import java.util.UUID;

public class TypeDeviceView extends AggregateRoot<TypeDeviceId> {

    /**
     * FIELDS
     */

    private final UUID operativeSystemId;

    private final UUID deviceClassificationId;


    private TypeDeviceView(Builder builder) {
        setId(builder.typeDeviceId);
        setEnabled(builder.enabled);
        setCreatedDate(builder.createdDate);
        setModifiedDate(builder.modifiedDate);
        operativeSystemId = builder.operativeSystemId;
        deviceClassificationId = builder.deviceClassificationId;
    }

    public static Builder builder() {
        return new Builder();
    }


    /**
     * GETTERS
     */


    public UUID getOperativeSystemId() {
        return operativeSystemId;
    }

    public UUID getDeviceClassificationId() {
        return deviceClassificationId;
    }

    /**
     * BUILDER SECTION
     */

    public static final class Builder {
        private TypeDeviceId typeDeviceId;
        private Boolean enabled;
        private long createdDate;
        private long modifiedDate;
        private UUID operativeSystemId;
        private UUID deviceClassificationId;


        private Builder() {
        }

        public Builder id(TypeDeviceId val) {
            typeDeviceId = val;
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

        public Builder operativeSystemId(UUID val) {
            operativeSystemId = val;
            return this;
        }

        public Builder deviceClassificationId(UUID val) {
            deviceClassificationId = val;
            return this;
        }

        public TypeDeviceView build() {
            return new TypeDeviceView(this);
        }
    }

}
