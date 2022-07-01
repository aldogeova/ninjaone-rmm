package com.ninjaone.inventory.domain.services.operative.system;

import com.ninjaone.domain.base.NinjaPage;
import com.ninjaone.inventory.domain.TestConfiguration;
import com.ninjaone.inventory.domain.dto.command.operative.system.OperativeSystemCommand;
import com.ninjaone.inventory.domain.dto.command.operative.system.OperativeSystemResponse;
import com.ninjaone.inventory.domain.dto.service.OperativeSystemApplicationService;
import com.ninjaone.inventory.domain.entity.OperativeSystem;
import com.ninjaone.inventory.domain.exception.InventoryDomainException;
import com.ninjaone.inventory.domain.ports.output.repository.OperativeSystemRepository;
import com.ninjaone.inventory.domain.valueobject.OperativeSystemId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
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
public class OperativeSystemApplicationServiceImplTest {


    /**
     * CONSTANTS
     */

    private final UUID WINDOWS_OS_UUID = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb41");
    private final String WINDOWS_OS_NAME = "Windows";
    private final String WINDOWS_OS_DESCRIPTION = "Windows Operating System";

    private final UUID   LINUX_OS_UUID = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb42");
    private final String LINUX_OS_NAME = "Linux";
    private final String LINUX_OS_DESCRIPTION = "Linux Operating System";

    private final UUID   MAC_OS_UUID = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb43");
    private final String MAC_OS_NAME = "Mac";
    private final String MAC_OS_DESCRIPTION = "Mac Operating System";

    private final String OS_CREATE_SUCCESSFULL = "OperativeSystem created successfully";

    private final String OS_UPDATED_SUCCESSFULL = "OperativeSystem updated successfully";
    private final String EXCEPTION_OS_ALREADY_EXISTS = "OperativeSystem already exists";

    private final String EXCEPTION_OS_WITHOUT_NAME = "Name is empty, please fill it";


    /**
     * BEANS
     */
    @Autowired
    private OperativeSystemApplicationService operativeSystemApplicationService;

    @Autowired
    private OperativeSystemRepository operativeSystemRepository;


    /**
     * REQUESTS
     */
    private OperativeSystemCommand operativeSystemCommandNewRegisterWindows;
    private OperativeSystemCommand operativeSystemCommandNewRegisterLinux;
    private OperativeSystemCommand operativeSystemCommandNewRegisterMac;

    private OperativeSystemCommand operativeSystemCommandWithoutName;

    @BeforeAll
    public void setUp() {
        operativeSystemCommandNewRegisterWindows = OperativeSystemCommand.builder()
                .name(WINDOWS_OS_NAME)
                .description(WINDOWS_OS_DESCRIPTION)
                .build();

        operativeSystemCommandNewRegisterLinux = OperativeSystemCommand.builder()
                .name(LINUX_OS_NAME)
                .description(LINUX_OS_DESCRIPTION)
                .build();

        operativeSystemCommandNewRegisterMac = OperativeSystemCommand.builder()
                .name(MAC_OS_NAME)
                .description(MAC_OS_DESCRIPTION)
                .build();

        operativeSystemCommandWithoutName = OperativeSystemCommand.builder()
                .description(WINDOWS_OS_DESCRIPTION)
                .build();

        OperativeSystem operativeSystemWindowsCreated = OperativeSystem.builder()
                .id(new OperativeSystemId(WINDOWS_OS_UUID))
                .name(WINDOWS_OS_NAME)
                .description(WINDOWS_OS_DESCRIPTION)
                .enabled(true)
                .build();

        when(operativeSystemRepository.findTopByName(WINDOWS_OS_NAME)).thenReturn(Optional.empty());
        when(operativeSystemRepository.save(any(OperativeSystem.class))).thenReturn(operativeSystemWindowsCreated);

    }

    @Test
    public void createNewOperativeSystemWindows() {
        OperativeSystemResponse operativeSystemResponse = operativeSystemApplicationService.save(operativeSystemCommandNewRegisterWindows);
        Assertions.assertNotNull(operativeSystemResponse.getId(), ()-> "OperativeSystem id is null");
        Assertions.assertEquals(OS_CREATE_SUCCESSFULL, operativeSystemResponse.getMessage(), ()-> "OperativeSystem message is not correct");
    }


    @Test
    public void createDuplicateOperativeSystemWindows() {
        OperativeSystem operativeSystemWindowsExistent = new OperativeSystem(new OperativeSystemId(WINDOWS_OS_UUID), WINDOWS_OS_NAME, WINDOWS_OS_DESCRIPTION);
        when(operativeSystemRepository.findTopByName(stripAccents(defaultFormat(WINDOWS_OS_NAME)))).thenReturn(Optional.of(operativeSystemWindowsExistent));
        InventoryDomainException uniqueException = Assertions.assertThrows(InventoryDomainException.class, ()-> operativeSystemApplicationService.save(operativeSystemCommandNewRegisterWindows));
        Assertions.assertEquals(EXCEPTION_OS_ALREADY_EXISTS, uniqueException.getMessage(), ()-> "OperativeSystem message is not correct");
    }

