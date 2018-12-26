package xyz.vegaone.andalusiabe.dto;

import lombok.Getter;
import lombok.Setter;
import xyz.vegaone.andalusiabe.domain.OrganizationEntity;
import xyz.vegaone.andalusiabe.domain.UserEntity;

import java.util.List;

@Getter
@Setter
public class Project {

    private Long id;

    private String name;

    private String description;

    private OrganizationEntity organization;

    private List<Sprint> sprints;

    private List<User> users;

}
