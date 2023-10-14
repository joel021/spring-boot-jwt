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
    private List<StudentSummary> summaryList;

    public static List<StudentSummary> summaryFromListOfObjects(List<Object[]> listOfObects) {

        if (listOfObects == null) {
            return null;
        }

        List<StudentSummary> studentSummaries = new ArrayList<>();
        for(Object[] summary: listOfObects) {

            float meanUnits = summary[3] != null ? (float) summary[3] : 0f;
            studentSummaries.add(new StudentSummary((String) summary[0], (String) summary[1], (String) summary[2], meanUnits));
        }
        return studentSummaries;
    }


}
