package kz.bi.rest.controller.dto.incubator.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class IncubatorProjects {
    private Integer year;
    private Integer projectsCount;
    private BigDecimal fund;
}
