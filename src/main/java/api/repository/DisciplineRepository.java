package api.repository;

import api.model.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DisciplineRepository extends JpaRepository<Discipline, String> {
}
