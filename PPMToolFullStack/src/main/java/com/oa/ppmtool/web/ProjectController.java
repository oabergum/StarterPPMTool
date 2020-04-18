package com.oa.ppmtool.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oa.ppmtool.domain.Project;
import com.oa.ppmtool.services.ProjectService;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @PostMapping("")
    public ResponseEntity<?> createProject(@Valid @RequestBody Project project, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }

        Project savedProject = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<>(savedProject, HttpStatus.CREATED);
    }
}
