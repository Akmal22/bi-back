package kz.bi.rest.controller.dto.incubator.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class IncubatorIncome {
    private BigDecimal initialCapital;
    private BigDecimal paidServicesIncome;
    private BigDecimal paidTrainingIncome;
    private BigDecimal paidFacilitiesIncome;
    private BigDecimal donors;
    private BigDecimal state;
    private BigDecimal loans;
}
