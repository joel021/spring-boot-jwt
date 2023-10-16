package api.repository;

import api.model.ClassroomEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClassroomEvaluationRepository extends JpaRepository<ClassroomEvaluation, Integer> {

}
