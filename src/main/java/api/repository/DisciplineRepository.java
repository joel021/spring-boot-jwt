package api.repository;

import api.model.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplineRepository extends JpaRepository<Discipline, String> {
}
