package api.controller;

import api.exception.NotAcceptedException;
import api.model.AppUser;
import api.model.Classroom;
import api.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/classroom")
public class ClassroomController {

    @Autowired
    private ClassroomService classroomService;

    @PreAuthorize("hasRole('ROLE_PROFESSOR')")
    @PostMapping("/")
    public ResponseEntity<Classroom> create(@RequestBody @Valid Classroom classroom) throws NotAcceptedException {

        AppUser owner = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        classroom.setProfessor(owner);
        return ResponseEntity.status(HttpStatus.CREATED).body(classroomService.create(classroom));
    }

    @PreAuthorize("hasRole('ROLE_PROFESSOR')")
    @GetMapping("/")
    public ResponseEntity<List<Classroom>> findAllOfProfessor() {

        AppUser professor = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok().body(classroomService.findClassroomsByProfessor(professor));
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping("/professor/{username}")
    public ResponseEntity<List<Classroom>> findByProfessor(@PathVariable String username) {

        AppUser professor = new AppUser();
        professor.setUsername(username);
        return ResponseEntity.status(HttpStatus.OK).body(classroomService.findClassroomsByProfessor(professor));
    }

}
