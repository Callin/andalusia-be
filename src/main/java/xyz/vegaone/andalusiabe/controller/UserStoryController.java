package xyz.vegaone.andalusiabe.controller;

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
