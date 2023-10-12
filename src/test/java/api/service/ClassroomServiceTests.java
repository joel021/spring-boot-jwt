package api.service;

import api.exception.ControllerException;
import api.exception.NotAcceptedException;
import api.exception.ResourceNotFoundException;
import api.model.AppUser;
import api.model.Classroom;
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

        classroomToCreate = new Classroom(null, new AppUser(), null, "mathematics", null);
        classroomExpected = new Classroom(UUID.randomUUID(), null, null, "mathematics", null);
        when(classroomRepository.save(classroomToCreate)).thenReturn(classroomExpected);
    }

    @Test
    public void createTest() throws NotAcceptedException {

        Classroom classroomCreated = classroomService.create(classroomToCreate);
        assertEquals(classroomExpected.getId(), classroomCreated.getId(), "Assert the entity is created and returned.");
    }

    @Test
    public void createMissingOwnerTest() {

        classroomToCreate.setOwner(null);
        NotAcceptedException exception = assertThrows(NotAcceptedException.class, () -> {
            classroomService.create(classroomToCreate);
        });
        assertSame("The owner is required", exception.getMessage(), "Assert the entity is created and returned.");
    }

}
