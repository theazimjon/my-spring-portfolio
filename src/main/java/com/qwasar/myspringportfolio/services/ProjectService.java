package com.qwasar.myspringportfolio.services;

import com.qwasar.myspringportfolio.models.Project;
import com.qwasar.myspringportfolio.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Optional<Project> getById(Long id) {
        return projectRepository.findById(id);
    }

    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    public Project save(Project project) {
        return projectRepository.save(project);
    }

    public void delete(Project project) {
        projectRepository.delete(project);
    }

}
