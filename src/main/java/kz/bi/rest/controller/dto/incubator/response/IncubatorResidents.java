package kz.bi.rest.controller.dto.incubator.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class IncubatorResidents {
    private BigDecimal incubatedCompanies;
    private BigDecimal failedCompanies;
    private BigDecimal graduatedCompanies;
    private BigDecimal receivedApplication;
    private BigDecimal acceptedApplication;
    private BigDecimal activeAfter3Months;
    private BigDecimal activeAfter6Months;
    private BigDecimal activeAfter1Year;
    private BigDecimal activeAfter3Years;
    private BigDecimal activeAfter5Years;
    private BigDecimal failedAfter3Months;
    private BigDecimal failedAfter6Months;
    private BigDecimal failedAfter1Year;
    private BigDecimal failedAfter3Years;
    private BigDecimal failedAfter5Years;
}
