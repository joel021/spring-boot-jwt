package api.service;


import api.exception.ControllerException;
import api.exception.ResourceNotFoundException;
import api.model.CourseLevel;
import api.model.Student;
import api.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class StudentServiceTests {


    @MockBean
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    private Student studentFirstYear;


    @BeforeEach
    public void setup() {
        studentFirstYear = new Student("register", "Student First Year", CourseLevel.FIRST_YEAR, 8.1f);
        when(studentRepository.save(studentFirstYear)).thenReturn(studentFirstYear);
        when(studentRepository.findByRegister(studentFirstYear.getRegister())).thenReturn(Optional.of(studentFirstYear));
    }

    @Test
    public void registerTest() {
        Student createdStudent = studentService.register(studentFirstYear);
        assertEquals(studentFirstYear.getRegister(), createdStudent.getRegister(), "Assert whether user is created.");
    }

    @Test
    public void findStudentByRegisterExistsTest() throws ResourceNotFoundException {

        Student userFound = studentService.findStudentByRegister(studentFirstYear.getRegister());
        assertEquals(userFound.getRegister(), studentFirstYear.getRegister(), "Test whether user is returned");
    }

    @Test
    public void findStudentByRegisterNotExistsTest() {

        ControllerException exception = assertThrows(ResourceNotFoundException.class, () -> {
            studentService.findStudentByRegister("notExistentRegister");
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus(), "Test whether the correct exception is raised.");
    }

}
