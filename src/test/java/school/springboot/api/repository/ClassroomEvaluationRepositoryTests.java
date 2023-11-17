package school.springboot.api.repository;

import school.springboot.api.model.ClassroomEvaluation;
import school.springboot.api.repository.ClassroomEvaluationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class ClassroomEvaluationRepositoryTests {

    @Autowired
    private ClassroomEvaluationRepository classroomEvaluationRepository;

    @Test
    public void saveTest() {

        ClassroomEvaluation classroomEvaluation = new ClassroomEvaluation();
        ClassroomEvaluation classroomEvaluationSaved = classroomEvaluationRepository.save(classroomEvaluation);

        assertNotNull(classroomEvaluationSaved.getId(), "Assert transaction can be completed.");
    }
}
