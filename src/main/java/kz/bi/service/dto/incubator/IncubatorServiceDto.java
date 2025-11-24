package kz.bi.service.dto.incubator;

import lombok.Data;

@Data
public class IncubatorServiceDto {
    private Long id;
    private Long incubatorId;
    private Integer offeredServices;
    private Integer freeServices;
    private Integer usedServices;
    private Integer paidServices;
    private Integer offeredFacilities;
    private Integer freeFacilities;
    private Integer usedFacilities;
    private Integer paidFacilities;
    private Integer offeredTrainings;
    private Integer freeTrainings;
    private Integer usedTrainings;
    private Integer paidTrainings;
}
