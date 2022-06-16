package com.ninjaone.inventory.domain.entity;

import com.ninjaone.domain.entity.BaseEntity;
import com.ninjaone.inventory.domain.exception.InventoryDomainException;
import com.ninjaone.inventory.domain.valueobject.DeviceClassificationId;
import com.ninjaone.inventory.domain.valueobject.OperativeSystemId;

import java.util.UUID;

import static com.ninjaone.utils.NinjaStringUtils.isBlank;

public class DeviceClassification extends BaseEntity<DeviceClassificationId> {
    private String name;
    private String description;

    public DeviceClassification(DeviceClassificationId deviceClassificationId, String name, String description) {
        setId(deviceClassificationId);
        this.name = name;
        this.description = description;
    }

    private DeviceClassification(Builder builder) {
        super.setId(builder.deviceClassificationId);
        name = builder.name;
        description = builder.description;
        setEnabled(builder.enabled);
        setCreatedDate(builder.createdDate);
        setModifiedDate(builder.lastModifiedDate);
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void initialize(){
        super.setId(new DeviceClassificationId(UUID.randomUUID()));
    }

    public void validate() {
        if(isBlank(name)){
            throw new InventoryDomainException("Name is empty, please fill it");
        }
    }



    public static final class Builder {
        private DeviceClassificationId deviceClassificationId;
        private String name;
        private String description;

        private Boolean enabled;

        private long createdDate;

        private long lastModifiedDate;



        private Builder() {
        }

        public Builder id(DeviceClassificationId val) {
            deviceClassificationId = val;
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



        public DeviceClassification build() {
            return new DeviceClassification(this);
        }
    }
}
