package kz.bi.rest.controller.dto.incubator.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class IncubatorProjects {
    @NotNull(message = "Year is required")
    @Min(value = 1970, message = "Year must be at least 1970")
    @Max(value = 9999, message = "Year must not exceed current year")
    private Integer year;

    @NotNull(message = "Projects count is required")
    @Min(value = 0, message = "Projects count must be non-negative")
    private Integer projectsCount;

    @NotNull(message = "Fund is required")
    @DecimalMin(value = "0", message = "Fund must be non-negative")
    private BigDecimal fund;
}
