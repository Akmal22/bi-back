package kz.bi.rest.controller.dto.incubator.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Incubator infrastructure information")
@Data
public class IncubatorInfrastructure {
    @Schema(description = "Number of sectors covered", example = "5")
    private Integer sectorsCovered;
    @Schema(description = "Years in operation", example = "3")
    private Integer yearsInOperation;
    @Schema(description = "Programme duration in months", example = "12")
    private Integer programmeDuration;
}
