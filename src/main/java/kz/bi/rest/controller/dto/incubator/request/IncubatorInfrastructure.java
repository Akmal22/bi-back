package kz.bi.rest.controller.dto.incubator.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "Incubator infrastructure information")
@Data
public class IncubatorInfrastructure {
    @Schema(description = "Number of sectors covered", example = "5", required = true)
    @NotNull(message = "Sectors covered is required")
    @Min(value = 0, message = "Sectors covered must be non-negative")
    private Integer sectorsCovered;

    @Schema(description = "Years in operation", example = "3", required = true)
    @NotNull(message = "Years in operation is required")
    @Min(value = 0, message = "Years in operation must be non-negative")
    private Integer yearsInOperation;

    @Schema(description = "Programme duration in months", example = "12", required = true)
    @NotNull(message = "Programme duration is required")
    @Min(value = 0, message = "Programme duration must be non-negative")
    private Integer programmeDuration;
}
