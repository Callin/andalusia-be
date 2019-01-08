package xyz.vegaone.andalusiabe.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
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

    private Project project;

    @JsonIgnore
    private List<UserStory> userStories;
}
