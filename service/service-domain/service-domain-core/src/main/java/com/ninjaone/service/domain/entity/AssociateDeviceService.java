package com.ninjaone.service.domain.entity;

import com.ninjaone.domain.entity.AggregateRoot;
import com.ninjaone.domain.valueobject.AssociateDeviceId;
import com.ninjaone.domain.valueobject.DeviceId;
import com.ninjaone.domain.valueobject.DeviceStatus;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

public class AssociateDeviceService extends AggregateRoot<AssociateDeviceId> {

    private final UUID sagaId;

    private final DeviceId deviceId;

    private  DeviceStatus deviceStatus;

    private final List<DeviceServiceAssociate> deviceServicesAssociate;


    public UUID getSagaId() {
        return sagaId;
    }

    public DeviceId getDeviceId() {
        return deviceId;
    }

    public DeviceStatus getDeviceStatus() {
        return deviceStatus;
    }

    public List<DeviceServiceAssociate> getDeviceServicesAssociate() {
        return deviceServicesAssociate;
    }

    private AssociateDeviceService(Builder builder) {
        setId(builder.associateDeviceId);
        setEnabled(builder.enabled);
        setCreatedDate(builder.createdDate);
        setModifiedDate(builder.modifiedDate);
        sagaId = builder.sagaId;
        deviceId = builder.deviceId;
        deviceStatus = builder.deviceStatus;
        deviceServicesAssociate = builder.deviceServicesAssociate;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private AssociateDeviceId associateDeviceId;
        private Boolean enabled;
        private long createdDate;
        private long modifiedDate;
        private UUID sagaId;
        private DeviceId deviceId;
        private DeviceStatus deviceStatus;
        private List<DeviceServiceAssociate> deviceServicesAssociate;

        private Builder() {
        }

        public Builder id(AssociateDeviceId val) {
            associateDeviceId = val;
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

        public Builder sagaId(UUID val) {
            sagaId = val;
            return this;
        }

        public Builder deviceId(DeviceId val) {
            deviceId = val;
            return this;
        }

        public Builder deviceStatus(DeviceStatus val) {
            deviceStatus = val;
            return this;
        }

        public Builder deviceServicesAssociate(List<DeviceServiceAssociate> val) {
            deviceServicesAssociate = val;
            return this;
        }

        public AssociateDeviceService build() {
            return new AssociateDeviceService(this);
        }
    }

    /**
     * METHODS
     */

    public void validate(List<String> failureMessages) {
        if(deviceId == null) {
            failureMessages.add("DeviceId is required!");
        }

        if(deviceStatus == null) {
            failureMessages.add("DeviceStatus is required!");
        }

        if(deviceServicesAssociate == null || deviceServicesAssociate.isEmpty()) {
            failureMessages.add("DeviceServices to Associate is required!");
        }
    }


    public void initialize() {
        setId(new AssociateDeviceId(UUID.randomUUID()));
        setCreatedDate(ZonedDateTime.now(ZoneId.of("UTC")).toInstant().toEpochMilli());
    }

    public void updateStatus(DeviceStatus deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

}
