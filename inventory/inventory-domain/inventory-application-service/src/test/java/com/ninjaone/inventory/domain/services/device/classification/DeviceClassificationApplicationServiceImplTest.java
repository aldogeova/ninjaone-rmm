package com.ninjaone.inventory.domain.services.device.classification;

import com.ninjaone.domain.base.NinjaPage;
import com.ninjaone.inventory.domain.TestConfiguration;
import com.ninjaone.inventory.domain.UtilTest;
import com.ninjaone.inventory.domain.dto.command.device.classification.DeviceClassificationCommand;
import com.ninjaone.inventory.domain.dto.command.device.classification.DeviceClassificationResponse;
import com.ninjaone.inventory.domain.dto.service.DeviceClassificationApplicationService;
import com.ninjaone.inventory.domain.entity.DeviceClassification;
import com.ninjaone.inventory.domain.exception.InventoryDomainException;
import com.ninjaone.inventory.domain.ports.output.repository.DeviceClassificationRepository;
import com.ninjaone.inventory.domain.valueobject.DeviceClassificationId;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.ninjaone.utils.NinjaStringUtils.defaultFormat;
import static com.ninjaone.utils.NinjaStringUtils.stripAccents;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = TestConfiguration.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DeviceClassificationApplicationServiceImplTest {

    /**
     * CONSTANTS
     */
    private final String nameEntity = "DeviceClassification";

    private final UUID LAPTOP_UUID = UUID.fromString("2c938088-8093-2899-0180-932c85560007");
    private final String LAPTOP_NAME = "LAPTOP";
    private final String LAPTOP_DESCRIPTION ="COMPUTER THAT IS PORTABLE AND SUITABLE FOR USE WHILE TRAVELING";


    private final UUID DESKTOP_UUID = UUID.fromString("2c938088-8093-2899-0180-932c85a70009");
    private final String DESKTOP_NAME = "DESKTOP";
    private final String DESKTOP_DESCRIPTION ="A COMPUTER SUITABLE FOR USE AT AN ORDINARY DESK";


    private final UUID WORKSTATION_UUID = UUID.fromString("2c938088-8093-2899-0180-932ccec5001b");
    private final String WORKSTATION_NAME = "WORKSTATION";
    private final String WORKSTATION_DESCRIPTION ="A DESKTOP COMPUTER TERMINAL, TYPICALLY NETWORKED AND MORE POWERFULL THAN A PERSONAL COMPUTER";

    /**
     * REQUEST
     */
    private DeviceClassificationCommand deviceClassificationCommandLaptop;

    private DeviceClassificationCommand deviceClassificationCommandWithoutName;
    private DeviceClassification deviceClassificationLaptopCreated;

    /**
     * BEANS
     */
    @Autowired
    private DeviceClassificationApplicationService deviceClassificationApplicationService;

    @Autowired
    private DeviceClassificationRepository deviceClassificationRepository;


    @BeforeAll
    public void setUp() {

        deviceClassificationCommandLaptop = DeviceClassificationCommand.builder()
                .name(LAPTOP_NAME)
                .description(LAPTOP_DESCRIPTION)
                .build();

        deviceClassificationCommandWithoutName = DeviceClassificationCommand.builder()
                .description(LAPTOP_DESCRIPTION)
                .build();

        deviceClassificationLaptopCreated = DeviceClassification.builder()
                .id(new DeviceClassificationId(LAPTOP_UUID))
                .name(LAPTOP_NAME)
                .description(LAPTOP_DESCRIPTION)
                .enabled(true)
                .build();

    }


    @Test
    @Order(1)
    public void createNew() {
        when(deviceClassificationRepository.findTopByName(LAPTOP_NAME)).thenReturn(Optional.empty());
        when(deviceClassificationRepository.save(any(DeviceClassification.class))).thenReturn(deviceClassificationLaptopCreated);
        DeviceClassificationResponse response = deviceClassificationApplicationService.save(deviceClassificationCommandLaptop);
        Assertions.assertNotNull(response.getId(), ()-> nameEntity+" id is null");
        Assertions.assertEquals(nameEntity + UtilTest.CREATE_SUCCESSFULL, response.getMessage(), ()-> nameEntity+" message is not correct");
    }


    @Test
    @Order(2)
    public void createDuplicate() {
        DeviceClassification entityExistent = new DeviceClassification(new DeviceClassificationId(LAPTOP_UUID), LAPTOP_NAME, LAPTOP_DESCRIPTION);
        when(deviceClassificationRepository.findTopByName(stripAccents(defaultFormat(LAPTOP_NAME)))).thenReturn(Optional.of(entityExistent));
        InventoryDomainException uniqueException = Assertions.assertThrows(InventoryDomainException.class, ()-> deviceClassificationApplicationService.save(deviceClassificationCommandLaptop));
        Assertions.assertEquals(nameEntity + UtilTest.EXCEPTION_ALREADY_EXISTS, uniqueException.getMessage(), ()-> nameEntity+" message is not correct");
    }

    @Test
    @Order(3)
    public void validateRequiredFields() {
        InventoryDomainException requiredFieldsException = Assertions.assertThrows(InventoryDomainException.class, ()-> deviceClassificationApplicationService.save(deviceClassificationCommandWithoutName));
        Assertions.assertEquals("Name"+UtilTest.EMPTY_FIELD, requiredFieldsException.getMessage(), ()-> nameEntity+" message is not correct");
    }

    @Test
    @Order(4)
    public void updateOperativeSystem(){
        DeviceClassification entity = new DeviceClassification(new DeviceClassificationId(LAPTOP_UUID), LAPTOP_NAME+"_new_name", LAPTOP_DESCRIPTION);
        when(deviceClassificationRepository.findById(entity.getId())).thenReturn(Optional.of(entity));
        when(deviceClassificationRepository.findTopByNameAndIdNot(entity.getName(), entity.getId())).thenReturn(Optional.empty());

        DeviceClassificationCommand deviceClassificationCommandUpdate = DeviceClassificationCommand.builder()
                .name(entity.getName())
                .description(entity.getDescription())
                .build();

        DeviceClassificationResponse response = deviceClassificationApplicationService.update(entity.getId().getValue(), deviceClassificationCommandUpdate);
        Assertions.assertNotNull(response.getId(), ()-> nameEntity+" id is null");
        Assertions.assertEquals(nameEntity + UtilTest.UPDATED_SUCCESSFULL, response.getMessage(), ()-> nameEntity + " message is not correct");
    }


    @Test
    @Order(5)
    public void findById(){
        DeviceClassification entityCreated = DeviceClassification.builder()
                .id(new DeviceClassificationId(LAPTOP_UUID))
                .name(LAPTOP_NAME)
                .description(LAPTOP_DESCRIPTION)
                .enabled(true)
                .build();
        when(deviceClassificationRepository.findById(new DeviceClassificationId(LAPTOP_UUID))).thenReturn(Optional.of(entityCreated));
        DeviceClassificationResponse response = deviceClassificationApplicationService.findById(LAPTOP_UUID);
        Assertions.assertNotNull(response.getId(), ()-> nameEntity + " id is null");
        Assertions.assertEquals(entityCreated.getId().getValue(), LAPTOP_UUID, () -> "The uuid returned no is correct");
        Assertions.assertEquals(entityCreated.getName(), LAPTOP_NAME, () -> "The name returned no is correct");
        Assertions.assertEquals(entityCreated.getDescription(), LAPTOP_DESCRIPTION, () -> "The description returned no is correct");
    }

    @Test
    @Order(6)
    public void findAll(){
        List<DeviceClassification> list = new ArrayList<>();

        list.add(DeviceClassification.builder()
                .id(new DeviceClassificationId(LAPTOP_UUID))
                .name(LAPTOP_NAME)
                .description(LAPTOP_DESCRIPTION)
                .enabled(true)
                .build());

        list.add(DeviceClassification.builder()
                .id(new DeviceClassificationId(DESKTOP_UUID))
                .name(DESKTOP_NAME)
                .description(DESKTOP_DESCRIPTION)
                .enabled(true)
                .build());

        list.add(DeviceClassification.builder()
                .id(new DeviceClassificationId(WORKSTATION_UUID))
                .name(WORKSTATION_NAME)
                .description(WORKSTATION_DESCRIPTION)
                .enabled(true)
                .build());


        NinjaPage<DeviceClassification> page = NinjaPage.builder()
                .page(0)
                .totalPages(1)
                .totalElements(3)
                .size(10)
                .content(list)
                .build();

        when(deviceClassificationRepository.findAll(0,10)).thenReturn(page);

        NinjaPage<DeviceClassificationResponse> pageResponse = deviceClassificationApplicationService.getAll(0,10);

        Assertions.assertEquals(0, pageResponse.getPage(), () -> "The number of page isn't correct" );
        Assertions.assertEquals(3, pageResponse.getTotalElements(), () -> "The number of total elements isn't correct" );
        Assertions.assertEquals(10, pageResponse.getSize(), () -> "The size isn't correct" );

    }



    @Test
    @Order(7)
    public void delete(){
        DeviceClassification entityDelete = new DeviceClassification(new DeviceClassificationId(LAPTOP_UUID), LAPTOP_NAME, LAPTOP_DESCRIPTION);
        entityDelete.setEnabled(false);
        when(deviceClassificationRepository.findById(entityDelete.getId())).thenReturn(Optional.of(entityDelete));
        when(deviceClassificationRepository.save(entityDelete)).thenReturn(entityDelete);
        DeviceClassificationResponse response = deviceClassificationApplicationService.delete(LAPTOP_UUID);
        Assertions.assertFalse(response.isEnabled(), () -> nameEntity+" has not been deleted" );

    }


}