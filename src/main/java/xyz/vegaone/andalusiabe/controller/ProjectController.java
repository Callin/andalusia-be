package xyz.vegaone.andalusiabe.controller;

import org.springframework.web.bind.annotation.*;
import xyz.vegaone.andalusiabe.dto.Project;
import xyz.vegaone.andalusiabe.service.ProjectService;

import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
    private ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("{id}")
    public Project getProject(@PathVariable Long id) {
        return projectService.getProject(id);
    }

    @GetMapping("{id}/brief")
    public Project getProjectBrief(@PathVariable Long id) {
        return projectService.getProjectBrief(id);
    }

    @GetMapping("/all")
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/organization/{id}")
    public List<Project> getAllProjectsByOrganizationId(@PathVariable Long id) {
        return projectService.getAllProjectsByOrganizationId(id);
    }

    @GetMapping("/user/{id}")
    public List<Project> getAllProjectsByUserId(@PathVariable Long id) {
        return projectService.getAllProjectsByUserId(id);
    }

    @PostMapping
    public Project createProject(@RequestBody Project project) {
        return projectService.createProject(project);
    }

    @PutMapping
    public Project updateProject(@RequestBody Project project) {
        return projectService.updateProject(project);
    }

    @DeleteMapping("{id}")
    public void deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
    }
}
