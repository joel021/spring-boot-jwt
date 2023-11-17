package school.springboot.api.service;

import school.springboot.exception.ResourceNotFoundException;
import school.springboot.api.model.Student;
import school.springboot.report.StudentReport;
import school.springboot.api.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student register(Student student) {

        return studentRepository.save(student);
    }

    public Student findStudentByRegister(String register) throws ResourceNotFoundException {

        Optional<Student> optionalStudent = studentRepository.findById(register);

        if (optionalStudent.isPresent()) {
            return optionalStudent.get();
        }
        throw new ResourceNotFoundException("Student not found.");
    }

    public StudentReport generateStudentReport(String register) throws ResourceNotFoundException {

        Student student = findStudentByRegister(register);
        List<Object[]> studentSummariesObjects = studentRepository.summaryStudentById(student.getRegister());

        return new StudentReport(student, StudentReport.summaryFromListOfObjects(studentSummariesObjects));
    }
}
