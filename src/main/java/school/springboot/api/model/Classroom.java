package school.springboot.api.model;

import school.springboot.auth.model.AppUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    private AppUser professor;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Discipline discipline;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<ClassroomEvaluation> classroomEvaluations = new ArrayList<>();

    public static Classroom getInstanceFrom(Map<String, Object> fields) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue (fields, Classroom.class);
    }

}
