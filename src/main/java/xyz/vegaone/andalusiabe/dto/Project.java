package xyz.vegaone.andalusiabe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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

    private Organization organization;

    private List<Sprint> sprints;

    private List<User> users;

    private List<UserStory> userStories;

}
