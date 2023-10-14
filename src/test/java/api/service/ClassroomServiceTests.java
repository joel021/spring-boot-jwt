package api.service;

import api.exception.NotAcceptedException;
import api.model.AppUser;
import api.model.Classroom;
import api.model.Discipline;
import api.repository.ClassroomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ClassroomServiceTests {


    @MockBean
    private ClassroomRepository classroomRepository;

    @Autowired
    private ClassroomService classroomService;

    private Classroom classroomToCreate;
    private Classroom classroomExpected;


    @BeforeEach
    public void setup() {

        classroomToCreate = new Classroom(null, new AppUser(), new Discipline("GCET234", "Mathematics"), null);
        classroomExpected = new Classroom(UUID.randomUUID(), null, new Discipline("GCET234", "Mathematics"), null);
        when(classroomRepository.save(classroomToCreate)).thenReturn(classroomExpected);
    }

    @Test
    public void createTest() throws NotAcceptedException {

        Classroom classroomCreated = classroomService.create(classroomToCreate);
        assertEquals(classroomExpected.getId(), classroomCreated.getId(), "Assert the entity is created and returned.");
    }

    @Test
    public void createMissingOwnerTest() {

        classroomToCreate.setProfessor(null);
        NotAcceptedException exception = assertThrows(NotAcceptedException.class, () -> {
            classroomService.create(classroomToCreate);
        });
        assertSame("The owner is required", exception.getMessage(), "Assert the entity is created and returned.");
    }

}
