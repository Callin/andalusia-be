package xyz.vegaone.andalusiabe.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class User {

    private Long id;

    private String name;

    private String email;

    private Organization organization;

    private List<Project> projects;
}
