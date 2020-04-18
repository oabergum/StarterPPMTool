package com.oa.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oa.ppmtool.domain.Project;
import com.oa.ppmtool.repositories.ProjectRepository;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project) {
        // TODO Logic to be added her - validation and so on

        return projectRepository.save(project);
    }
}
