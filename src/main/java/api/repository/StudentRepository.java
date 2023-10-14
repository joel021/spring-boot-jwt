package api.repository;

import api.model.Student;
import api.report.StudentSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {


    @Query(value = "select u.name as professor_name, d.code as discipline_code, d.name as discipline, AVG(e.student_score) as mean_units\n" +
            "\n" +
            "    from student s, student_evaluation e, classroom_evaluation_student_evaluations c_e_s_e, classroom_evaluation ce, classroom_classroom_evaluations cce, classroom c, discipline d, app_user u\n" +
            "        where\n" +
            "            e.valid = true\n" +
            "            and e.student_register = ?1 \n" +
            "            and c_e_s_e.student_evaluations_id = e.id\n" +
            "            and ce.id = c_e_s_e.classroom_evaluation_id\n" +
            "            and cce.classroom_evaluations_id = ce.id\n" +
            "            and c.id = cce.classroom_id\n" +
            "            and d.code = c.discipline_code\n" +
            "            and u.username = c.professor_username\n" +
            "\n" +
            "    group by c.professor_username, c.discipline_code, d.name", nativeQuery = true)
    List<Object[]> summaryStudentById(String username);
}
