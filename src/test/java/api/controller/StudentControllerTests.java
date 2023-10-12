package api.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class StudentControllerTests extends ControllerTests {


    @BeforeEach
    public void setup() {
        super.setup();
    }


    @Test
    public void findStudentByRegisterNotExistsTest() throws Exception {

        mockMvc.perform(
                        get("/student/000293")
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("authorization", "Bearer " + adminToken))
                .andExpect(status().isNotFound());
    }

    @Test
    public void findStudentByRegisterNotAuthorized() throws Exception {

        mockMvc.perform(
                        get("/student/000293")
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("authorization", "Bearer " + userToken))
                .andExpect(status().isForbidden());
    }

}