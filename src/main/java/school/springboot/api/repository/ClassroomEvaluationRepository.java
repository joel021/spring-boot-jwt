package school.springboot.api.repository;

import school.springboot.api.model.ClassroomEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomEvaluationRepository extends JpaRepository<ClassroomEvaluation, Integer> {

}
