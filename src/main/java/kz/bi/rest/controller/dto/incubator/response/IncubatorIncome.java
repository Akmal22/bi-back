package kz.bi.rest.controller.dto.incubator.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "Incubator income data for a specific year")
@Data
public class IncubatorIncome {
    @Schema(description = "Year", example = "2023")
    private Integer year;
    @Schema(description = "Initial capital amount", example = "1000000.00")
    private BigDecimal initialCapital;
    @Schema(description = "Income from paid services", example = "50000.00")
    private BigDecimal paidServicesIncome;
    @Schema(description = "Income from paid training", example = "30000.00")
    private BigDecimal paidTrainingIncome;
    @Schema(description = "Income from paid facilities", example = "20000.00")
    private BigDecimal paidFacilitiesIncome;
    @Schema(description = "Donations received", example = "100000.00")
    private BigDecimal donors;
    @Schema(description = "State funding", example = "200000.00")
    private BigDecimal state;
    @Schema(description = "Loans received", example = "50000.00")
    private BigDecimal loans;
}
