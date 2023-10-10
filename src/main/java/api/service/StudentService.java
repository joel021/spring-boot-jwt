package api.service;

import api.exception.ResourceNotFoundException;
import api.model.Student;
import api.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student register(Student student) {

        return studentRepository.save(student);
    }

    public Student findStudentByRegister(String register) throws ResourceNotFoundException {

        Optional<Student> optionalStudent = studentRepository.findByRegister(register);

        if (optionalStudent.isPresent()) {
            return optionalStudent.get();
        }
        throw new ResourceNotFoundException("Student not found.");
    }
}
