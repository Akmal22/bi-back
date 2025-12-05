package kz.bi.rest.controller.dto.incubator.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "Incubator space information")
@Data
public class IncubatorSpace {
    @Schema(description = "Overall space in square meters", example = "1000", required = true)
    @NotNull(message = "Overall space is required")
    @Min(value = 0, message = "Overall space must be non-negative")
    private Integer overallSpace;

    @Schema(description = "Average resident space in square meters", example = "50", required = true)
    @NotNull(message = "Average resident space is required")
    @Min(value = 0, message = "Average resident space must be non-negative")
    private Integer avgResidentSpace;

    @Schema(description = "Communal space in square meters", example = "200", required = true)
    @NotNull(message = "Communal space is required")
    @Min(value = 0, message = "Communal space must be non-negative")
    private Integer communalSpace;

    @Schema(description = "Admin space in square meters", example = "100", required = true)
    @NotNull(message = "Admin space is required")
    @Min(value = 0, message = "Admin space must be non-negative")
    private Integer adminSpace;

    @Schema(description = "Communal space ratio percentage", example = "20", required = true, minimum = "0", maximum = "100")
    @NotNull(message = "Communal space ratio is required")
    @Min(value = 0, message = "Communal space ratio must be between 0 and 100")
    @Max(value = 100, message = "Communal space ratio must be between 0 and 100")
    private Integer communalSpaceRatio;
}
