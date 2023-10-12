package api.model;

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
        assertTrue(classroom.getEvaluations().isEmpty(), "Assert the list of evaluations are instanced and empty.");
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
        fields.put("name", "name");
        Classroom classroom = Classroom.getInstanceFrom(fields);
        assertSame(classroom.getName(), fields.get("name"));
    }

    @Test
    public void getInstanceFromNameAndListTest() {

        List<Evaluation> evaluationList = new ArrayList<>();
        evaluationList.add(new Evaluation());

        Map<String, Object> fields = new HashMap<>();
        fields.put("name", "name");
        fields.put("evaluations", evaluationList);

        Classroom classroom = Classroom.getInstanceFrom(fields);
        assertNotNull(classroom.getEvaluations());
    }


}
