package api.model;

import api.model.Classroom;
import api.model.ClassroomEvaluation;
import api.model.Discipline;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ClassroomTests {


    @Test
    public void instanceOfEvaluations() {

        Classroom classroom = new Classroom();
        assertTrue(classroom.getClassroomEvaluations().isEmpty(), "Assert the list of evaluations are instanced and empty.");
    }

    @Test
    public void getInstanceFromNullTest() {

        Map<String, Object> fields = null;
        Classroom classroom = Classroom.getInstanceFrom(fields);
        assertNull(classroom);
    }

    @Test
    public void getInstanceFromEmptyTest() {

        Map<String, Object> fields = new HashMap<>();
        Classroom classroom = Classroom.getInstanceFrom(fields);
        assertNotNull(classroom);
    }

    @Test
    public void getInstanceFromOnlyNameTest() {

        Map<String, Object> fields = new HashMap<>();
        fields.put("discipline", new Discipline("GCET-123", "Algorithms"));
        Classroom classroom = Classroom.getInstanceFrom(fields);
        assertSame(classroom.getDiscipline().getCode(), "GCET-123");
    }

    @Test
    public void getInstanceFromDisciplineAndListTest() {

        List<ClassroomEvaluation> classroomEvaluationList = new ArrayList<>();
        classroomEvaluationList.add(new ClassroomEvaluation());

        Map<String, Object> fields = new HashMap<>();
        fields.put("discipline", new Discipline("GCEPT-1234", "AI"));
        fields.put("classroomEvaluations", classroomEvaluationList);

        Classroom classroom = Classroom.getInstanceFrom(fields);
        assertNotNull(classroom.getClassroomEvaluations());
    }


}
