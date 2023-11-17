package school.springboot.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ClassroomEvaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private short unit;
    private Date date;

    @OneToMany(cascade = CascadeType.ALL)
    private List<StudentEvaluation> studentEvaluations = new ArrayList<>();

}
