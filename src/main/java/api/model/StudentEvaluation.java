package api.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Data
@Entity
public class StudentEvaluation {

    @Id
    private Calendar dateTime = new GregorianCalendar();

    @ManyToOne
    private Student student;
    private float studentScore;
    private boolean valid;
}
