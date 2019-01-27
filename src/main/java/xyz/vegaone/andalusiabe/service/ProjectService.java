package xyz.vegaone.andalusiabe.service;

import org.dozer.Mapper;
import org.springframework.stereotype.Service;
import xyz.vegaone.andalusiabe.domain.ProjectEntity;
import xyz.vegaone.andalusiabe.domain.UserEntity;
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

        return mapper.map(projectEntity, Project.class);
    }

    public List<Project> getAllProjects() {
        List<ProjectEntity> projectEntityList = projectRepo.findAll();
        return projectEntityList
                .stream()
                .map(projectEntity -> mapper.map(projectEntity, Project.class))
                .collect(Collectors.toList());
    }

    public List<Project> getAllProjectsByOrganizationId(Long id) {
        List<ProjectEntity> projectEntityList = projectRepo.findAllByOrganizationId(id);
        return projectEntityList
                .stream()
                .map(projectEntity -> mapper.map(projectEntity, Project.class))
                .collect(Collectors.toList());
    }

    public List<Project> getAllProjectsByUserId(Long id) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(id);

        List<ProjectEntity> projectEntityList = projectRepo.findAllByUsersIsContaining(userEntity);
        return projectEntityList
                .stream()
                .map(projectEntity -> mapper.map(projectEntity, Project.class))
                .collect(Collectors.toList());
    }


    public Project createProject(Project project) {
        ProjectEntity projectEntity =
                projectRepo.save(mapper.map(project, ProjectEntity.class));
        return mapper.map(projectEntity, Project.class);
    }

    public Project updateProject(Project project) {
        ProjectEntity projectEntity =
                projectRepo.save(mapper.map(project, ProjectEntity.class));
        return mapper.map(projectEntity, Project.class);
    }

    public void deleteProject(Long id) {
        projectRepo.deleteById(id);
    }
}
