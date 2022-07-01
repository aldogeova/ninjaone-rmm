package com.ninjaone.inventory.domain.services.type.device;

import com.ninjaone.inventory.domain.TestConfiguration;
import com.ninjaone.inventory.domain.dto.service.TypeDeviceApplicationService;
import com.ninjaone.inventory.domain.ports.output.repository.TypeDeviceRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = TestConfiguration.class)
class TypeDeviceApplicationServiceImplTest {

    /**
     * CONSTANTS
     */
    private final String nameEntity = "TypeDevice";

    /**
     * REQUEST
     */

    /**
     * BEANS
     */
    @Autowired
    private TypeDeviceApplicationService typeDeviceApplicationService;

    @Autowired
    private TypeDeviceRepository typeDeviceRepository;


    @BeforeAll
    public void setUp() {

    }


    @Test
    public void createNew() {
//        TypeDeviceResponse response = typeDeviceApplicationService.save(typeDeviceCommandLaptop);
//        Assertions.assertNotNull(response.getId(), ()-> nameEntity+" id is null");
//        Assertions.assertEquals(nameEntity + UtilTest.CREATE_SUCCESSFULL, response.getMessage(), ()-> nameEntity+" message is not correct");
    }

}