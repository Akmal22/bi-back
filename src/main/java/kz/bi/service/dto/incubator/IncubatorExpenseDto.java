package kz.bi.service.dto.incubator;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class IncubatorExpenseDto {
    private Long id;
    private Long incubatorId;
    private BigDecimal payroll;
    private BigDecimal equipment;
    private BigDecimal utilities;
    private BigDecimal tax;
    private BigDecimal rents;
    private BigDecimal bankRepayments;
    private BigDecimal material;
    private BigDecimal insurance;
}
