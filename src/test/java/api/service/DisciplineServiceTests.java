package api.service;

import api.model.Discipline;
import api.repository.DisciplineRepository;
import api.service.DisciplineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DisciplineServiceTests {

    @MockBean
    private DisciplineRepository disciplineRepository;

    @Autowired
    private DisciplineService disciplineService;

    private Discipline disciplineToBeSaved;

    private Discipline disciplineSaved;

    @BeforeEach
    public void setup() {
        disciplineToBeSaved = new Discipline("GCET-3421", "mathematics");
        disciplineSaved = new Discipline("GCET-3421", "mathematics");
        when(disciplineRepository.save(disciplineToBeSaved)).thenReturn(disciplineSaved);
    }

    @Test
    public void createTest() {

        Discipline discipline = disciplineService.create(disciplineToBeSaved);
        assertNotNull(discipline.getName(), "Assert discipline is saved.");
    }
}
