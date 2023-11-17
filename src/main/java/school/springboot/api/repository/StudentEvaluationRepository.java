package school.springboot.api.repository;

import school.springboot.api.model.Student;
import school.springboot.api.model.StudentEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface StudentEvaluationRepository extends JpaRepository<StudentEvaluation, UUID> {

    public List<StudentEvaluation> findByStudent(Student student);

}
