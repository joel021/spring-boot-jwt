package api.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private AppUser owner;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Discipline discipline;

    @NotNull
    @NotBlank
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Evaluation> evaluations = new ArrayList<>();

    public static Classroom getInstanceFrom(Map<String, Object> fields) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue (fields, Classroom.class);
    }

}
