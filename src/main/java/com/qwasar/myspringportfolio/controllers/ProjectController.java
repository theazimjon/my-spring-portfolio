package com.qwasar.myspringportfolio.controllers;

import com.qwasar.myspringportfolio.models.Account;
import com.qwasar.myspringportfolio.models.Project;
import com.qwasar.myspringportfolio.services.AccountService;
import com.qwasar.myspringportfolio.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }


    @GetMapping("/projects")
    public String project(Model model) {
        List<Project> projects = projectService.getAll();
        model.addAttribute("projects", projects);
        return "projects";
    }

    @GetMapping("/projects/{id}")
    public String getPost(@PathVariable Long id, Model model) {

        Optional<Project> optionalProject = this.projectService.getById(id);

        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            model.addAttribute("project", project);
            return "project";
        } else {
            return "404";
        }
    }

    @PostMapping("/projects/{id}")
    @PreAuthorize("isAuthenticated()")
    public String updatePost(@PathVariable Long id, Project project, BindingResult result, Model model) {

        Optional<Project> optionalProject = projectService.getById(id);
        if (optionalProject.isPresent()) {
            Project existingProject = optionalProject.get();

            existingProject.setName(project.getName());
            existingProject.setDescription(project.getDescription());

            projectService.save(existingProject);
        }

        return "redirect:/projects/" + project.getId();
    }

    @GetMapping("/projects/new")
    @PreAuthorize("isAuthenticated()")
    public String createNewPost(Model model, Principal principal) {

        String authUsername = "anonymousUser";
        if (principal != null) {
            authUsername = principal.getName();
        }

        Optional<Account> optionalAccount = accountService.findOneByEmail(authUsername);
        if (optionalAccount.isPresent()) {
            Project project = new Project();
            project.setAccount(optionalAccount.get());
            model.addAttribute("project", project);
            return "new_project";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/projects/new")
    @PreAuthorize("isAuthenticated()")
    public String createNewPost(@ModelAttribute Project project, Principal principal) {
        String authUsername = "anonymousUser";
        if (principal != null) {
            authUsername = principal.getName();
        }
        if (project.getAccount().getEmail().compareToIgnoreCase(authUsername) < 0) {
            return "404";
        }
        projectService.save(project);
        return "redirect:/projects/" + project.getId();
    }

    @GetMapping("/projects/{id}/edit")
    @PreAuthorize("isAuthenticated()")
    public String getPostForEdit(@PathVariable Long id, Model model) {
        Optional<Project> optionalPost = projectService.getById(id);
        if (optionalPost.isPresent()) {
            Project projects = optionalPost.get();
            model.addAttribute("project", projects);
            return "edit_project";
        } else {
            return "404";
        }
    }

    @GetMapping("/projects/{id}/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deletePost(@PathVariable Long id) {

        Optional<Project> optionalPost = projectService.getById(id);
        if (optionalPost.isPresent()) {
            Project project = optionalPost.get();
            projectService.delete(project);
            return "redirect:/projects";
        } else {
            return "401";
        }
    }

}
