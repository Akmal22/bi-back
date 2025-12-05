package kz.bi.rest.controller.dto.report;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Schema(description = "Demographic data containing service, facility, and training metrics")
@Data
@Accessors(chain = true)
public class DemographicData {
    // Service
    @Schema(description = "Average service cost", example = "5000.00")
    private BigDecimal avgServiceCost;
    @Schema(description = "Service cost variance", example = "250.00")
    private BigDecimal serviceCostVariance;
    @Schema(description = "Service adoption index", example = "0.80")
    private BigDecimal serviceAdoptionIndex;
    @Schema(description = "Service cost index", example = "4000.00")
    private BigDecimal serviceCostIndex;

    // Facilities
    @Schema(description = "Average facility cost", example = "3000.00")
    private BigDecimal avgFacilityCost;
    @Schema(description = "Facility cost variance", example = "200.00")
    private BigDecimal facilityCostVariance;
    @Schema(description = "Facility adoption index", example = "0.75")
    private BigDecimal facilityAdoptionIndex;
    @Schema(description = "Facility cost index", example = "2250.00")
    private BigDecimal facilityCostIndex;

    // Training
    @Schema(description = "Average training cost", example = "2000.00")
    private BigDecimal avgTrainingCost;
    @Schema(description = "Training cost variance", example = "150.00")
    private BigDecimal trainingCostVariance;
    @Schema(description = "Training adoption index", example = "0.83")
    private BigDecimal trainingAdoptionIndex;
    @Schema(description = "Training cost index", example = "1660.00")
    private BigDecimal trainingCostIndex;

    // Services free to paid ratio
    @Schema(description = "Free to paid services ratio", example = "1.00")
    private BigDecimal freeToPaidServicesRatio;
    @Schema(description = "Free to paid facilities ratio", example = "1.00")
    private BigDecimal freeToPaidFacilitiesRatio;
    @Schema(description = "Free to paid trainings ratio", example = "1.00")
    private BigDecimal freeToPaidTrainingsRatio;
}
