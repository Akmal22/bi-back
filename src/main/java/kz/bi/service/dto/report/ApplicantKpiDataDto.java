package kz.bi.service.dto.report;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class ApplicantKpiDataDto {
    private BigDecimal acceptanceRatio;
    private BigDecimal companiesFailureIndex;
    private BigDecimal companiesGraduationIndex;
    private BigDecimal graduatedCompaniesSurvivalRate;
    private BigDecimal moralityRateAfter1Year;
    private BigDecimal moralityRateAfter3Years;
    private BigDecimal moralityRateAfter5Years;
}
