package kz.bi.rest.controller.dto.incubator.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "Incubator expense information")
@Data
public class IncubatorExpense {
    @Schema(description = "Payroll expenses", example = "300000.00", required = true)
    @NotNull(message = "Payroll is required")
    @DecimalMin(value = "0", message = "Payroll must be non-negative")
    private BigDecimal payroll;

    @Schema(description = "Equipment expenses", example = "100000.00", required = true)
    @NotNull(message = "Equipment is required")
    @DecimalMin(value = "0", message = "Equipment must be non-negative")
    private BigDecimal equipment;

    @Schema(description = "Utilities expenses", example = "50000.00", required = true)
    @NotNull(message = "Utilities is required")
    @DecimalMin(value = "0", message = "Utilities must be non-negative")
    private BigDecimal utilities;

    @Schema(description = "Tax expenses", example = "40000.00", required = true)
    @NotNull(message = "Tax is required")
    @DecimalMin(value = "0", message = "Tax must be non-negative")
    private BigDecimal tax;

    @Schema(description = "Rent expenses", example = "80000.00", required = true)
    @NotNull(message = "Rents is required")
    @DecimalMin(value = "0", message = "Rents must be non-negative")
    private BigDecimal rents;

    @Schema(description = "Bank repayment expenses", example = "60000.00", required = true)
    @NotNull(message = "Bank repayments is required")
    @DecimalMin(value = "0", message = "Bank repayments must be non-negative")
    private BigDecimal bankRepayments;

    @Schema(description = "Material expenses", example = "30000.00", required = true)
    @NotNull(message = "Material is required")
    @DecimalMin(value = "0", message = "Material must be non-negative")
    private BigDecimal material;

    @Schema(description = "Insurance expenses", example = "20000.00", required = true)
    @NotNull(message = "Insurance is required")
    @DecimalMin(value = "0", message = "Insurance must be non-negative")
    private BigDecimal insurance;
}
