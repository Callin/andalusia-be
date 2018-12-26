package xyz.vegaone.andalusiabe.service;

import org.dozer.Mapper;
import org.springframework.stereotype.Service;
import xyz.vegaone.andalusiabe.domain.ProjectEntity;
import xyz.vegaone.andalusiabe.dto.Project;
import xyz.vegaone.andalusiabe.repo.ProjectRepo;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private ProjectRepo projectRepo;

    private Mapper mapper;

    public ProjectService(ProjectRepo projectRepo, Mapper mapper) {
        this.projectRepo = projectRepo;
        this.mapper = mapper;
    }

    public Project getProject(Long id) {
        ProjectEntity projectEntity = projectRepo.findById(id).orElseThrow(EntityNotFoundException::new);

        return mapProjectAndRemoveProjectFromUser(projectEntity);
    }

    public List<Project> getAllProjects() {
        List<ProjectEntity> projectEntityList = projectRepo.findAll();
        return projectEntityList
                .stream()
                .map(this::mapProjectAndRemoveProjectFromUser)
                .collect(Collectors.toList());
    }

    public List<Project> getAllProjectsByOrganizationId(Long id) {
        List<ProjectEntity> projectEntityList = projectRepo.findAllByOrganizationId(id);
        return projectEntityList
                .stream()
                .map(this::mapProjectAndRemoveProjectFromUser)
                .collect(Collectors.toList());
    }


    public Project createProject(Project project) {
        ProjectEntity projectEntity =
                projectRepo.save(mapper.map(project, ProjectEntity.class));
        return mapProjectAndRemoveProjectFromUser(projectEntity);
    }

    public Project updateProject(Project project) {
        ProjectEntity projectEntity =
                projectRepo.save(mapper.map(project, ProjectEntity.class));
        return mapProjectAndRemoveProjectFromUser(projectEntity);
    }

    public void deleteProject(Long id) {
        projectRepo.deleteById(id);
    }

    /**
     * Breaks circular reference of Project that has a list of Users that have an Project that has a list of
     * USers.
     *
     * @param projectEntity the project that will have it's circular reference fixed
     * @return the project
     */
    private Project mapProjectAndRemoveProjectFromUser(ProjectEntity projectEntity) {
        Project project = mapper.map(projectEntity, Project.class);
        if (project.getUsers() != null) {
            project.getUsers().forEach(user -> user.setProjects(null));
        }
        return project;
    }
}
