package kz.bi.rest.controller.dto.incubator.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class IncubatorExpense {
    private BigDecimal payroll;
    private BigDecimal equipment;
    private BigDecimal utilities;
    private BigDecimal tax;
    private BigDecimal rents;
    private BigDecimal bankRepayments;
    private BigDecimal material;
    private BigDecimal insurance;
}
