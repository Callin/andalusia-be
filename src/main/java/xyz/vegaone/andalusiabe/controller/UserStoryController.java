package xyz.vegaone.andalusiabe.controller;

import com.sun.javaws.exceptions.InvalidArgumentException;
import org.springframework.web.bind.annotation.*;
import xyz.vegaone.andalusiabe.dto.UserStory;
import xyz.vegaone.andalusiabe.service.UserStoryService;

import java.util.List;

@RestController
@RequestMapping("/api/userstory")
public class UserStoryController {
    private UserStoryService userStoryService;

    public UserStoryController(UserStoryService userStoryService) {
        this.userStoryService = userStoryService;
    }

    @GetMapping("/project/{projectId}/currentsprint")
    public List<UserStory> getAllUserStoriesByProjectIdAndCurrentSprint(@PathVariable Long projectId) throws InvalidArgumentException {
        return userStoryService.getAllUserStoriesByProjectIdAndCurrentSprint(projectId);
    }

    @GetMapping("/project/{projectId}/sprint/{sprintId}")
    public List<UserStory> getAllUserStoriesByProjectIdAndSprintId(@PathVariable Long projectId,
                                                                   @PathVariable Long sprintId) {
        return userStoryService.getAllUserStoriesByProjectIdAndSprintId(projectId, sprintId);
    }

    @GetMapping("{id}")
    public UserStory getUserStory(@PathVariable Long id) {
        return userStoryService.getUserStory(id);
    }

    @GetMapping("/all")
    public List<UserStory> getAllUserStorys() {
        return userStoryService.getAllUserStories();
    }

    @PostMapping
    public UserStory createUserStory(@RequestBody UserStory userStory) {
        return userStoryService.createUserStory(userStory);
    }

    @PutMapping
    public UserStory updateUserStory(@RequestBody UserStory userStory) {
        return userStoryService.updateUserStory(userStory);
    }

    @DeleteMapping("{id}")
    public void deleteUserStory(@PathVariable Long id) {
        userStoryService.deleteUserStory(id);
    }
}
