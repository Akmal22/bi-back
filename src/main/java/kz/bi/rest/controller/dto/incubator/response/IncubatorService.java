package kz.bi.rest.controller.dto.incubator.response;

import lombok.Data;

@Data
public class IncubatorService {
    private Integer offeredServices;
    private Integer freeServices;
    private Integer paidServices;
    private Integer usedServices;
    private Integer offeredFacilities;
    private Integer freeFacilities;
    private Integer paidFacilities;
    private Integer usedFacilities;
    private Integer offeredTrainings;
    private Integer freeTrainings;
    private Integer paidTrainings;
    private Integer usedTrainings;
}
