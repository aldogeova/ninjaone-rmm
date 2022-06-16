package com.ninjaone.inventory.domain.entity;

import com.ninjaone.domain.entity.BaseEntity;
import com.ninjaone.domain.valueobject.*;
import com.ninjaone.inventory.domain.valueobject.DeviceServiceId;

public class DeviceService extends BaseEntity<DeviceServiceId> {
    private DeviceId deviceId;
    private final ServiceTypeId serviceTypeId;
    private final Money price;
    private DeviceServiceStatus deviceServiceStatus;

    private String message;

    private String status;


    private DeviceService(Builder builder) {
        super.setId(builder.deviceServiceId);
        deviceId = builder.deviceId;
        serviceTypeId = builder.serviceTypeId;
        price = builder.price;
        deviceServiceStatus = builder.deviceServiceStatus;
        message = builder.message;
        status = builder.status;
        setEnabled(builder.enabled);
        setCreatedDate(builder.createdDate);
        setModifiedDate(builder.lastModifiedDate);
    }

    public static Builder builder() {
        return new Builder();
    }


    public void initializeDeviceService(DeviceId deviceId, DeviceServiceId deviceServiceId) {
        this.deviceId = deviceId;
        super.setId(deviceServiceId);
    }

    public DeviceId getDeviceId() {
        return deviceId;
    }


    public Money getPrice() {
        return price;
    }

    public ServiceTypeId getServiceTypeId() {
        return serviceTypeId;
    }

    public DeviceServiceStatus getDeviceServiceStatus() {
        return deviceServiceStatus;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public void setDeviceId(DeviceId deviceId) {
        this.deviceId = deviceId;
    }

    public static final class Builder {
        private DeviceServiceId deviceServiceId;
        private DeviceId deviceId;
        private ServiceTypeId serviceTypeId;
        private Money price;

        private DeviceServiceStatus deviceServiceStatus;

        private String message;

        private String status;

        private Boolean enabled;

        private long createdDate;

        private long lastModifiedDate;



        private Builder() {
        }

        public Builder id(DeviceServiceId val) {
            deviceServiceId = val;
            return this;
        }

        public Builder deviceId(DeviceId val) {
            deviceId = val;
            return this;
        }

        public Builder serviceTypeId(ServiceTypeId val) {
            serviceTypeId = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder deviceServiceStatus(DeviceServiceStatus val) {
            deviceServiceStatus = val;
            return this;
        }

        public Builder message(String val) {
            message = val;
            return this;
        }

        public Builder status(String val) {
            status = val;
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


        public DeviceService build() {
            return new DeviceService(this);
        }
    }
}
