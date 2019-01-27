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

        return mapper.map(bugEntity, Bug.class);
    }

    public List<Bug> getAllUserStories() {
        List<BugEntity> bugEntityList = bugRepo.findAll();
        return bugEntityList
                .stream()
                .map(bugEntity -> mapper.map(bugEntity, Bug.class))
                .collect(Collectors.toList());
    }


    public Bug createBug(Bug bug) {
        BugEntity bugEntity =
                bugRepo.save(mapper.map(bug, BugEntity.class));
        return mapper.map(bugEntity, Bug.class);
    }

    public Bug updateBug(Bug bug) {
        BugEntity bugEntity =
                bugRepo.save(mapper.map(bug, BugEntity.class));
        return mapper.map(bugEntity, Bug.class);
    }

    public void deleteBug(Long id) {
        bugRepo.deleteById(id);
    }
}
