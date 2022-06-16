package com.ninjaone.inventory.domain.services.device;

import com.ninjaone.domain.valueobject.*;
import com.ninjaone.inventory.domain.InventoryDomainService;
import com.ninjaone.inventory.domain.dto.command.device.CreateDeviceCommand;
import com.ninjaone.inventory.domain.entity.*;
import com.ninjaone.inventory.domain.event.AssociateServicesEvent;
import com.ninjaone.inventory.domain.exception.InventoryDomainException;
import com.ninjaone.inventory.domain.mapper.DeviceDataMapper;
import com.ninjaone.inventory.domain.ports.output.message.publisher.service.AssociateServiceMessagePublisher;
import com.ninjaone.inventory.domain.ports.output.repository.CustomerRepository;
import com.ninjaone.inventory.domain.ports.output.repository.DeviceRepository;
import com.ninjaone.inventory.domain.ports.output.repository.DeviceServiceRepository;
import com.ninjaone.inventory.domain.ports.output.repository.ServiceTypeRepository;
import com.ninjaone.inventory.domain.services.type.device.TypeDeviceApplicationHelper;
import com.ninjaone.inventory.domain.valueobject.DeviceServiceId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.ninjaone.utils.NinjaStringUtils.defaultFormat;
import static com.ninjaone.utils.NinjaStringUtils.stripAccents;
import static com.ninjaone.utils.NinjaUuiiUtils.identifierToUuid;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class DeviceCreateHelper {
    private final InventoryDomainService inventoryDomainService;

    private final DeviceRepository deviceRepository;

    private final ServiceTypeRepository serviceTypeRepository;

    private final CustomerRepository customerRepository;

    private final DeviceDataMapper deviceDataMapper;

    private final TypeDeviceApplicationHelper typeDeviceApplicationHelper;

    private final AssociateServiceMessagePublisher associateServiceMessagePublisher;

    private final DeviceServiceRepository deviceServiceRepository;

    public DeviceCreateHelper(InventoryDomainService inventoryDomainService,
                              DeviceRepository deviceRepository,
                              ServiceTypeRepository serviceTypeRepository,
                              CustomerRepository customerRepository,
                              DeviceDataMapper deviceDataMapper,
                              TypeDeviceApplicationHelper typeDeviceApplicationHelper,
                              AssociateServiceMessagePublisher associateServiceMessagePublisher,
                              DeviceServiceRepository deviceServiceRepository) {
        this.inventoryDomainService = inventoryDomainService;
        this.deviceRepository = deviceRepository;
        this.serviceTypeRepository = serviceTypeRepository;
        this.customerRepository = customerRepository;
        this.deviceDataMapper = deviceDataMapper;
        this.typeDeviceApplicationHelper = typeDeviceApplicationHelper;
        this.associateServiceMessagePublisher = associateServiceMessagePublisher;
        this.deviceServiceRepository = deviceServiceRepository;
    }

    @Transactional
    public AssociateServicesEvent persistDevice(CreateDeviceCommand createDeviceCommand){
        typeDeviceApplicationHelper.controlExistence(identifierToUuid(createDeviceCommand.getTypeDeviceId()));
        Customer customer = checkCustomer(identifierToUuid(createDeviceCommand.getCustomerID()));
        controlUnique(null, createDeviceCommand);
        Device device = deviceDataMapper.createDeviceCommandOrderToDevice(createDeviceCommand);
        device.initializeDevice();
        saveDevice(device);
        AssociateServicesEvent deviceCreateEvent = inventoryDomainService.associateServices(device,associateServiceMessagePublisher);
        log.info("Device is created with {}", deviceCreateEvent.getDevice().getId().getValue());
        return  deviceCreateEvent;
    }


    @Transactional
    public AssociateServicesEvent update(UUID idDevice, CreateDeviceCommand createDeviceCommand){
        Device originalDevice = controlExistence(idDevice);
        typeDeviceApplicationHelper.controlExistence(identifierToUuid(createDeviceCommand.getTypeDeviceId()));
        Customer customer = checkCustomer(identifierToUuid(createDeviceCommand.getCustomerID()));
        controlUnique(idDevice, createDeviceCommand);
        Device device = deviceDataMapper.createDeviceCommandOrderToDevice(createDeviceCommand);
        device.setId(new DeviceId(idDevice));
        device.setTrackingId(originalDevice.getTrackingId());
        device.setDeviceStatus(DeviceStatus.PENDING);
        checkDeviceServices(device);
        saveDevice(device);
        AssociateServicesEvent deviceCreateEvent = inventoryDomainService.associateServices(device,associateServiceMessagePublisher);
        log.info("Device is updated with {}", deviceCreateEvent.getDevice().getId().getValue());
        return  deviceCreateEvent;
    }

    private void checkDeviceServices(Device device) {

        List<DeviceService> deviceServices = deviceServiceRepository.findByDeviceIdAndDeviceEnabledTrue(device.getId());

        device.getDeviceServices().forEach(deviceService -> {

            if(deviceServices.stream().filter(ds -> ds.getServiceTypeId().getValue().equals(deviceService.getServiceTypeId().getValue())).count() == 0){
                deviceServices.add(DeviceService.builder()
                                .id(new DeviceServiceId(UUID.randomUUID()))
                                .deviceId(device.getId())
                                .serviceTypeId(deviceService.getServiceTypeId())
                                .price(Money.ZERO)
                                .status(DeviceServiceStatus.PENDING.name())
                        .build());
            }
        });

        device.setDeviceServices(deviceServices);

    }


    public Customer checkCustomer(UUID customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if(customer.isEmpty()){
            log.warn("The customer with id {} not exist ", customerId);
            throw new InventoryDomainException("Could not find customer with customer id: "+ customerId);
        }

        return customer.get();
    }



    private Device saveDevice(Device device){
        Device deviceResult = deviceRepository.save(device);
        if(deviceResult == null){
            throw new InventoryDomainException("Could not save device!");
        }

        log.info("Device is saved with id {}", deviceResult.getId().getValue());
        return deviceResult;
    }

    private ServiceType serviceTypeById(UUID serviceTypeId){
        Optional<ServiceType> serviceTypeResult = serviceTypeRepository.findById(serviceTypeId);

        if(serviceTypeResult.isEmpty()){
            log.warn("The service type with id {} not exist ", serviceTypeId);
            throw new InventoryDomainException("Could not find the service type with id: "+ serviceTypeId);
        }

        return serviceTypeResult.get();

    }


    /**
     * Check if device is unique
     * @param id
     * @param createDeviceCommand
     */
    private void controlUnique(UUID id, CreateDeviceCommand createDeviceCommand) {
        Optional<Device> device;
        if(id == null){
            device = deviceRepository.findTopByCustomerIdAndTypeDeviceIdAndSystemName(
                    new CustomerId(identifierToUuid(createDeviceCommand.getCustomerID())),
                    new TypeDeviceId(identifierToUuid(createDeviceCommand.getTypeDeviceId())),
                    stripAccents(defaultFormat(createDeviceCommand.getSystemName()))
            );
        }else{
            device = deviceRepository.findTopByCustomerIdAndTypeDeviceIdAndSystemNameAndIdNot(
                    new CustomerId(identifierToUuid(createDeviceCommand.getCustomerID())),
                    new TypeDeviceId(identifierToUuid(createDeviceCommand.getTypeDeviceId())),
                    stripAccents(defaultFormat(createDeviceCommand.getSystemName())),
                    new DeviceId(id)
            );
        }
        if(device.isPresent()) {
            throw new InventoryDomainException("Device already exists");
        }
    }



    /**
     * Control if the operative system exists
     * @param id
     */
    public Device controlExistence(UUID id) {
        Optional<Device> device = deviceRepository.findById(new DeviceId(id));
        if(!device.isPresent()) {
            throw new InventoryDomainException("Device with id "+id+" does not exist");
        }

        return device.get();
    }


}
