package kz.bi.rest.controller.dto.incubator.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "Incubator income data for a specific year")
@Data
public class IncubatorIncome {
    @Schema(description = "Year", example = "2023", required = true)
    @NotNull(message = "Year is required")
    @DecimalMin(value = "1970", message = "Incubated companies must be non-negative")
    private Integer year;

    @Schema(description = "Initial capital amount", example = "1000000.00", required = true)
    @NotNull(message = "Initial capital is required")
    @DecimalMin(value = "0", message = "Initial capital must be non-negative")
    private BigDecimal initialCapital;

    @Schema(description = "Income from paid services", example = "50000.00", required = true)
    @NotNull(message = "Paid services income is required")
    @DecimalMin(value = "0", message = "Paid services income must be non-negative")
    private BigDecimal paidServicesIncome;

    @Schema(description = "Income from paid training", example = "30000.00", required = true)
    @NotNull(message = "Paid training income is required")
    @DecimalMin(value = "0", message = "Paid training income must be non-negative")
    private BigDecimal paidTrainingIncome;

    @Schema(description = "Income from paid facilities", example = "20000.00", required = true)
    @NotNull(message = "Paid facilities income is required")
    @DecimalMin(value = "0", message = "Paid facilities income must be non-negative")
    private BigDecimal paidFacilitiesIncome;

    @Schema(description = "Donations received", example = "100000.00", required = true)
    @NotNull(message = "Donors is required")
    @DecimalMin(value = "0", message = "Donors must be non-negative")
    private BigDecimal donors;

    @Schema(description = "State funding", example = "200000.00", required = true)
    @NotNull(message = "State is required")
    @DecimalMin(value = "0", message = "State must be non-negative")
    private BigDecimal state;

    @Schema(description = "Loans received", example = "50000.00", required = true)
    @NotNull(message = "Loans is required")
    @DecimalMin(value = "0", message = "Loans must be non-negative")
    private BigDecimal loans;
}
