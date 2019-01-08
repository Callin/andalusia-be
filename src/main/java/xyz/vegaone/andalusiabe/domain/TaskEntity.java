package xyz.vegaone.andalusiabe.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "task")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String status;

    private Integer priority;

    private Integer estimation;

    @ManyToOne
    @JoinColumn(name = "user_story_id")
    private UserStoryEntity userStory;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
