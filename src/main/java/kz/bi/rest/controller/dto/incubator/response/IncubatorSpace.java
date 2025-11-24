package kz.bi.rest.controller.dto.incubator.response;

import lombok.Data;

@Data
public class IncubatorSpace {
    private Integer overallSpace;
    private Integer avgResidentSpace;
    private Integer communalSpace;
    private Integer adminSpace;
    private Integer communalSpaceRatio;
}
