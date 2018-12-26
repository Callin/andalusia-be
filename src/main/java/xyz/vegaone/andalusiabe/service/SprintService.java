package xyz.vegaone.andalusiabe.service;

import org.dozer.Mapper;
import org.springframework.stereotype.Service;
import xyz.vegaone.andalusiabe.domain.SprintEntity;
import xyz.vegaone.andalusiabe.dto.Sprint;
import xyz.vegaone.andalusiabe.repo.SprintRepo;

import javax.persistence.EntityNotFoundException;
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

    public Sprint getSprint(Long id) {
        SprintEntity sprintEntity = sprintRepo.findById(id).orElseThrow(EntityNotFoundException::new);

        return mapSprintAndRemoveSprintFromUser(sprintEntity);
    }

    public List<Sprint> getAllSprints() {
        List<SprintEntity> sprintEntityList = sprintRepo.findAll();
        return sprintEntityList
                .stream()
                .map(this::mapSprintAndRemoveSprintFromUser)
                .collect(Collectors.toList());
    }


    public Sprint createSprint(Sprint sprint) {
        SprintEntity sprintEntity =
                sprintRepo.save(mapper.map(sprint, SprintEntity.class));
        return mapSprintAndRemoveSprintFromUser(sprintEntity);
    }

    public Sprint updateSprint(Sprint sprint) {
        SprintEntity sprintEntity =
                sprintRepo.save(mapper.map(sprint, SprintEntity.class));
        return mapSprintAndRemoveSprintFromUser(sprintEntity);
    }

    public void deleteSprint(Long id) {
        sprintRepo.deleteById(id);
    }

    /**
     * Breaks circular reference of Sprint that has a list of Users that have an Sprint that has a list of
     * USers.
     *
     * @param sprintEntity the sprint that will have it's circular reference fixed
     * @return the sprint
     */
    private Sprint mapSprintAndRemoveSprintFromUser(SprintEntity sprintEntity) {
        Sprint sprint = mapper.map(sprintEntity, Sprint.class);
        if (sprint.getProject() != null) {
            sprint.setProject(null);
        }
        return sprint;
    }
}
