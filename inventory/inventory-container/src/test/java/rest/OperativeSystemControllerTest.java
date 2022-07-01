package rest;


import com.google.gson.Gson;
import com.ninjaone.inventory.domain.dto.command.operative.system.OperativeSystemCommand;
import com.ninjaone.inventory.domain.dto.command.operative.system.OperativeSystemResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;

class OperativeSystemControllerTest extends RequestTestCase {

    @Test
    void save() throws  Exception {

        int randomIndicator = (int) (1 + (Math.random() * 99));

       OperativeSystemCommand newOS =  OperativeSystemCommand.builder()
                .name("NINJA_OS"+randomIndicator)
                .description("NINJA OPERATIVE SYSTEM"+randomIndicator)
                .build();

        String jsonOS = (new Gson()).toJson(newOS);

        MvcResult result = assertRequestWithBody(HttpMethod.POST.name(),
                "/operativeSystems",
                jsonOS,
                HttpStatus.OK.value()
                );

        String jsonResponse = result.getResponse().getContentAsString();

        OperativeSystemResponse osResponse = (new Gson()).fromJson(jsonResponse, OperativeSystemResponse.class);

        Assertions.assertNotNull(osResponse.getId(), ()-> "The id of OS is null");
        Assertions.assertEquals(newOS.getName(), osResponse.getName(), () -> "The name to new OS it's not the expected");
        Assertions.assertEquals(newOS.getName(), osResponse.getName(), () -> "The name to new OS it's not the expected");
        Assertions.assertTrue(osResponse.isEnabled(),  () -> "The status to new OS it's not the expected");

    }


}