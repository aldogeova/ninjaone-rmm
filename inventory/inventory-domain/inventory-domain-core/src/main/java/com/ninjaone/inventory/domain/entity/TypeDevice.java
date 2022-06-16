package com.ninjaone.inventory.domain.entity;

import com.ninjaone.domain.entity.BaseEntity;
import com.ninjaone.domain.valueobject.TypeDeviceId;
import com.ninjaone.inventory.domain.exception.InventoryDomainException;
import com.ninjaone.inventory.domain.valueobject.OperativeSystemId;

import java.util.UUID;

import static com.ninjaone.utils.NinjaStringUtils.isBlank;

public class TypeDevice extends BaseEntity<TypeDeviceId> {
    private String description;
    private OperativeSystem operativeSystem;
    private DeviceClassification deviceClassification;

    public TypeDevice(TypeDeviceId typeDeviceId, String description, OperativeSystem operativeSystem, DeviceClassification deviceClassification) {
        setId(typeDeviceId);
        this.description = description;
        this.operativeSystem = operativeSystem;
        this.deviceClassification = deviceClassification;
    }

    public TypeDevice(TypeDeviceId typeDeviceId) {
        setId(typeDeviceId);
    }

    private TypeDevice(Builder builder) {
        super.setId(builder.typeDeviceId);
        description = builder.description;
        operativeSystem = builder.operativeSystem;
        deviceClassification = builder.deviceClassification;
        setEnabled(builder.enabled);
        setCreatedDate(builder.createdDate);
        setModifiedDate(builder.lastModifiedDate);
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getDescription() {
        return description;
    }

    public OperativeSystem getOperativeSystem() {
        return operativeSystem;
    }

    public DeviceClassification getDeviceClassification() {
        return deviceClassification;
    }


    public void initialize(){
        super.setId(new TypeDeviceId(UUID.randomUUID()));
    }

    public void validate() {
        if(isBlank(description)){
            throw new InventoryDomainException("Description is empty, please fill it");
        }

        if(operativeSystem == null){
            throw new InventoryDomainException("OperativeSystem is empty, please fill it");
        }

        if (deviceClassification == null) {
            throw new InventoryDomainException("DeviceClassification is empty, please fill it");
        }
    }


    public static final class Builder {
        private TypeDeviceId typeDeviceId;
        private String description;
        private OperativeSystem operativeSystem;
        private DeviceClassification deviceClassification;

        private Boolean enabled;

        private long createdDate;

        private long lastModifiedDate;

        private Builder() {
        }

        public Builder id(TypeDeviceId val) {
            typeDeviceId = val;
            return this;
        }

        public Builder description(String val) {
            description = val;
            return this;
        }

        public Builder operativeSystem(OperativeSystem val) {
            operativeSystem = val;
            return this;
        }

        public Builder deviceClassification(DeviceClassification val) {
            deviceClassification = val;
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

        public Builder lastModifiedDate(long val) {
            lastModifiedDate = val;
            return this;
        }

        public TypeDevice build() {
            return new TypeDevice(this);
        }
    }
}
