package kz.bi.rest.controller.dto.incubator.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "Incubator investment data for a specific year")
@Data
public class IncubatorInvestment {
    @Schema(description = "Year", example = "2023")
    private Integer year;
    @Schema(description = "Seed investment", example = "500000.00")
    private BigDecimal seed;
    @Schema(description = "State investment", example = "300000.00")
    private BigDecimal state;
    @Schema(description = "Private investment", example = "200000.00")
    private BigDecimal privates;
    @Schema(description = "Current year investment", example = "1000000.00")
    private BigDecimal currentYearInvestment;
    @Schema(description = "Cumulative investment", example = "5000000.00")
    private BigDecimal cumulativeInvestment;
}
