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

        return mapAndRemoveTaskFromUserStory(taskEntity);
    }

    public List<Task> getAllUserStories() {
        List<TaskEntity> taskEntityList = taskRepo.findAll();
        return taskEntityList
                .stream()
                .map(this::mapAndRemoveTaskFromUserStory)
                .collect(Collectors.toList());
    }


    public Task createTask(Task task) {
        TaskEntity taskEntity =
                taskRepo.save(mapper.map(task, TaskEntity.class));
        return mapAndRemoveTaskFromUserStory(taskEntity);
    }

    public Task updateTask(Task task) {
        TaskEntity taskEntity =
                taskRepo.save(mapper.map(task, TaskEntity.class));
        return mapAndRemoveTaskFromUserStory(taskEntity);
    }

    public void deleteTask(Long id) {
        taskRepo.deleteById(id);
    }

    /**
     * Breaks circular reference of Task that has a list of Users that have an Task that has a list of
     * USers.
     *
     * @param taskEntity the task that will have it's circular reference fixed
     * @return the task
     */
    private Task mapAndRemoveTaskFromUserStory(TaskEntity taskEntity) {
        Task task = mapper.map(taskEntity, Task.class);
        if (task.getUserStory() != null) {
            task.getUserStory().setTasks(null);
        }
        return task;
    }
}
