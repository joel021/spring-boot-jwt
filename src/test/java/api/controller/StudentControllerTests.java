package api.controller;

import api.exception.ResourceAlreadyExists;
import api.model.Student;
import api.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class StudentControllerTests extends ControllerTests {


    @Autowired
    private StudentRepository studentRepository;

    private Student student;

    @BeforeEach
    public void setup() throws ResourceAlreadyExists {
        super.setup();

        student = new Student();
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

    @Test
    public void getStudentReportTest() throws Exception {

        mockMvc.perform(
                        get("/student/000293")
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("authorization", "Bearer " + userToken))
                .andExpect(status().isForbidden());
    }

}