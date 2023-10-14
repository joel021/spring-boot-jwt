package api.service;

import api.model.Student;
import api.model.StudentEvaluation;
import api.repository.StudentEvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentEvaluationService {

    @Autowired
    private StudentEvaluationRepository studentEvaluationRepository;

    public List<StudentEvaluation> findByStudentRegister(String studentRegister) {

        return studentEvaluationRepository.findByStudent(new Student(studentRegister));
    }

}
