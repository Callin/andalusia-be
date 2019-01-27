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
public class UserStory {

    private Long id;

    private String name;

    private String description;

    private String status;

    private Integer priority;

    private Integer estimation;

    @JsonBackReference(value = "project-userstory")
    private Project project;

    @JsonBackReference(value = "sprint-userstory")
    private Sprint sprint;

    private User user;

    @JsonManagedReference(value = "userstory-task")
    private List<Task> tasks;

    @JsonManagedReference(value = "userstory-bug")
    private List<Bug> bugs;
}
