package school.springboot.api.controller;

import school.springboot.exception.ResourceAlreadyExists;
import school.springboot.api.model.CourseLevel;
import school.springboot.api.model.Student;
import school.springboot.api.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class StudentControllerTests extends ControllerTests {


    @MockBean
    private StudentRepository studentRepository;

    private Student student;

    @BeforeEach
    public void setup() throws ResourceAlreadyExists {
        super.setup();

        student = new Student("088.999.999-00", "Lara", CourseLevel.FIRST_YEAR);
        List<Object[]> studentSummary = new ArrayList<>();
        for(int i = 0; i < 15; i++) {
            studentSummary.add(new Object[]{"Professor Name", "DisciplineCode-"+i, "Discipline Name "+i, i+0.3f});
        }

        when(studentRepository.summaryStudentById(student.getRegister())).thenReturn(studentSummary);
        when(studentRepository.findById(student.getRegister())).thenReturn(Optional.of(student));
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
                        get("/student/report/"+student.getRegister())
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("authorization", "Bearer " + userToken))
                .andExpect(status().isForbidden());
    }

}