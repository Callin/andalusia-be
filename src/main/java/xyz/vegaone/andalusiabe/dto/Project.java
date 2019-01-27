package xyz.vegaone.andalusiabe.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Project {

    private Long id;

    private String name;

    private String description;

    @JsonBackReference(value = "organization-projects")
    private Organization organization;

    @JsonManagedReference(value = "project-sprints")
    private List<Sprint> sprints;

    private List<User> users;

    @JsonManagedReference(value = "project-userstory")
    private List<UserStory> userStories;

}
