package api.report;

import api.model.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentReport {

    private Student student;
    List<StudentSummary> summaryList;

    public static List<StudentSummary> summaryFromListOfObjects(List<Object[]> listOfObects) {

        List<StudentSummary> studentSummaries = new ArrayList<>();
        for(Object[] summary: listOfObects) {
            studentSummaries.add(new StudentSummary((String) summary[0], (String) summary[1], (String) summary[2], (float) summary[3]));
        }
        return studentSummaries;
    }
}
