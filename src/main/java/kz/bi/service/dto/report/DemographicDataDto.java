package kz.bi.service.dto.report;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class DemographicDataDto {
    // Service
    private BigDecimal avgServiceCost;
    private BigDecimal serviceCostVariance;
    private BigDecimal serviceAdoptionIndex;
    private BigDecimal serviceCostIndex;

    // Facilities
    private BigDecimal avgFacilityCost;
    private BigDecimal facilityCostVariance;
    private BigDecimal facilityAdoptionIndex;
    private BigDecimal facilityCostIndex;

    // Training
    private BigDecimal avgTrainingCost;
    private BigDecimal trainingCostVariance;
    private BigDecimal trainingAdoptionIndex;
    private BigDecimal trainingCostIndex;

    // Services free to paid ratio
    private BigDecimal freeToPaidServicesRatio;
    private BigDecimal freeToPaidFacilitiesRatio;
    private BigDecimal freeToPaidTrainingsRatio;
}
