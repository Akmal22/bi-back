package kz.bi.service.dto.incubator;

import lombok.Data;

@Data
public class IncubatorInfrastructureDto {
    private Long id;
    private Long incubatorId;
    private Integer sectorsCovered;
    private Integer yearsInOperation;
    private Integer programmeDuration;
}
