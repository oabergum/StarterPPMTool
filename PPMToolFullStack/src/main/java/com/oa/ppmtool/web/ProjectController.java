package com.oa.ppmtool.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oa.ppmtool.domain.Project;
import com.oa.ppmtool.services.MapValidationErrorService;
import com.oa.ppmtool.services.ProjectService;

@RestController
@RequestMapping("/api/project")
@CrossOrigin
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("")
    public ResponseEntity<?> createProject(@Valid @RequestBody Project project, BindingResult result) {

        ResponseEntity<?> errorMap = mapValidationErrorService.validateRequest(result);
        if (errorMap != null) {
            return errorMap;
        }

        Project savedProject = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<>(savedProject, HttpStatus.CREATED);
    }

    @GetMapping("/{projectIdentifier}")
    public ResponseEntity<?> findProjectByIdentifier(@PathVariable String projectIdentifier) {
        Project project = projectService.finyProjectByIdentifier(projectIdentifier);

        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Project> getAllProjects() {
        return projectService.findAllProjects();
    }

    @DeleteMapping("/{projectIdentifier}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectIdentifier) {
        projectService.deleteProjectByIdentifier(projectIdentifier.toUpperCase());

        return new ResponseEntity<String>("Project with id: '" + projectIdentifier + "' was deleted successfully",
                                          HttpStatus.OK);
    }
}
