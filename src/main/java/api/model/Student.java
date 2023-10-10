package api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Student extends AppUser {

    @Pattern(regexp = "[0-9]{3}[.][0-9]{3}[.][0-9]{3}-[0-9]{2}")
    @Column(unique=true)
    private String register;

    @NotBlank(message = "You must provide the student name.")
    private String name;

    @NotNull(message = "You must provide the student register.")
    private CourseLevel courseLevel;

    @Min(message = "The min user score is 0.", value = 0)
    @Max(message = "The max user score is 10.", value = 10)
    private float score;


}
