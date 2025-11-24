package kz.bi.rest.controller.dto.incubator.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class IncubatorInfrastructure {
    @NotNull(message = "Sectors covered is required")
    @Min(value = 0, message = "Sectors covered must be non-negative")
    private Integer sectorsCovered;

    @NotNull(message = "Years in operation is required")
    @Min(value = 0, message = "Years in operation must be non-negative")
    private Integer yearsInOperation;

    @NotNull(message = "Programme duration is required")
    @Min(value = 0, message = "Programme duration must be non-negative")
    private Integer programmeDuration;
}
