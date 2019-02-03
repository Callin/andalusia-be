package xyz.vegaone.andalusiabe.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import xyz.vegaone.andalusiabe.domain.ProjectEntity;
import xyz.vegaone.andalusiabe.domain.SprintEntity;
import xyz.vegaone.andalusiabe.dto.Project;

import java.util.Date;
import java.util.List;

@Repository
public interface SprintRepo extends JpaRepository<SprintEntity, Long> {
    List<SprintEntity> findAllByProject(ProjectEntity project);

    @Query("select new SprintEntity(sprint.id, sprint.number, sprint.startDate, sprint.endDate) " +
            "from SprintEntity sprint where sprint.project = :project")
    List<SprintEntity> findAllByProjectBrief(@Param("project") ProjectEntity project);

    @Query("select sprint from SprintEntity sprint where " +
            "sprint.project = :project and sprint.startDate <= :from and sprint.endDate >= :to ")
    List<SprintEntity> findAllByProjectAndStartDateBeforeAndEndDateAfter(@Param("project") ProjectEntity project,
                                                                         @Param("from") Date from,
                                                                         @Param("to") Date to);

}
