package kz.bi.service.dto.incubator;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class IncubatorIncomeDto {
    private Long id;
    private Long incubatorId;
    private Integer year;
    private BigDecimal initialCapital;
    private BigDecimal paidServicesIncome;
    private BigDecimal paidTrainingIncome;
    private BigDecimal paidFacilitiesIncome;
    private BigDecimal donors;
    private BigDecimal state;
    private BigDecimal loans;
}
