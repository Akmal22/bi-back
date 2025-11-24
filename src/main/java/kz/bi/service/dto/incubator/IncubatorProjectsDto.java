package kz.bi.service.dto.incubator;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class IncubatorProjectsDto {
    private Long id;
    private Long incubatorId;
    private int year;
    private int projectsCount;
    private BigDecimal fund;
}
