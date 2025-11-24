package kz.bi.service.dto.incubator;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class IncubatorInvestmentDto {
    private Long id;
    private Long incubatorId;
    private BigDecimal seed;
    private BigDecimal state;
    private BigDecimal privates;
    private BigDecimal currentYearInvestment;
    private BigDecimal cumulativeInvestment;
}
