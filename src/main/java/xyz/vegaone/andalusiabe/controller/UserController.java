package xyz.vegaone.andalusiabe.controller;

import org.springframework.web.bind.annotation.*;
import xyz.vegaone.andalusiabe.dto.User;
import xyz.vegaone.andalusiabe.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/organization/{id}")
    public List<User> getAllUserssByOrganizationId(@PathVariable Long id) {
        return userService.getAllUsersByOrganizationId(id);
    }

    @GetMapping("/project/{id}")
    public List<User> getAllUsersByProjectId(@PathVariable Long id) {
        return userService.getAllUsersByProjectId(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/{email}/authenticate")
    public User checkCredentials(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }
}
