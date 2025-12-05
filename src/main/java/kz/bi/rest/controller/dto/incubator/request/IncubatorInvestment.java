package kz.bi.rest.controller.dto.incubator.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "Incubator investment data for a specific year")
@Data
public class IncubatorInvestment {
    @Schema(description = "Year", example = "2023", required = true)
    @NotNull(message = "Year is required")
    @DecimalMin(value = "1970", message = "Incubated companies must be non-negative")
    private Integer year;

    @Schema(description = "Seed investment", example = "500000.00", required = true)
    @NotNull(message = "Seed is required")
    @DecimalMin(value = "0", message = "Seed must be non-negative")
    private BigDecimal seed;

    @Schema(description = "State investment", example = "300000.00", required = true)
    @NotNull(message = "State is required")
    @DecimalMin(value = "0", message = "State must be non-negative")
    private BigDecimal state;

    @Schema(description = "Private investment", example = "200000.00", required = true)
    @NotNull(message = "Privates is required")
    @DecimalMin(value = "0", message = "Privates must be non-negative")
    private BigDecimal privates;

    @Schema(description = "Current year investment", example = "1000000.00", required = true)
    @NotNull(message = "Current year investment is required")
    @DecimalMin(value = "0", message = "Current year investment must be non-negative")
    private BigDecimal currentYearInvestment;

    @Schema(description = "Cumulative investment", example = "5000000.00", required = true)
    @NotNull(message = "Cumulative investment is required")
    @DecimalMin(value = "0", message = "Cumulative investment must be non-negative")
    private BigDecimal cumulativeInvestment;
}
