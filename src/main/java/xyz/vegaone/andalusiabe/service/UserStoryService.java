package xyz.vegaone.andalusiabe.service;

import org.dozer.Mapper;
import org.springframework.stereotype.Service;
import xyz.vegaone.andalusiabe.domain.UserStoryEntity;
import xyz.vegaone.andalusiabe.dto.UserStory;
import xyz.vegaone.andalusiabe.repo.UserStoryRepo;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserStoryService {

    private UserStoryRepo userStoryRepo;

    private Mapper mapper;

    public UserStoryService(UserStoryRepo userStoryRepo, Mapper mapper) {
        this.userStoryRepo = userStoryRepo;
        this.mapper = mapper;
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
