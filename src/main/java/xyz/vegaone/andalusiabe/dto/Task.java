package xyz.vegaone.andalusiabe.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Task {

    private Long id;

    private String name;

    private String description;

    private String status;

    private Long priority;

    private Long estimation;

    @JsonBackReference(value = "userstory-task")
    private UserStory userStory;

    private User user;
}
