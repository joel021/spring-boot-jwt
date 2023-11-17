package school.springboot.api.repository;

import school.springboot.auth.model.AppUser;
import school.springboot.api.model.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ClassroomRepository extends JpaRepository<Classroom, UUID> {

    List<Classroom> findByProfessor(AppUser professor);
}
