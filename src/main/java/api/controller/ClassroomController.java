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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/classroom")
public class ClassroomController {

    @Autowired
    private ClassroomService classroomService;

    @PreAuthorize("hasRole('ROLE_PROFESSOR')")
    @PostMapping("/")
    public ResponseEntity<Classroom> create(@RequestBody @Valid Classroom classroom) throws NotAcceptedException {

        AppUser owner = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        classroom.setOwner(owner);
        return ResponseEntity.status(HttpStatus.CREATED).body(classroomService.create(classroom));
    }
}
