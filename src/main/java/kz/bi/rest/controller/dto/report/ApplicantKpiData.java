package kz.bi.rest.controller.dto.report;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Schema(description = "Applicant KPI data containing acceptance, failure, graduation, and survival metrics")
@Data
@Accessors(chain = true)
public class ApplicantKpiData {
    @Schema(description = "Acceptance ratio (accepted applications / total applications)", example = "30.12")
    private BigDecimal acceptanceRatio;
    @Schema(description = "Companies failure index (failed companies / incubated companies)", example = "20.21")
    private BigDecimal companiesFailureIndex;
    @Schema(description = "Companies graduation index (graduated companies / incubated companies)", example = "60.00")
    private BigDecimal companiesGraduationIndex;
    @Schema(description = "Graduated companies survival rate (active after 5 years / incubated companies)", example = "32.99")
    private BigDecimal graduatedCompaniesSurvivalRate;
    @Schema(description = "Morality rate after 1 year", example = "32.99")
    private BigDecimal moralityRateAfter1Year;
    @Schema(description = "Morality rate after 3 years", example = "32.99")
    private BigDecimal moralityRateAfter3Years;
    @Schema(description = "Morality rate after 5 years", example = "32.99")
    private BigDecimal moralityRateAfter5Years;
}
