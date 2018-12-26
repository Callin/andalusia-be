package xyz.vegaone.andalusiabe.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Sprint {

    private Long id;

    private Integer number;

    private Date startDate;

    private Date endDate;

    private Project project;
}
