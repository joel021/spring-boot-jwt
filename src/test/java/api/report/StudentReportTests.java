package api.report;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StudentReportTests {

    private List<Object[]> listOfObjects;

    @BeforeEach
    public void setup() {

        listOfObjects = new ArrayList<>();
        for(int i = 0; i < 10; i++) {

            listOfObjects.add(new Object[]{"value1", null, "value3", i+0.4f });
            listOfObjects.add(new Object[]{null, "value2", "value3", null });
            listOfObjects.add(new Object[]{"value1", "value2", "value3", i+0.4f });
        }
    }

    @Test
    public void summaryFromListOfObjectsTest() {

        List<StudentSummary> studentSummaries = StudentReport.summaryFromListOfObjects(listOfObjects);
        assertFalse(studentSummaries.isEmpty(), "Assert it convert the objects.");
    }

    @Test
    public void summaryFromListOfObjectsNullTest() {

        List<StudentSummary> studentSummaries = StudentReport.summaryFromListOfObjects(null);
        assertTrue(studentSummaries == null, "Assert it does not raise exception.");
    }



}
