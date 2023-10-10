package api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Classroom {

    @Id
    private String disciplineCode;

    @ManyToOne
    private Professor professor;

    @ManyToOne
    private Discipline discipline;

    @ManyToMany
    private List<Student> students = new ArrayList<>();

    private String name;

    @OneToMany
    private List<Evaluation> evaluations = new ArrayList<>();
}
