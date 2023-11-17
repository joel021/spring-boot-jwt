package school.springboot.api.controller;


import school.springboot.api.model.Classroom;
import school.springboot.api.model.ClassroomEvaluation;
import school.springboot.api.model.Discipline;
import school.springboot.exception.ResourceAlreadyExists;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class ClassroomControllerTests extends ControllerTests {

    @BeforeEach
    public void setup() throws ResourceAlreadyExists {
        super.setup();
    }

    @Test
    public void createTest() throws Exception {

        Discipline discipline = new Discipline("GCET-9684", "mathematics");
        List<ClassroomEvaluation> classroomEvaluations = new ArrayList<>();

        Classroom classroom = new Classroom(null, null, discipline, classroomEvaluations);

        String classroomBody = new ObjectMapper().writeValueAsString(classroom);
        mockMvc.perform(
                post("/classroom/")
                        .content(classroomBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("authorization", "Bearer " + adminToken))
                .andExpect(status().isCreated());
    }

    @Test
    public void createButIsNotAllowedTest() throws Exception {

        Discipline discipline = new Discipline("GCET-9684", "mathematics");
        List<ClassroomEvaluation> classroomEvaluations = new ArrayList<>();

        Classroom classroom = new Classroom(null, null, discipline, classroomEvaluations);

        String classroomBody = new ObjectMapper().writeValueAsString(classroom);
        mockMvc.perform(
                        post("/classroom/")
                                .content(classroomBody)
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("authorization", "Bearer " + userToken))
                .andExpect(status().isForbidden());
    }

    @Test
    public void createMissingFieldsTest() throws Exception {

        List<ClassroomEvaluation> classroomEvaluations = new ArrayList<>();
        Classroom classroom = new Classroom(null, null, null, classroomEvaluations);
        String classroomBody = new ObjectMapper().writeValueAsString(classroom);

        mockMvc.perform(
                        post("/classroom/")
                                .content(classroomBody)
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("authorization", "Bearer " + adminToken))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void findAllOfProfessorTest() throws Exception {

        mockMvc.perform(
                get("/classroom/")
                        .header("authorization", "Bearer "+professorToken))
                .andExpect(status().isOk());
    }

    @Test
    public void findByProfessorButNotAllowedTest() throws Exception {

        mockMvc.perform(
                        get("/classroom/professor/"+professorUser.getUsername())
                                .header("authorization", "Bearer "+professorToken))
                .andExpect(status().isForbidden());
    }

    @Test
    public void findByProfessorTest() throws Exception {

        mockMvc.perform(
                        get("/classroom/professor/"+adminUser.getUsername())
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("authorization", "Bearer "+adminToken))
                .andExpect(status().isOk());
    }

}
