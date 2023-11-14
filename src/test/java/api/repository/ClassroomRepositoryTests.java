package api.repository;

import api.repository.ClassroomRepository;
import auth.model.AppUser;
import auth.model.AppUserRole;
import api.model.Classroom;
import api.model.Discipline;
import auth.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class ClassroomRepositoryTests {

    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    private UserRepository userRepository;

    private Classroom classroomExistent;

    private AppUser professor;


    @BeforeEach
    public void setup() {

        professor = new AppUser("profesor-1", "Professor", "professor@professor.com",
                "i_am_a_professor_hahaha", true, AppUserRole.ROLE_PROFESSOR);
        userRepository.save(professor);
        classroomExistent = classroomRepository.saveAndFlush(new Classroom(null, professor, new Discipline("GCET-3421",
                "Systematic Testing2"), null));
    }

    @Test
    public void saveTest() {

        Classroom classroom = new Classroom(null, professor, new Discipline("GCET-3121",
                "Systematic Testing"), null);
        Classroom actualClassroom = classroomRepository.save(classroom);
        assertNotNull(actualClassroom.getId(), "Assert create new classroom.");
    }

    @Test
    public void findByProfessorTest() {

        List<Classroom> classroomList = classroomRepository.findByProfessor(professor);
        assertFalse(classroomList.isEmpty(), "Assert return the classrooms of the provided professor.");
    }

    @Test
    public void findByProfessorWithOnlyIdTest() {

        AppUser professorWithOnlyUserName = new AppUser();
        professorWithOnlyUserName.setUsername(professor.getUsername());
        List<Classroom> classroomList = classroomRepository.findByProfessor(professorWithOnlyUserName);
        assertFalse(classroomList.isEmpty(), "Assert return the classrooms of the provided professor.");
    }

    @Test
    public void findByProfessorNotExistentTest()  {

        AppUser professorNotExistent = new AppUser();
        professorNotExistent.setUsername("this_professor-does-not-exists");
        List<Classroom> classroomList = classroomRepository.findByProfessor(professorNotExistent);
        assertTrue(classroomList.isEmpty(), "Assert search by non existent does not raise exception.");
    }

}
