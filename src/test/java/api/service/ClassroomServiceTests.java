package api.service;

import api.service.ClassroomService;
import exception.NotAcceptedException;
import auth.model.AppUser;
import auth.model.AppUserRole;
import api.model.Classroom;
import api.model.Discipline;
import api.repository.ClassroomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;
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

    private AppUser professor;

    @BeforeEach
    public void setup() {

        professor = new AppUser("profesor-1", "Professor", "professor@professor.com", "i_am_a_professor_hahaha", true, AppUserRole.ROLE_PROFESSOR);
        classroomToCreate = new Classroom(null, professor, new Discipline("GCET234", "Mathematics"), null);
        classroomExpected = new Classroom(UUID.randomUUID(), professor, new Discipline("GCET234", "Mathematics"), null);
        when(classroomRepository.save(classroomToCreate)).thenReturn(classroomExpected);
        when(classroomRepository.findByProfessor(professor)).thenReturn(Collections.singletonList(classroomExpected));
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

    @Test
    public void findByProfessorTest() {

        List<Classroom> classrooms = classroomRepository.findByProfessor(professor);
        assertFalse(classrooms.isEmpty(), "Assert the list of classrooms is not empty.");
    }

}
