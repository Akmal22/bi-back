package kz.bi.rest.controller.dto.incubator.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "Incubator residents data for a specific year")
@Data
public class IncubatorResidents {
    @Schema(description = "Year", example = "2023", required = true)
    @NotNull(message = "Year is required")
    @DecimalMin(value = "1970", message = "Incubated companies must be non-negative")
    private Integer year;

    @Schema(description = "Number of incubated companies", example = "25.0", required = true)
    @NotNull(message = "Incubated companies is required")
    @DecimalMin(value = "0", message = "Incubated companies must be non-negative")
    private BigDecimal incubatedCompanies;

    @Schema(description = "Number of failed companies", example = "5.0", required = true)
    @NotNull(message = "Failed companies is required")
    @DecimalMin(value = "0", message = "Failed companies must be non-negative")
    private BigDecimal failedCompanies;

    @Schema(description = "Number of graduated companies", example = "15.0", required = true)
    @NotNull(message = "Graduated companies is required")
    @DecimalMin(value = "0", message = "Graduated companies must be non-negative")
    private BigDecimal graduatedCompanies;

    @Schema(description = "Number of received applications", example = "100.0", required = true)
    @NotNull(message = "Received application is required")
    @DecimalMin(value = "0", message = "Received application must be non-negative")
    private BigDecimal receivedApplication;

    @Schema(description = "Number of accepted applications", example = "30.0", required = true)
    @NotNull(message = "Accepted application is required")
    @DecimalMin(value = "0", message = "Accepted application must be non-negative")
    private BigDecimal acceptedApplication;

    @Schema(description = "Number of companies active after 3 months", example = "20.0", required = true)
    @NotNull(message = "Active after 3 months is required")
    @DecimalMin(value = "0", message = "Active after 3 months must be non-negative")
    private BigDecimal activeAfter3Months;

    @Schema(description = "Number of companies active after 6 months", example = "18.0", required = true)
    @NotNull(message = "Active after 6 months is required")
    @DecimalMin(value = "0", message = "Active after 6 months must be non-negative")
    private BigDecimal activeAfter6Months;

    @Schema(description = "Number of companies active after 1 year", example = "15.0", required = true)
    @NotNull(message = "Active after 1 year is required")
    @DecimalMin(value = "0", message = "Active after 1 year must be non-negative")
    private BigDecimal activeAfter1Year;

    @Schema(description = "Number of companies active after 3 years", example = "10.0", required = true)
    @NotNull(message = "Active after 3 years is required")
    @DecimalMin(value = "0", message = "Active after 3 years must be non-negative")
    private BigDecimal activeAfter3Years;

    @Schema(description = "Number of companies active after 5 years", example = "8.0", required = true)
    @NotNull(message = "Active after 5 years is required")
    @DecimalMin(value = "0", message = "Active after 5 years must be non-negative")
    private BigDecimal activeAfter5Years;

    @Schema(description = "Number of companies failed after 3 months", example = "2.0", required = true)
    @NotNull(message = "Failed after 3 months is required")
    @DecimalMin(value = "0", message = "Failed after 3 months must be non-negative")
    private BigDecimal failedAfter3Months;

    @Schema(description = "Number of companies failed after 6 months", example = "3.0", required = true)
    @NotNull(message = "Failed after 6 months is required")
    @DecimalMin(value = "0", message = "Failed after 6 months must be non-negative")
    private BigDecimal failedAfter6Months;

    @Schema(description = "Number of companies failed after 1 year", example = "5.0", required = true)
    @NotNull(message = "Failed after 1 year is required")
    @DecimalMin(value = "0", message = "Failed after 1 year must be non-negative")
    private BigDecimal failedAfter1Year;

    @Schema(description = "Number of companies failed after 3 years", example = "7.0", required = true)
    @NotNull(message = "Failed after 3 years is required")
    @DecimalMin(value = "0", message = "Failed after 3 years must be non-negative")
    private BigDecimal failedAfter3Years;

    @Schema(description = "Number of companies failed after 5 years", example = "10.0", required = true)
    @NotNull(message = "Failed after 5 years is required")
    @DecimalMin(value = "0", message = "Failed after 5 years must be non-negative")
    private BigDecimal failedAfter5Years;
}
