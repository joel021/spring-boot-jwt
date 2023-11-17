package school.springboot.api.service;

import school.springboot.api.repository.ClassroomEvaluationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class ClassroomEvaluationServiceTests {

    @MockBean
    private ClassroomEvaluationRepository classroomEvaluationRepository;

    @BeforeEach
    public void setup() {


    }
}
