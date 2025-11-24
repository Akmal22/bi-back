package kz.bi.rest.controller.dto.incubator.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class IncubatorResidents {
    @NotNull(message = "Incubated companies is required")
    @DecimalMin(value = "0", message = "Incubated companies must be non-negative")
    private BigDecimal incubatedCompanies;

    @NotNull(message = "Failed companies is required")
    @DecimalMin(value = "0", message = "Failed companies must be non-negative")
    private BigDecimal failedCompanies;

    @NotNull(message = "Graduated companies is required")
    @DecimalMin(value = "0", message = "Graduated companies must be non-negative")
    private BigDecimal graduatedCompanies;

    @NotNull(message = "Received application is required")
    @DecimalMin(value = "0", message = "Received application must be non-negative")
    private BigDecimal receivedApplication;

    @NotNull(message = "Accepted application is required")
    @DecimalMin(value = "0", message = "Accepted application must be non-negative")
    private BigDecimal acceptedApplication;

    @NotNull(message = "Active after 3 months is required")
    @DecimalMin(value = "0", message = "Active after 3 months must be non-negative")
    private BigDecimal activeAfter3Months;

    @NotNull(message = "Active after 6 months is required")
    @DecimalMin(value = "0", message = "Active after 6 months must be non-negative")
    private BigDecimal activeAfter6Months;

    @NotNull(message = "Active after 1 year is required")
    @DecimalMin(value = "0", message = "Active after 1 year must be non-negative")
    private BigDecimal activeAfter1Year;

    @NotNull(message = "Active after 3 years is required")
    @DecimalMin(value = "0", message = "Active after 3 years must be non-negative")
    private BigDecimal activeAfter3Years;

    @NotNull(message = "Active after 5 years is required")
    @DecimalMin(value = "0", message = "Active after 5 years must be non-negative")
    private BigDecimal activeAfter5Years;

    @NotNull(message = "Failed after 3 months is required")
    @DecimalMin(value = "0", message = "Failed after 3 months must be non-negative")
    private BigDecimal failedAfter3Months;

    @NotNull(message = "Failed after 6 months is required")
    @DecimalMin(value = "0", message = "Failed after 6 months must be non-negative")
    private BigDecimal failedAfter6Months;

    @NotNull(message = "Failed after 1 year is required")
    @DecimalMin(value = "0", message = "Failed after 1 year must be non-negative")
    private BigDecimal failedAfter1Year;

    @NotNull(message = "Failed after 3 years is required")
    @DecimalMin(value = "0", message = "Failed after 3 years must be non-negative")
    private BigDecimal failedAfter3Years;

    @NotNull(message = "Failed after 5 years is required")
    @DecimalMin(value = "0", message = "Failed after 5 years must be non-negative")
    private BigDecimal failedAfter5Years;
}
