package xyz.vegaone.andalusiabe.service;

import org.dozer.Mapper;
import org.springframework.stereotype.Service;
import xyz.vegaone.andalusiabe.domain.ProjectEntity;
import xyz.vegaone.andalusiabe.domain.SprintEntity;
import xyz.vegaone.andalusiabe.dto.Sprint;
import xyz.vegaone.andalusiabe.repo.SprintRepo;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SprintService {

    private SprintRepo sprintRepo;

    private Mapper mapper;

    public SprintService(SprintRepo sprintRepo, Mapper mapper) {
        this.sprintRepo = sprintRepo;
        this.mapper = mapper;
    }

    public List<Sprint> getAllByProjectIdBrief(Long projectId) {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(projectId);

        return sprintRepo.findAllByProjectBrief(projectEntity)
                .stream()
                .map(sprintEntity -> mapper.map(sprintEntity, Sprint.class))
                .collect(Collectors.toList());

    }

    public List<Sprint> getSprintsByProjectIdAndDateInterval(Long projectId, Date from, Date to) {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(projectId);

        return sprintRepo.findAllByProjectAndStartDateBeforeAndEndDateAfter(projectEntity, from, to)
                .stream()
                .map(sprintEntity -> mapper.map(sprintEntity, Sprint.class))
                .collect(Collectors.toList());
    }

    public Sprint getSprint(Long id) {
        SprintEntity sprintEntity = sprintRepo.findById(id).orElseThrow(EntityNotFoundException::new);

        return mapper.map(sprintEntity, Sprint.class);
    }

    public List<Sprint> getAllSprints() {
        List<SprintEntity> sprintEntityList = sprintRepo.findAll();
        return sprintEntityList
                .stream()
                .map(sprintEntity -> mapper.map(sprintEntity, Sprint.class))
                .collect(Collectors.toList());
    }


    public Sprint createSprint(Sprint sprint) {
        SprintEntity sprintEntity =
                sprintRepo.save(mapper.map(sprint, SprintEntity.class));
        return mapper.map(sprintEntity, Sprint.class);
    }

    public Sprint updateSprint(Sprint sprint) {
        SprintEntity sprintEntity =
                sprintRepo.save(mapper.map(sprint, SprintEntity.class));
        return mapper.map(sprintEntity, Sprint.class);
    }

    public void deleteSprint(Long id) {
        sprintRepo.deleteById(id);
    }

}
