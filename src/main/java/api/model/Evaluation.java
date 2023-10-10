package api.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.sql.Date;
import java.util.List;

@Data
@Entity
public class Evaluation {


    private String unitName;
    private Date date;

    @OneToMany
    private List<StudentEvaluation> studentEvaluations;

}
