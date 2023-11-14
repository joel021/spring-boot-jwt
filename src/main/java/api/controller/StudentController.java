package api.controller;

import exception.ResourceNotFoundException;
import api.model.Student;
import api.report.StudentReport;
import api.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<Student> register(@Valid Student student) {

        return ResponseEntity.ok().body(studentService.register(student));
    }

    @GetMapping("/{register}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<Student> findStudentByRegister(@PathVariable String register) throws ResourceNotFoundException {

        return ResponseEntity.ok().body(studentService.findStudentByRegister(register));
    }

    @GetMapping("/report/{register}")
    @PreAuthorize("hasRole('ROLE_PROFESSOR')")
    public ResponseEntity<StudentReport> getStudentReport(@PathVariable String register) throws ResourceNotFoundException {

        return ResponseEntity.status(HttpStatus.OK).body(studentService.generateStudentReport(register));
    }

}
