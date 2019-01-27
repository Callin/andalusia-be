package xyz.vegaone.andalusiabe.service;

import org.dozer.Mapper;
import org.springframework.stereotype.Service;
import xyz.vegaone.andalusiabe.domain.TaskEntity;
import xyz.vegaone.andalusiabe.dto.Task;
import xyz.vegaone.andalusiabe.repo.TaskRepo;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private TaskRepo taskRepo;

    private Mapper mapper;

    public TaskService(TaskRepo taskRepo, Mapper mapper) {
        this.taskRepo = taskRepo;
        this.mapper = mapper;
    }

    public Task getTask(Long id) {
        TaskEntity taskEntity = taskRepo.findById(id).orElseThrow(EntityNotFoundException::new);

        return mapper.map(taskEntity, Task.class);
    }

    public List<Task> getAllUserStories() {
        List<TaskEntity> taskEntityList = taskRepo.findAll();
        return taskEntityList
                .stream()
                .map(taskEntity -> mapper.map(taskEntity, Task.class))
                .collect(Collectors.toList());
    }


    public Task createTask(Task task) {
        TaskEntity taskEntity =
                taskRepo.save(mapper.map(task, TaskEntity.class));
        return mapper.map(taskEntity, Task.class);
    }

    public Task updateTask(Task task) {
        TaskEntity taskEntity =
                taskRepo.save(mapper.map(task, TaskEntity.class));
        return mapper.map(taskEntity, Task.class);
    }

    public void deleteTask(Long id) {
        taskRepo.deleteById(id);
    }

}
