package api.service;

import api.exception.NotAcceptedException;
import api.model.Classroom;
import api.repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassroomService {

    @Autowired
    private ClassroomRepository classroomRepository;

    public Classroom create(Classroom classroom) throws NotAcceptedException {

        if (classroom.getOwner() == null) {
            throw new NotAcceptedException("The owner is required", null);
        }

        return classroomRepository.save(classroom);
    }

}
