package xyz.vegaone.andalusiabe.domain;

import lombok.Getter;
import lombok.Setter;
import xyz.vegaone.andalusiabe.dto.UserStory;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private OrganizationEntity organization;

    @ManyToMany(mappedBy = "users")
    private List<ProjectEntity> projects;

    @OneToMany(mappedBy = "user")
    private List<UserStoryEntity> userStories;


    @OneToMany(mappedBy = "user")
    private List<TaskEntity> tasks;


    @OneToMany(mappedBy = "user")
    private List<BugEntity> bugs;
}
