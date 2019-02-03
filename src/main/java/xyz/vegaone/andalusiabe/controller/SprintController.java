package xyz.vegaone.andalusiabe.controller;

import org.springframework.web.bind.annotation.*;
import xyz.vegaone.andalusiabe.dto.Sprint;
import xyz.vegaone.andalusiabe.service.SprintService;

import java.util.List;

@RestController
@RequestMapping("/api/sprint")
public class SprintController {
    private SprintService sprintService;

    public SprintController(SprintService sprintService) {
        this.sprintService = sprintService;
    }

    @GetMapping("{id}")
    public Sprint getSprint(@PathVariable Long id) {
        return sprintService.getSprint(id);
    }

    @GetMapping("/project/{projectId}/brief")
    public List<Sprint> getAllByProjectIdBrief(@PathVariable Long projectId) {
        return sprintService.getAllByProjectIdBrief(projectId);
    }

    @GetMapping("/project/{projectId}")
    public List<Sprint> getAllByProjectId(@PathVariable Long projectId) {
        return sprintService.getAllByProjectIdBrief(projectId);
    }

    @GetMapping("/all")
    public List<Sprint> getAllSprints() {
        return sprintService.getAllSprints();
    }

    @PostMapping
    public Sprint createSprint(@RequestBody Sprint sprint) {
        return sprintService.createSprint(sprint);
    }

    @PutMapping
    public Sprint updateSprint(@RequestBody Sprint sprint) {
        return sprintService.updateSprint(sprint);
    }

    @DeleteMapping("{id}")
    public void deleteSprint(@PathVariable Long id) {
        sprintService.deleteSprint(id);
    }
}
