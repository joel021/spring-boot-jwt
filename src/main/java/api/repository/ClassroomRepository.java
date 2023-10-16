package api.repository;

import api.model.AppUser;
import api.model.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ClassroomRepository extends JpaRepository<Classroom, UUID> {

    List<Classroom> findByProfessor(AppUser professor);
}
