package kz.bi.service.dto.incubator;

import lombok.Data;

@Data
public class IncubatorSpaceDto {
    private Long id;
    private Long incubatorId;
    private Integer overallSpace;
    private Integer avgResidentSpace;
    private Integer communalSpace;
    private Integer adminSpace;
    private Integer communalSpaceRatio;
}
