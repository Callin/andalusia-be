package xyz.vegaone.andalusiabe.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Sprint {

    private Long id;

    private Integer number;

    private Date startDate;

    private Date endDate;

    @JsonBackReference(value = "project-sprints")
    private Project project;

    @JsonManagedReference(value = "sprint-userstory")
    private List<UserStory> userStories;
}
