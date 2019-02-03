package xyz.vegaone.andalusiabe.service;

import org.dozer.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import xyz.vegaone.andalusiabe.domain.ProjectEntity;
import xyz.vegaone.andalusiabe.domain.SprintEntity;
import xyz.vegaone.andalusiabe.domain.UserStoryEntity;
import xyz.vegaone.andalusiabe.dto.Sprint;
import xyz.vegaone.andalusiabe.dto.UserStory;
import xyz.vegaone.andalusiabe.exception.InvalidArgumentException;
import xyz.vegaone.andalusiabe.repo.UserStoryRepo;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserStoryService {

    private UserStoryRepo userStoryRepo;

    private Mapper mapper;

    private SprintService sprintService;

    public UserStoryService(UserStoryRepo userStoryRepo,
                            Mapper mapper,
                            SprintService sprintService) {
        this.userStoryRepo = userStoryRepo;
        this.mapper = mapper;
        this.sprintService = sprintService;
    }

    public UserStory getUserStory(Long id) {
        UserStoryEntity userStoryEntity = userStoryRepo.findById(id).orElseThrow(EntityNotFoundException::new);

        return mapper.map(userStoryEntity, UserStory.class);
    }

    public List<UserStory> getAllUserStories() {
        List<UserStoryEntity> userStoryEntityList = userStoryRepo.findAll();
        return userStoryEntityList
                .stream()
                .map(userStoryEntity -> mapper.map(userStoryEntity, UserStory.class))
                .collect(Collectors.toList());
    }

    public List<UserStory> getAllUserStoriesByProjectIdAndCurrentSprint(Long projectId) throws InvalidArgumentException {
        List<Sprint> sprintList = sprintService.getSprintsByProjectIdAndDateInterval(projectId, new Date(), new Date());

        if (CollectionUtils.isEmpty(sprintList)) {
            throw new InvalidArgumentException("There is no current sprint defined");
        }

        return getAllUserStoriesByProjectIdAndSprintId(projectId, sprintList.get(0).getId());
    }

    public List<UserStory> getAllUserStoriesByProjectIdAndSprintId(Long projectId, Long sprintId) {
        ProjectEntity project = new ProjectEntity();
        project.setId(projectId);

        SprintEntity sprint = new SprintEntity();
        sprint.setId(sprintId);

        List<UserStoryEntity> userStoryEntityList = userStoryRepo.findAllByProjectAndSprint(project, sprint);

        return userStoryEntityList
                .stream()
                .map(userStoryEntity -> mapper.map(userStoryEntity, UserStory.class))
                .collect(Collectors.toList());
    }


    public UserStory createUserStory(UserStory userStory) {
        UserStoryEntity userStoryEntity =
                userStoryRepo.save(mapper.map(userStory, UserStoryEntity.class));
        return mapper.map(userStoryEntity, UserStory.class);
    }

    public UserStory updateUserStory(UserStory userStory) {
        UserStoryEntity userStoryEntity =
                userStoryRepo.save(mapper.map(userStory, UserStoryEntity.class));
        return mapper.map(userStoryEntity, UserStory.class);
    }

    public void deleteUserStory(Long id) {
        userStoryRepo.deleteById(id);
    }
}
