package kz.bi.rest.controller.dto.incubator.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class IncubatorInvestment {
    private BigDecimal seed;
    private BigDecimal state;
    private BigDecimal privates;
    private BigDecimal currentYearInvestment;
    private BigDecimal cumulativeInvestment;
}
