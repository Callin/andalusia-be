package xyz.vegaone.andalusiabe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @JsonManagedReference(value = "organization-users")
    private List<User> users;

    @JsonManagedReference(value = "organization-projects")
    private List<Project> projects;

}
