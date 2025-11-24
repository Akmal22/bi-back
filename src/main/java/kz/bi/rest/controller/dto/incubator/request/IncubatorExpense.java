package kz.bi.rest.controller.dto.incubator.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class IncubatorExpense {
    @NotNull(message = "Payroll is required")
    @DecimalMin(value = "0", message = "Payroll must be non-negative")
    private BigDecimal payroll;

    @NotNull(message = "Equipment is required")
    @DecimalMin(value = "0", message = "Equipment must be non-negative")
    private BigDecimal equipment;

    @NotNull(message = "Utilities is required")
    @DecimalMin(value = "0", message = "Utilities must be non-negative")
    private BigDecimal utilities;

    @NotNull(message = "Tax is required")
    @DecimalMin(value = "0", message = "Tax must be non-negative")
    private BigDecimal tax;

    @NotNull(message = "Rents is required")
    @DecimalMin(value = "0", message = "Rents must be non-negative")
    private BigDecimal rents;

    @NotNull(message = "Bank repayments is required")
    @DecimalMin(value = "0", message = "Bank repayments must be non-negative")
    private BigDecimal bankRepayments;

    @NotNull(message = "Material is required")
    @DecimalMin(value = "0", message = "Material must be non-negative")
    private BigDecimal material;

    @NotNull(message = "Insurance is required")
    @DecimalMin(value = "0", message = "Insurance must be non-negative")
    private BigDecimal insurance;
}
