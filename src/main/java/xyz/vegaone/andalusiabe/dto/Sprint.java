package xyz.vegaone.andalusiabe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Sprint {

    private Long id;

    private Integer number;

    private Date startDate;

    private Date endDate;

    private Project project;
}
