package kz.bi.rest.controller.dto.incubator.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "Incubator expense information")
@Data
public class IncubatorExpense {
    @Schema(description = "Payroll expenses", example = "300000.00")
    private BigDecimal payroll;
    @Schema(description = "Equipment expenses", example = "100000.00")
    private BigDecimal equipment;
    @Schema(description = "Utilities expenses", example = "50000.00")
    private BigDecimal utilities;
    @Schema(description = "Tax expenses", example = "40000.00")
    private BigDecimal tax;
    @Schema(description = "Rent expenses", example = "80000.00")
    private BigDecimal rents;
    @Schema(description = "Bank repayment expenses", example = "60000.00")
    private BigDecimal bankRepayments;
    @Schema(description = "Material expenses", example = "30000.00")
    private BigDecimal material;
    @Schema(description = "Insurance expenses", example = "20000.00")
    private BigDecimal insurance;
}