    @Test
    public void validateRequiredFields() {
        InventoryDomainException requiredFieldsException = Assertions.assertThrows(InventoryDomainException.class, ()-> operativeSystemApplicationService.save(operativeSystemCommandWithoutName));
        Assertions.assertEquals(EXCEPTION_OS_WITHOUT_NAME, requiredFieldsException.getMessage(), ()-> "OperativeSystem message is not correct");
    }

    @Test
    public void updateOperativeSystem(){
        OperativeSystem operativeSystemWindowsUpdate = new OperativeSystem(new OperativeSystemId(WINDOWS_OS_UUID), WINDOWS_OS_NAME+"_new_name", WINDOWS_OS_DESCRIPTION);
        when(operativeSystemRepository.findById(operativeSystemWindowsUpdate.getId())).thenReturn(Optional.of(operativeSystemWindowsUpdate));
        when(operativeSystemRepository.findTopByNameAndIdNot(operativeSystemWindowsUpdate.getName(), operativeSystemWindowsUpdate.getId())).thenReturn(Optional.empty());

        OperativeSystemCommand operativeSystemCommandUpdateRegisterWindows = OperativeSystemCommand.builder()
                .name(operativeSystemWindowsUpdate.getName())
                .description(operativeSystemWindowsUpdate.getDescription())
                .build();

        OperativeSystemResponse operativeSystemResponse = operativeSystemApplicationService.update(operativeSystemWindowsUpdate.getId().getValue(), operativeSystemCommandUpdateRegisterWindows);
        Assertions.assertNotNull(operativeSystemResponse.getId(), ()-> "OperativeSystem id is null");
        Assertions.assertEquals(OS_UPDATED_SUCCESSFULL, operativeSystemResponse.getMessage(), ()-> "OperativeSystem message is not correct");
    }


    @Test
    public void findById(){
        OperativeSystem operativeSystemWindowsCreated = OperativeSystem.builder()
                .id(new OperativeSystemId(WINDOWS_OS_UUID))
                .name(WINDOWS_OS_NAME)
                .description(WINDOWS_OS_DESCRIPTION)
                .enabled(true)
                .build();
        when(operativeSystemRepository.findById(new OperativeSystemId(WINDOWS_OS_UUID))).thenReturn(Optional.of(operativeSystemWindowsCreated));
        OperativeSystemResponse operativeSystemResponse = operativeSystemApplicationService.findById(WINDOWS_OS_UUID);
        Assertions.assertNotNull(operativeSystemResponse.getId(), ()-> "OperativeSystem id is null");
        Assertions.assertEquals(operativeSystemWindowsCreated.getId().getValue(), WINDOWS_OS_UUID, () -> "The uuid returned no is correct");
        Assertions.assertEquals(operativeSystemWindowsCreated.getName(), WINDOWS_OS_NAME, () -> "The name returned no is correct");
        Assertions.assertEquals(operativeSystemWindowsCreated.getDescription(), WINDOWS_OS_DESCRIPTION, () -> "The description returned no is correct");
    }

    @Test
    public void findAll(){
        List<OperativeSystem> operativeSystems = new ArrayList<>();

        operativeSystems.add(OperativeSystem.builder()
                .id(new OperativeSystemId(WINDOWS_OS_UUID))
                .name(WINDOWS_OS_NAME)
                .description(WINDOWS_OS_DESCRIPTION)
                .enabled(true)
                .build());

        operativeSystems.add(OperativeSystem.builder()
                .id(new OperativeSystemId(LINUX_OS_UUID))
                .name(LINUX_OS_NAME)
                .description(LINUX_OS_DESCRIPTION)
                .enabled(true)
                .build());

        operativeSystems.add(OperativeSystem.builder()
                .id(new OperativeSystemId(MAC_OS_UUID))
                .name(MAC_OS_NAME)
                .description(MAC_OS_DESCRIPTION)
                .enabled(true)
                .build());


        NinjaPage<OperativeSystem> page = NinjaPage.builder()
                .page(0)
                .totalPages(1)
                .totalElements(3)
                .size(10)
                .content(operativeSystems)
                .build();

        when(operativeSystemRepository.findAll(0,10)).thenReturn(page);

        NinjaPage<OperativeSystemResponse> pageResponse = operativeSystemApplicationService.getAll(0,10);

        Assertions.assertEquals(0, pageResponse.getPage(), () -> "The number of page isn't correct" );
        Assertions.assertEquals(3, pageResponse.getTotalElements(), () -> "The number of total elements isn't correct" );
        Assertions.assertEquals(10, pageResponse.getSize(), () -> "The size isn't correct" );

    }



    @Test
    public void delete(){
        OperativeSystem operativeSystemWindowsDelete = new OperativeSystem(new OperativeSystemId(WINDOWS_OS_UUID), WINDOWS_OS_NAME, WINDOWS_OS_DESCRIPTION);
        operativeSystemWindowsDelete.setEnabled(false);
        when(operativeSystemRepository.findById(operativeSystemWindowsDelete.getId())).thenReturn(Optional.of(operativeSystemWindowsDelete));
        when(operativeSystemRepository.save(operativeSystemWindowsDelete)).thenReturn(operativeSystemWindowsDelete);
        OperativeSystemResponse deleteResponse = operativeSystemApplicationService.delete(WINDOWS_OS_UUID);
        Assertions.assertFalse(deleteResponse.isEnabled(), () ->"The operating system has not been deleted" );

    }

}