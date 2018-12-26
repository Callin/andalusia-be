package xyz.vegaone.andalusiabe.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "organization")
@Getter
@Setter
public class OrganizationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "organization", fetch = FetchType.EAGER)
    private List<UserEntity> users;

    @OneToMany(mappedBy = "organization")
    private List<ProjectEntity> projects;

}
