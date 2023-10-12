package api.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private short unit;
    private Date date;

    @OneToMany
    private List<StudentEvaluation> studentEvaluations = new ArrayList<>();

}
