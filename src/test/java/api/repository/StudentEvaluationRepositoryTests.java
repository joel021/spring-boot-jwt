package api.repository;

import api.model.CourseLevel;
import api.model.Student;
import api.model.StudentEvaluation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@SpringBootTest
public class StudentEvaluationRepositoryTests {


    @Autowired
    private StudentEvaluationRepository studentEvaluationRepository;

    @Autowired
    private StudentRepository studentRepository;

    private Student student;

    @BeforeEach
    public void setup() {

        student = studentRepository.save(new Student("000.000.000-00", "Student", CourseLevel.FIRST_YEAR));
        for(int i = 0; i < 10; i++) {
            studentEvaluationRepository.save(new StudentEvaluation(null, student, 10f, true));
        }
    }

    @Test
    public void findByStudentTest() {

        List<StudentEvaluation> studentEvaluations = studentEvaluationRepository.findByStudent(student);
        assertTrue(studentEvaluations.size() > 0, "Assert find by student.");
    }

    @Test
    public void findByStudentWithOnlyIdTest() {

        Student studentWithOnlyId = new Student(student.getRegister(), null, null);
        List<StudentEvaluation> studentEvaluations = studentEvaluationRepository.findByStudent(studentWithOnlyId);
        assertTrue(studentEvaluations.size() > 0, "Assert find by student.");
    }
}
