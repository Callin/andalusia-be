package xyz.vegaone.andalusiabe.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sprint")
@Getter
@Setter
public class SprintEntity {

    public SprintEntity() {
    }

    public SprintEntity(
            Long id,
            Integer number,
            Date startDate,
            Date endDate) {
        this.id = id;
        this.number = number;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer number;

    private Date startDate;

    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectEntity project;

    @OneToMany(mappedBy = "sprint")
    private List<UserStoryEntity> userStories;
}
