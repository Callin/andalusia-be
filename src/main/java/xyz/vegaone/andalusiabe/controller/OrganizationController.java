package xyz.vegaone.andalusiabe.controller;

import org.springframework.web.bind.annotation.*;
import xyz.vegaone.andalusiabe.dto.Organization;
import xyz.vegaone.andalusiabe.service.OrganizationService;

import java.util.List;

@RestController
@RequestMapping("/api/organization")
public class OrganizationController {
    private OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping("{id}")
    public Organization getOrganization(@PathVariable Long id) {
        return organizationService.getOrganization(id);
    }

    @GetMapping("/all")
    public List<Organization> getAllOrganizations() {
        return organizationService.getAllOrganizations();
    }

    @PostMapping
    public Organization createOrganization(@RequestBody Organization organization) {
        return organizationService.createOrganization(organization);
    }

    @PutMapping
    public Organization updateOrganization(@RequestBody Organization organization) {
        return organizationService.updateOrganization(organization);
    }

    @GetMapping("/user/{id}")
    public List<Organization> getAllByUsersIsContaining(@PathVariable Long id) {
        return organizationService.findAllByUsersIsContaining(id);
    }

    @DeleteMapping("{id}")
    public void deleteOrganization(@PathVariable Long id) {
        organizationService.deleteOrganization(id);
    }
}
