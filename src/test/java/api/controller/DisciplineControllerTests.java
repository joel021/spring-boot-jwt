package api.controller;


import exception.ResourceAlreadyExists;
import api.model.Discipline;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DisciplineControllerTests extends ControllerTests {


    @BeforeEach
    public void setup() throws ResourceAlreadyExists {
        super.setup();
    }

    @Test
    public void createTest() throws Exception {

        String disciplineJsonString  = new ObjectMapper().writeValueAsString(new Discipline("GCET-0000", "Discipline"));
        mockMvc.perform(
                        post("/discipline/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(disciplineJsonString)
                                .header("authorization", "Bearer " + adminToken))
                .andExpect(status().isCreated());
    }
}
