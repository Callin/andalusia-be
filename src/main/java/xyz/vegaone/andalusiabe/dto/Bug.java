package xyz.vegaone.andalusiabe.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Bug {

    private Long id;

    private String name;

    private String description;

    private String status;

    private Integer priority;

    private Integer estimation;

    private String severity;

    @JsonBackReference(value = "userstory-bug")
    private UserStory userStory;

    @JsonBackReference(value = "user-bug")
    private User user;
}
