package kz.bi.rest.controller.dto.incubator.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "Incubator residents data for a specific year")
@Data
public class IncubatorResidents {
    @Schema(description = "Year", example = "2023")
    private Integer year;
    @Schema(description = "Number of incubated companies", example = "25.0")
    private BigDecimal incubatedCompanies;
    @Schema(description = "Number of failed companies", example = "5.0")
    private BigDecimal failedCompanies;
    @Schema(description = "Number of graduated companies", example = "15.0")
    private BigDecimal graduatedCompanies;
    @Schema(description = "Number of received applications", example = "100.0")
    private BigDecimal receivedApplication;
    @Schema(description = "Number of accepted applications", example = "30.0")
    private BigDecimal acceptedApplication;
    @Schema(description = "Number of companies active after 3 months", example = "20.0")
    private BigDecimal activeAfter3Months;
    @Schema(description = "Number of companies active after 6 months", example = "18.0")
    private BigDecimal activeAfter6Months;
    @Schema(description = "Number of companies active after 1 year", example = "15.0")
    private BigDecimal activeAfter1Year;
    @Schema(description = "Number of companies active after 3 years", example = "10.0")
    private BigDecimal activeAfter3Years;
    @Schema(description = "Number of companies active after 5 years", example = "8.0")
    private BigDecimal activeAfter5Years;
    @Schema(description = "Number of companies failed after 3 months", example = "2.0")
    private BigDecimal failedAfter3Months;
    @Schema(description = "Number of companies failed after 6 months", example = "3.0")
    private BigDecimal failedAfter6Months;
    @Schema(description = "Number of companies failed after 1 year", example = "5.0")
    private BigDecimal failedAfter1Year;
    @Schema(description = "Number of companies failed after 3 years", example = "7.0")
    private BigDecimal failedAfter3Years;
    @Schema(description = "Number of companies failed after 5 years", example = "10.0")
    private BigDecimal failedAfter5Years;
}
