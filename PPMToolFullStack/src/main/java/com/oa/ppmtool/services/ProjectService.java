package com.oa.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oa.ppmtool.domain.Project;
import com.oa.ppmtool.exceptions.ProjectIdentifierException;
import com.oa.ppmtool.repositories.ProjectRepository;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project) {
        // TODO Logic to be added her - validation and so on
        try {
            // Ensure uppercase value for identifier when stored
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        } catch (Exception ex) {
            /**
             * TODO - Antar at det her bør sjekkes nærmere hva slags feil som faktisk skjer?
             * Uansett hva grunnen er vil samme feilmelding bli vist slik det er nå!!
             * Ser i loggen følgende som kanskje kan brukes?
             *      : SQL Error: 23505, SQLState: 23505
             *      : Unique index or primary key violation: ..............
             */
            throw new ProjectIdentifierException("Projectidentifier '" + project.getProjectIdentifier() +"' already exists");
        }

    }

    public Project finyProjectByIdentifier(String projectIdentifier) {
        Project project = projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());

        // Sjekke om prosjektet ble funnet - Det kastes ikke exception - må ta hånd om det selv
        if (project == null) {
            throw new ProjectIdentifierException("No project with Projectidentifier like '" + projectIdentifier +"' was found");
        }

        return project;
    }

    public Iterable<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String projectIdentifier) {
        Project project = projectRepository.findByProjectIdentifier(projectIdentifier);
        if (project == null) {
            throw new ProjectIdentifierException("No project with Projectidentifier like '" + projectIdentifier
                                                         +"' was found. No project was deleted");
        }

        projectRepository.delete(project);
    }
}
