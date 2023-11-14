package api.repository;

import api.model.*;
import api.repository.ClassroomRepository;
import api.repository.StudentRepository;
import auth.model.*;
import auth.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class StudentRepositoryTests {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    private UserRepository userRepository;

    private Student student;
    private AppUser professor;

    @BeforeEach
    public void setup() {

        student = studentRepository.save(new Student("000.000.000-45", "Student 1", CourseLevel.THIRD_YEAR));
        professor = userRepository.save(new AppUser("professor-007","007", "007@email.com", "08j38933",
                true, AppUserRole.ROLE_PROFESSOR));
        populateClassrooms();
    }

    @Test
    public void summaryStudentByIdTest() {

        List<Object[]> studentSummary = studentRepository.summaryStudentById(student.getRegister());
        assertFalse(studentSummary.isEmpty(), "Assert the query is correct.");
    }

    private void populateClassrooms() {

        for(int i = 0; i < 10; i++) {

            List<ClassroomEvaluation> classroomEvaluations = new ArrayList<>();
            for(short j = 0; j < 4; j++) {

                List<StudentEvaluation> studentEvaluations = Collections.singletonList(new StudentEvaluation(null,
                        student, 8.1f, true));
                ClassroomEvaluation classroomEvaluation = new ClassroomEvaluation(null, j, new Date(System.currentTimeMillis()), studentEvaluations);
                classroomEvaluations.add(classroomEvaluation);
            }
            Classroom classroom = new Classroom(null, professor, new Discipline("GCET-"+i, "Discipline "+i), classroomEvaluations);
            classroomRepository.save(classroom);
        }
    }
}
