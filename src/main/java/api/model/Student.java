package api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Pattern(regexp = "[0-9]{3}[.][0-9]{3}[.][0-9]{3}-[0-9]{2}")
    @Id
    private String register;

    @NotBlank(message = "You must provide the student name.")
    private String name;

    @NotNull(message = "You must provide the student register.")
    private CourseLevel courseLevel;

    public Student(String register){
        this.register = register;
    }
}
