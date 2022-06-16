package com.ninjaone.inventory.domain.entity;

import com.ninjaone.domain.entity.BaseEntity;
import com.ninjaone.inventory.domain.exception.InventoryDomainException;
import com.ninjaone.inventory.domain.valueobject.OperativeSystemId;

import java.util.UUID;

import static com.ninjaone.utils.NinjaStringUtils.isBlank;

public class OperativeSystem extends BaseEntity<OperativeSystemId> {
    private String name;
    private String description;


    public OperativeSystem(OperativeSystemId operativeSystemId, String name, String description) {
        setId(operativeSystemId);
        this.name = name;
        this.description = description;
    }

    private OperativeSystem(Builder builder) {
        super.setId(builder.operativeSystemId);
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
        super.setId(new OperativeSystemId(UUID.randomUUID()));
    }

    public void validate() {
        if(isBlank(name)){
            throw new InventoryDomainException("Name is empty, please fill it");
        }
    }

    public static final class Builder {
        private OperativeSystemId operativeSystemId;
        private String name;
        private String description;

        private Boolean enabled;

        private long createdDate;

        private long lastModifiedDate;

        private Builder() {
        }

        public Builder id(OperativeSystemId val) {
            operativeSystemId = val;
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

        public OperativeSystem build() {
            return new OperativeSystem(this);
        }
    }
}
