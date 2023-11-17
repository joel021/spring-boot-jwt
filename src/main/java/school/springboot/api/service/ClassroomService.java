package school.springboot.api.service;

import school.springboot.exception.NotAcceptedException;
import school.springboot.auth.model.AppUser;
import school.springboot.api.model.Classroom;
import school.springboot.api.repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomService {

    @Autowired
    private ClassroomRepository classroomRepository;

    public Classroom create(Classroom classroom) throws NotAcceptedException {

        if (classroom.getProfessor() == null) {
            throw new NotAcceptedException("The owner is required", null);
        }

        return classroomRepository.save(classroom);
    }

    public List<Classroom> findClassroomsByProfessor(AppUser professor) {

        return classroomRepository.findByProfessor(professor);
    }

}
