package xyz.vegaone.andalusiabe.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    private Long id;

    private String name;

    private String email;

    @JsonBackReference(value = "organization-users")
    private Organization organization;

    @JsonIgnore
    private List<Project> projects;

    @JsonIgnore
    private List<UserStory> userStories;

    @JsonIgnore
    private List<Task> tasks;

    @JsonIgnore
    private List<Bug> bugs;
}
