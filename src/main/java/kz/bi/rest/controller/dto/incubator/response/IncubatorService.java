package kz.bi.rest.controller.dto.incubator.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Incubator services information")
@Data
public class IncubatorService {
    @Schema(description = "Number of offered services", example = "10")
    private Integer offeredServices;
    @Schema(description = "Number of free services", example = "5")
    private Integer freeServices;
    @Schema(description = "Number of paid services", example = "5")
    private Integer paidServices;
    @Schema(description = "Number of used services", example = "8")
    private Integer usedServices;
    @Schema(description = "Number of offered facilities", example = "8")
    private Integer offeredFacilities;
    @Schema(description = "Number of free facilities", example = "4")
    private Integer freeFacilities;
    @Schema(description = "Number of paid facilities", example = "4")
    private Integer paidFacilities;
    @Schema(description = "Number of used facilities", example = "6")
    private Integer usedFacilities;
    @Schema(description = "Number of offered trainings", example = "12")
    private Integer offeredTrainings;
    @Schema(description = "Number of free trainings", example = "6")
    private Integer freeTrainings;
    @Schema(description = "Number of paid trainings", example = "6")
    private Integer paidTrainings;
    @Schema(description = "Number of used trainings", example = "10")
    private Integer usedTrainings;
}
