package kz.bi.rest.controller.dto.incubator.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "Incubator projects data for a specific year")
@Data
public class IncubatorProjects {
    @Schema(description = "Year", example = "2023", required = true, minimum = "1970", maximum = "9999")
    @NotNull(message = "Year is required")
    @Min(value = 1970, message = "Year must be at least 1970")
    @Max(value = 9999, message = "Year must not exceed current year")
    private Integer year;

    @Schema(description = "Number of projects", example = "10", required = true)
    @NotNull(message = "Projects count is required")
    @Min(value = 0, message = "Projects count must be non-negative")
    private Integer projectsCount;

    @Schema(description = "Fund amount", example = "500000.00", required = true)
    @NotNull(message = "Fund is required")
    @DecimalMin(value = "0", message = "Fund must be non-negative")
    private BigDecimal fund;
}
