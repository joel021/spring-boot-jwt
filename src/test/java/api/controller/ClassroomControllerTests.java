package api.controller;


import api.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ClassroomControllerTests extends ControllerTests {

    @BeforeEach
    public void setup() {
        super.setup();
    }

    @Test
    public void createTest() throws Exception {

        Discipline discipline = new Discipline();
        List<Evaluation> evaluations = new ArrayList<>();

        Classroom classroom = new Classroom(null, null, discipline, "classroom", evaluations);

        String classroomBody = new ObjectMapper().writeValueAsString(classroom);
        mockMvc.perform(
                post("/classroom/")
                        .content(classroomBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("authorization", "Bearer " + adminToken))
                .andExpect(status().isCreated());

    }

    @Test
    public void createMissingFieldsTest() throws Exception {

        List<Evaluation> evaluations = new ArrayList<>();
        Classroom classroom = new Classroom(null, null, null, null, evaluations);
        String classroomBody = new ObjectMapper().writeValueAsString(classroom);

        mockMvc.perform(
                        post("/classroom/")
                                .content(classroomBody)
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("authorization", "Bearer " + adminToken))
                .andExpect(status().isBadRequest());

    }

}
