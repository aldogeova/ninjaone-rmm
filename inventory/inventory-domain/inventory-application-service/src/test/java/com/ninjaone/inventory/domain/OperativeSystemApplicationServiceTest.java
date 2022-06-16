//package com.ninjaone.inventory.domain;
//
//import com.ninjaone.inventory.domain.dto.command.operative.system.OperativeSystemCommand;
//import com.ninjaone.inventory.domain.dto.command.operative.system.OperativeSystemResponse;
//import com.ninjaone.inventory.domain.dto.service.OperativeSystemApplicationService;
//import com.ninjaone.inventory.domain.entity.OperativeSystem;
//import com.ninjaone.inventory.domain.exception.InventoryDomainException;
//import com.ninjaone.inventory.domain.ports.output.repository.OperativeSystemRepository;
//import com.ninjaone.inventory.domain.valueobject.OperativeSystemId;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.Optional;
//import java.util.UUID;
//
//import static com.ninjaone.utils.NinjaStringUtils.stripAccents;
//import static com.ninjaone.utils.NinjaStringUtils.defaultFormat;
//
//import static org.mockito.Mockito.when;
//import static org.mockito.Mockito.any;
//
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@SpringBootTest(classes = OperativeSystemTestConfiguration.class)
//public class OperativeSystemApplicationServiceTest {
//
//    /**
//     * CONSTANTS
//     */
//
//    private final UUID   WINDOWS_OS_UUID = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb41");
//    private final String WINDOWS_OS_NAME = "Windows";
//    private final String WINDOWS_OS_DESCRIPTION = "Windows Operating System";
//
//    private final UUID   LINUX_OS_UUID = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb42");
//    private final String LINUX_OS_NAME = "Linux";
//    private final String LINUX_OS_DESCRIPTION = "Linux Operating System";
//
//    private final UUID   MAC_OS_UUID = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb43");
//    private final String MAC_OS_NAME = "Mac";
//    private final String MAC_OS_DESCRIPTION = "Mac Operating System";
//
//    private final String OS_CREATE_SUCCESSFULL = "OperativeSystem created successfully";
//    private final String EXCEPTION_OS_ALREADY_EXISTS = "OperativeSystem already exists";
//
//    private final String EXCEPTION_OS_WITHOUT_NAME = "Name is empty, please fill it";
//
//    /**
//     * BEANS
//     */
//    @Autowired
//    private OperativeSystemApplicationService operativeSystemApplicationService;
//
//    @Autowired
//    private OperativeSystemRepository operativeSystemRepository;
//
//
//    /**
//     * REQUESTS
//     */
//    private OperativeSystemCommand operativeSystemCommandNewRegisterWindows;
//    private OperativeSystemCommand operativeSystemCommandNewRegisterLinux;
//    private OperativeSystemCommand operativeSystemCommandNewRegisterMac;
//
//    private OperativeSystemCommand operativeSystemCommandWithoutName;
//
//    @BeforeAll
//    public void setUp() {
//        operativeSystemCommandNewRegisterWindows = OperativeSystemCommand.builder()
//                .name(WINDOWS_OS_NAME)
//                .description(WINDOWS_OS_DESCRIPTION)
//                .build();
//
//        operativeSystemCommandNewRegisterLinux = OperativeSystemCommand.builder()
//                .name(LINUX_OS_NAME)
//                .description(LINUX_OS_DESCRIPTION)
//                .build();
//
//        operativeSystemCommandNewRegisterMac = OperativeSystemCommand.builder()
//                .name(MAC_OS_NAME)
//                .description(MAC_OS_DESCRIPTION)
//                .build();
//
//        operativeSystemCommandWithoutName = OperativeSystemCommand.builder()
//                .description(WINDOWS_OS_DESCRIPTION)
//                .build();
//
//        OperativeSystem operativeSystemWindowsCreated = OperativeSystem.builder()
//                .id(new OperativeSystemId(WINDOWS_OS_UUID))
//                .name(WINDOWS_OS_NAME)
//                .description(WINDOWS_OS_DESCRIPTION)
//                .enabled(true)
//                .build();
//
//        when(operativeSystemRepository.findTopByName(WINDOWS_OS_NAME)).thenReturn(Optional.empty());
//        when(operativeSystemRepository.save(any(OperativeSystem.class))).thenReturn(operativeSystemWindowsCreated);
//
//    }
//
//    @Test
//    public void createNewOperativeSystemWindows() {
//        OperativeSystemResponse operativeSystemResponse = operativeSystemApplicationService.save(operativeSystemCommandNewRegisterWindows);
//        Assertions.assertNotNull(operativeSystemResponse.getId(), ()-> "OperativeSystem id is null");
//        Assertions.assertEquals(OS_CREATE_SUCCESSFULL, operativeSystemResponse.getMessage(), ()-> "OperativeSystem message is not correct");
//    }
//
//
//    @Test
//    public void createDuplicateOperativeSystemWindows() {
//        OperativeSystem operativeSystemWindowsExistent = new OperativeSystem(new OperativeSystemId(WINDOWS_OS_UUID), WINDOWS_OS_NAME, WINDOWS_OS_DESCRIPTION);
//        when(operativeSystemRepository.findTopByName(stripAccents(defaultFormat(WINDOWS_OS_NAME)))).thenReturn(Optional.of(operativeSystemWindowsExistent));
//        InventoryDomainException uniqueException = Assertions.assertThrows(InventoryDomainException.class, ()-> operativeSystemApplicationService.save(operativeSystemCommandNewRegisterWindows));
//        Assertions.assertEquals(EXCEPTION_OS_ALREADY_EXISTS, uniqueException.getMessage(), ()-> "OperativeSystem message is not correct");
//    }
//
//    @Test
//    public void validateRequiredFields() {
//        InventoryDomainException requiredFieldsException = Assertions.assertThrows(InventoryDomainException.class, ()-> operativeSystemApplicationService.save(operativeSystemCommandWithoutName));
//        Assertions.assertEquals(EXCEPTION_OS_WITHOUT_NAME, requiredFieldsException.getMessage(), ()-> "OperativeSystem message is not correct");
//    }
//
//}
