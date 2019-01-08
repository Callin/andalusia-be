package xyz.vegaone.andalusiabe.service;

import org.dozer.Mapper;
import org.springframework.stereotype.Service;
import xyz.vegaone.andalusiabe.domain.BugEntity;
import xyz.vegaone.andalusiabe.dto.Bug;
import xyz.vegaone.andalusiabe.repo.BugRepo;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BugService {

    private BugRepo bugRepo;

    private Mapper mapper;

    public BugService(BugRepo bugRepo, Mapper mapper) {
        this.bugRepo = bugRepo;
        this.mapper = mapper;
    }

    public Bug getBug(Long id) {
        BugEntity bugEntity = bugRepo.findById(id).orElseThrow(EntityNotFoundException::new);

        return mapBugAndRemoveBugFromUserStory(bugEntity);
    }

    public List<Bug> getAllUserStories() {
        List<BugEntity> bugEntityList = bugRepo.findAll();
        return bugEntityList
                .stream()
                .map(this::mapBugAndRemoveBugFromUserStory)
                .collect(Collectors.toList());
    }


    public Bug createBug(Bug bug) {
        BugEntity bugEntity =
                bugRepo.save(mapper.map(bug, BugEntity.class));
        return mapBugAndRemoveBugFromUserStory(bugEntity);
    }

    public Bug updateBug(Bug bug) {
        BugEntity bugEntity =
                bugRepo.save(mapper.map(bug, BugEntity.class));
        return mapBugAndRemoveBugFromUserStory(bugEntity);
    }

    public void deleteBug(Long id) {
        bugRepo.deleteById(id);
    }

    /**
     * Breaks circular reference of Bug that has a list of Users that have an Bug that has a list of
     * Users.
     *
     * @param bugEntity the bug that will have it's circular reference fixed
     * @return the bug
     */
    private Bug mapBugAndRemoveBugFromUserStory(BugEntity bugEntity) {
        Bug bug = mapper.map(bugEntity, Bug.class);
        if (bug.getUserStory() != null) {
            bug.getUserStory().setBugs(null);
        }
        return bug;
    }
}
