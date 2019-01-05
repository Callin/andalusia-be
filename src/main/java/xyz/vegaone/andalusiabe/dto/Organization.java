package xyz.vegaone.andalusiabe.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Organization {
    private Long id;

    private String name;

    private String description;

    @JsonIgnore
    private List<User> users;

    @JsonIgnore
    private List<Project> projects;

}
