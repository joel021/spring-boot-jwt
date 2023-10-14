package api.controller;

import api.model.Discipline;
import api.service.DisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/discipline")
public class DisciplineController {


    @Autowired
    private DisciplineService disciplineService;

    @PostMapping("/")
    @PreAuthorize("hasRole('ROLE_PROFESSOR')")
    public ResponseEntity<Discipline> create(@RequestBody @Valid Discipline discipline) {

        return ResponseEntity.status(HttpStatus.CREATED).body(disciplineService.create(discipline));
    }
}
