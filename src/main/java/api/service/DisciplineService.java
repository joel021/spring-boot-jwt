package api.service;

import api.model.Discipline;
import api.repository.DisciplineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DisciplineService {

    @Autowired
    private DisciplineRepository disciplineRepository;

    public Discipline create(Discipline discipline) {

        return disciplineRepository.save(discipline);
    }
}
