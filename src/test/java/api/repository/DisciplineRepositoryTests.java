package api.repository;

import api.model.Discipline;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class DisciplineRepositoryTests {

    @Autowired
    private DisciplineRepository disciplineRepository;

    @Test
    public void saveTest() {

        Discipline disciplineToBeSaved = new Discipline("GCET-0343", "iisj");
        Discipline disciplineActual = disciplineRepository.save(disciplineToBeSaved);

        assertEquals(disciplineToBeSaved.getCode(), disciplineActual.getCode(), "Assert it is able to save.");
    }
}
