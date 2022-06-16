package com.ninjaone.inventory.domain.entity;

import com.ninjaone.domain.entity.AggregateRoot;
import com.ninjaone.domain.valueobject.CustomerId;
import com.ninjaone.domain.valueobject.DeviceId;
import com.ninjaone.domain.valueobject.DeviceStatus;
import com.ninjaone.domain.valueobject.Money;
import com.ninjaone.inventory.domain.exception.InventoryDomainException;
import com.ninjaone.inventory.domain.valueobject.DeviceServiceId;
import com.ninjaone.inventory.domain.valueobject.TrackingId;

import java.util.List;
import java.util.UUID;

public class Device extends AggregateRoot<DeviceId> {

    public static final String FAILURE_MESSAGE_DELIMITER = ",";

    private final CustomerId customerId;

    private final String systemName;
    private final TypeDevice typeDevice;
    private List<DeviceService> deviceServices;
    private TrackingId trackingId;
    private DeviceStatus deviceStatus;
    private List<String> failureMessages;

    public static Builder newBuilder() {
        return new Builder();
    }


    public void initializeDevice(){
        setId(new DeviceId(UUID.randomUUID()));
        setTrackingId(new TrackingId(UUID.randomUUID()));
        deviceStatus = DeviceStatus.PENDING;
        initializeDeviceServices();
        setEnabled(true);
    }

    public void validateDevice(){
        validateInitalDevice();
    }

    private void validateInitalDevice() {
        if(deviceStatus != null || getId() != null) {
            throw new InventoryDomainException("Device is not in correct state for initialization");
        }
    }


    private void initializeDeviceServices() {
        for (DeviceService deviceService: deviceServices) {
            deviceService.initializeDeviceService(super.getId(), new DeviceServiceId(UUID.randomUUID()));
        }
    }

    private void asociateServices(){
        if(deviceStatus != DeviceStatus.PENDING){
            throw new InventoryDomainException("Device is not in correct state to check services");
        }
    }

    private void incorrectAsociateServices(List<String> failureMessages){
        if(deviceStatus != DeviceStatus.PENDING){
            throw new InventoryDomainException("Device is not in correct state to cancel creation of device services");
        }

        deviceStatus = DeviceStatus.INVALID;
        updateFailureMessages(failureMessages);
    }

    private void updateFailureMessages(List<String> failureMessages) {
        if(this.failureMessages != null && failureMessages != null){
            this.failureMessages.addAll(failureMessages.stream().filter(message -> !message.isEmpty()).toList());
        }

        if(this.failureMessages == null){
            this.failureMessages = failureMessages;
        }
    }

    private void removeServices(){
        if(deviceStatus != DeviceStatus.PENDING){
            throw new InventoryDomainException("Device is not in correct state to check services");
        }

        deviceStatus = DeviceStatus.VALIDATED;
    }


    private Device(Builder builder) {
        super.setId(builder.deviceId);
        customerId = builder.customerId;
        systemName = builder.systemName;
        typeDevice = builder.typeDevice;
        deviceServices = builder.deviceServices;
        trackingId = builder.trackingId;
        deviceStatus = builder.deviceStatus;
        failureMessages = builder.failureMessages;
        setEnabled(builder.enabled);
        setCreatedDate(builder.createdDate);
        setModifiedDate(builder.lastModifiedDate);
    }

    public static Builder builder() {
        return new Builder();
    }

    public CustomerId getCustomerId() {
        return customerId;
    }

    public TypeDevice getTypeDevice() {
        return typeDevice;
    }

    public List<DeviceService> getDeviceServices() {
        return deviceServices;
    }

    public DeviceStatus getDeviceStatus() {
        return deviceStatus;
    }

    public List<String> getFailureMessages() {
        return failureMessages;
    }

    public TrackingId getTrackingId() {
        return trackingId;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setTrackingId(TrackingId trackingId) {
        this.trackingId = trackingId;
    }

    public void setDeviceStatus(DeviceStatus deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public void setDeviceServices(List<DeviceService> deviceServices) {
        this.deviceServices = deviceServices;
    }

    public static final class Builder {
        private DeviceId deviceId;
        private CustomerId customerId;
        private String systemName;
        private TypeDevice typeDevice;
        private List<DeviceService> deviceServices;
        private TrackingId trackingId;
        private DeviceStatus deviceStatus;
        private List<String> failureMessages;

        private Boolean enabled;

        private long createdDate;

        private long lastModifiedDate;


        private Builder() {
        }

        public Builder deviceId(DeviceId val) {
            deviceId = val;
            return this;
        }

        public Builder customerId(CustomerId val) {
            customerId = val;
            return this;
        }

        public Builder systemName(String val) {
            systemName = val;
            return this;
        }

        public Builder typeDevice(TypeDevice val) {
            typeDevice = val;
            return this;
        }

        public Builder deviceServices(List<DeviceService> val) {
            deviceServices = val;
            return this;
        }

        public Builder trackingId(TrackingId val) {
            trackingId = val;
            return this;
        }

        public Builder deviceStatus(DeviceStatus val) {
            deviceStatus = val;
            return this;
        }

        public Builder failureMessages(List<String> val) {
            failureMessages = val;
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


        public Device build() {
            return new Device(this);
        }

    }
}
