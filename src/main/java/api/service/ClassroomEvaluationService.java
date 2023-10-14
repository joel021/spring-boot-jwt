package api.service;


import api.repository.ClassroomEvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassroomEvaluationService {

    @Autowired
    private ClassroomEvaluationRepository classroomEvaluationRepository;



}
