package kz.bi.rest.controller.dto.incubator.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class IncubatorInvestment {
    @NotNull(message = "Seed is required")
    @DecimalMin(value = "0", message = "Seed must be non-negative")
    private BigDecimal seed;

    @NotNull(message = "State is required")
    @DecimalMin(value = "0", message = "State must be non-negative")
    private BigDecimal state;

    @NotNull(message = "Privates is required")
    @DecimalMin(value = "0", message = "Privates must be non-negative")
    private BigDecimal privates;

    @NotNull(message = "Current year investment is required")
    @DecimalMin(value = "0", message = "Current year investment must be non-negative")
    private BigDecimal currentYearInvestment;

    @NotNull(message = "Cumulative investment is required")
    @DecimalMin(value = "0", message = "Cumulative investment must be non-negative")
    private BigDecimal cumulativeInvestment;
}
