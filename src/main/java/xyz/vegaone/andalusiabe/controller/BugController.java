package xyz.vegaone.andalusiabe.controller;

import org.springframework.web.bind.annotation.*;
import xyz.vegaone.andalusiabe.dto.Bug;
import xyz.vegaone.andalusiabe.service.BugService;

import java.util.List;

@RestController
@RequestMapping("/api/bug")
public class BugController {
    private BugService bugService;

    public BugController(BugService bugService) {
        this.bugService = bugService;
    }

    @GetMapping("{id}")
    public Bug getBug(@PathVariable Long id) {
        return bugService.getBug(id);
    }

    @GetMapping("/all")
    public List<Bug> getAllBugs() {
        return bugService.getAllUserStories();
    }

    @PostMapping
    public Bug createBug(@RequestBody Bug sprint) {
        return bugService.createBug(sprint);
    }

    @PutMapping
    public Bug updateBug(@RequestBody Bug sprint) {
        return bugService.updateBug(sprint);
    }

    @DeleteMapping("{id}")
    public void deleteBug(@PathVariable Long id) {
        bugService.deleteBug(id);
    }
}
