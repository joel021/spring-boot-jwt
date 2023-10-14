package api.report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentSummary implements StudentSummaryInterface {

    private String professorName;
    private String disciplineCode;
    private String discipline;
    private float meanUnits;
}
