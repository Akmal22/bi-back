package kz.bi.rest.controller.dto.incubator.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class IncubatorIncome {
    @NotNull(message = "Initial capital is required")
    @DecimalMin(value = "0", message = "Initial capital must be non-negative")
    private BigDecimal initialCapital;

    @NotNull(message = "Paid services income is required")
    @DecimalMin(value = "0", message = "Paid services income must be non-negative")
    private BigDecimal paidServicesIncome;

    @NotNull(message = "Paid training income is required")
    @DecimalMin(value = "0", message = "Paid training income must be non-negative")
    private BigDecimal paidTrainingIncome;

    @NotNull(message = "Paid facilities income is required")
    @DecimalMin(value = "0", message = "Paid facilities income must be non-negative")
    private BigDecimal paidFacilitiesIncome;

    @NotNull(message = "Donors is required")
    @DecimalMin(value = "0", message = "Donors must be non-negative")
    private BigDecimal donors;

    @NotNull(message = "State is required")
    @DecimalMin(value = "0", message = "State must be non-negative")
    private BigDecimal state;

    @NotNull(message = "Loans is required")
    @DecimalMin(value = "0", message = "Loans must be non-negative")
    private BigDecimal loans;
}
