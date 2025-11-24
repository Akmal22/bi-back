package kz.bi.rest.controller.dto.incubator.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class IncubatorSpace {
    @NotNull(message = "Overall space is required")
    @Min(value = 0, message = "Overall space must be non-negative")
    private Integer overallSpace;

    @NotNull(message = "Average resident space is required")
    @Min(value = 0, message = "Average resident space must be non-negative")
    private Integer avgResidentSpace;

    @NotNull(message = "Communal space is required")
    @Min(value = 0, message = "Communal space must be non-negative")
    private Integer communalSpace;

    @NotNull(message = "Admin space is required")
    @Min(value = 0, message = "Admin space must be non-negative")
    private Integer adminSpace;

    @NotNull(message = "Communal space ratio is required")
    @Min(value = 0, message = "Communal space ratio must be between 0 and 100")
    @Max(value = 100, message = "Communal space ratio must be between 0 and 100")
    private Integer communalSpaceRatio;
}
