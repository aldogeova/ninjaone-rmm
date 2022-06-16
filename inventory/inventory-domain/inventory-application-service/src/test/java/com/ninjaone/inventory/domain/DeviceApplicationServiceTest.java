//package com.ninjaone.inventory.domain;
//
//import com.ninjaone.domain.valueobject.CustomerId;
//import com.ninjaone.domain.valueobject.DeviceStatus;
//import com.ninjaone.domain.valueobject.TypeDeviceId;
//import com.ninjaone.inventory.domain.dto.create.CreateDeviceCommand;
//import com.ninjaone.inventory.domain.dto.create.CreateDeviceResponse;
//import com.ninjaone.inventory.domain.dto.create.DeviceService;
//import com.ninjaone.inventory.domain.entity.*;
//import com.ninjaone.inventory.domain.mapper.DeviceDataMapper;
//import com.ninjaone.inventory.domain.ports.input.service.DeviceApplicationService;
//import com.ninjaone.inventory.domain.ports.output.repository.CustomerRepository;
//import com.ninjaone.inventory.domain.ports.output.repository.DeviceRepository;
//import com.ninjaone.inventory.domain.ports.output.repository.ServiceTypeRepository;
//import com.ninjaone.inventory.domain.valueobject.DeviceClassificationId;
//import com.ninjaone.inventory.domain.valueobject.OperativeSystemId;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.Bean;
//
//import java.math.BigDecimal;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@SpringBootTest(classes = DeviceTestConfiguration.class)
//public class DeviceApplicationServiceTest {
//
//    @Autowired
//    private DeviceApplicationService deviceApplicationService;
//
//    @Autowired
//    private DeviceDataMapper deviceDataMapper;
//
//    @Autowired
//    public DeviceRepository deviceRepository;
//
//    @Autowired
//    public CustomerRepository customerRepository;
//
//    @Autowired
//    public ServiceTypeRepository serviceTypeRepository;
//
//    private CreateDeviceCommand createDeviceCommand;
//    private CreateDeviceCommand createDeviceCommandWrongServices;
//
//    private final UUID CUSTOMER_ID = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb41");
//    private final UUID TYPE_DEVICE_ID = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb45");
//    private final UUID SERVICE_TYPE_ID_1 = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb48");
//    private final UUID SERVICE_TYPE_ID_2 = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb49");
//    private final UUID SERVICE_TYPE_ID_3 = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb50");
//    private final UUID SERVICE_TYPE_ID_4 = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb51");
//
//    private final UUID WINDOWS_OPERATIVE_SYSTEM = UUID.fromString("215b5f8-0249-4dc5-89a3-51fd148cfb10");
//    private final UUID MAC_OPERATIVE_SYSTEM = UUID.fromString("15b5f8-0249-4dc5-89a3-51fd148cfb11");
//
//    private final UUID LAPTOP = UUID.fromString("a15b5f8-0249-4dc5-89a3-51fd148cfb12");
//    private final UUID DESKTOP = UUID.fromString("e15b5f8-0249-4dc5-89a3-51fd148cfb13");
//    private final UUID WORKSTATION = UUID.fromString("15b5f8-0249-4dc5-89a3-51fd148cfb14");
//
//    private final UUID DEVICE_ID = UUID.fromString("15a497c1-0f4b-4eff-b9f4-c402c8c07afb");
//    private final UUID SAGA_ID = UUID.fromString("15a497c1-0f4b-4eff-b9f4-c402c8c07afa");
//    private final BigDecimal PRICE = new BigDecimal("13.00");
//
//    @BeforeAll
//    public void init(){
//        createDeviceCommand = CreateDeviceCommand.builder()
//                .typeDeviceId(TYPE_DEVICE_ID)
//                .customerID(CUSTOMER_ID)
//                .price(PRICE)
//                .deviceServices(List.of(
//                        DeviceService.builder()
//                                .serviceTypeId(SERVICE_TYPE_ID_1)
//                                .price(new BigDecimal(4))
//                                .build(),
//                        DeviceService.builder()
//                                .serviceTypeId(SERVICE_TYPE_ID_2)
//                                .price(new BigDecimal(5))
//                                .build(),
//                        DeviceService.builder()
//                                .serviceTypeId(SERVICE_TYPE_ID_3)
//                                .price(new BigDecimal(3))
//                                .build(),
//                        DeviceService.builder()
//                                .serviceTypeId(SERVICE_TYPE_ID_4)
//                                .price(new BigDecimal(1))
//                                .build()
//                        ))
//                .build();
//
//        createDeviceCommandWrongServices = CreateDeviceCommand.builder()
//                .typeDeviceId(TYPE_DEVICE_ID)
//                .customerID(CUSTOMER_ID)
//                .price(PRICE)
//                .deviceServices(List.of(
//                        DeviceService.builder()
//                                .serviceTypeId(SERVICE_TYPE_ID_1)
//                                .price(new BigDecimal(4))
//                                .build(),
//                        DeviceService.builder()
//                                .serviceTypeId(SERVICE_TYPE_ID_2)
//                                .price(new BigDecimal(7))
//                                .build(),
//                        DeviceService.builder()
//                                .serviceTypeId(SERVICE_TYPE_ID_3)
//                                .price(new BigDecimal(3))
//                                .build(),
//                        DeviceService.builder()
//                                .serviceTypeId(SERVICE_TYPE_ID_4)
//                                .price(new BigDecimal(1))
//                                .build()
//                ))
//                .build();
//
//
//
//        Customer customer = new Customer(new CustomerId(CUSTOMER_ID));
//        ServiceType serviceType = ServiceType.builder()
//                .typeDevice(TypeDevice.builder()
//                        .id(new TypeDeviceId(TYPE_DEVICE_ID))
//                        .operativeSystem(OperativeSystem.builder()
//                                .id(new OperativeSystemId(WINDOWS_OPERATIVE_SYSTEM))
//                                .build())
//                        .deviceClassification(DeviceClassification.builder()
//                                .id(new DeviceClassificationId(LAPTOP))
//                                .build())
//
//                        .build()
//
//                )
//                .build();
//
//        Device device = deviceDataMapper.createDeviceCommandOrderToDevice(createDeviceCommand);
//
//        when(customerRepository.findCustomer(CUSTOMER_ID)).thenReturn(Optional.of(customer));
//        when(serviceTypeRepository.existByTypeDeviceAndService(SERVICE_TYPE_ID_1, TYPE_DEVICE_ID)).thenReturn(true);
//        when(serviceTypeRepository.existByTypeDeviceAndService(SERVICE_TYPE_ID_2, TYPE_DEVICE_ID)).thenReturn(true);
//        when(serviceTypeRepository.existByTypeDeviceAndService(SERVICE_TYPE_ID_3, TYPE_DEVICE_ID)).thenReturn(true);
//        when(serviceTypeRepository.existByTypeDeviceAndService(SERVICE_TYPE_ID_4, TYPE_DEVICE_ID)).thenReturn(true);
//        when(deviceRepository.save(any(Device.class))).thenReturn(device);
//        when(serviceTypeRepository.findById(SERVICE_TYPE_ID_1)).thenReturn(Optional.of(serviceType));
//        when(serviceTypeRepository.findById(SERVICE_TYPE_ID_2)).thenReturn(Optional.of(serviceType));
//        when(serviceTypeRepository.findById(SERVICE_TYPE_ID_3)).thenReturn(Optional.of(serviceType));
//        when(serviceTypeRepository.findById(SERVICE_TYPE_ID_4)).thenReturn(Optional.of(serviceType));
//
//    }
//
//    @Test
//    public void testCreateDevice(){
//        CreateDeviceResponse createDeviceResponse = deviceApplicationService.createDevice(createDeviceCommand);
//        assertEquals(createDeviceResponse.getDeviceStatus(), DeviceStatus.PENDING);
//        assertEquals(createDeviceResponse.getMessage(), "Device created successfully");
//    }
//
//}
